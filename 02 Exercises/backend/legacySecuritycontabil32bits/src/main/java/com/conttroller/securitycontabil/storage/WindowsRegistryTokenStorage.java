package com.conttroller.securitycontabil.storage;

import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinCrypt.DATA_BLOB;
import com.sun.jna.platform.win32.Crypt32;
import com.sun.jna.platform.win32.Kernel32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class WindowsRegistryTokenStorage implements TokenStorage {

    private static final Logger logger = LoggerFactory.getLogger(WindowsRegistryTokenStorage.class);

    private static final HKEY ROOT = WinReg.HKEY_LOCAL_MACHINE;
    private static final String REG_PATH = "Software\\Lorttnoc\\Snekot";

    private static final int DPAPI_MACHINE_SCOPE = WinCrypt.CRYPTPROTECT_LOCAL_MACHINE;

    // -----------------------
    // DPAPI MACHINE-WIDE REAL
    // -----------------------

    private byte[] protectMachineWide(byte[] data) {
        DATA_BLOB in = new DATA_BLOB(data);
        DATA_BLOB out = new DATA_BLOB();

        boolean ok = Crypt32.INSTANCE.CryptProtectData(
                in,
                null,
                null,
                null,
                null,
                DPAPI_MACHINE_SCOPE,
                out
        );

        if (!ok) {
            int err = Kernel32.INSTANCE.GetLastError();
            throw new Win32Exception(err);
        }

        return out.getData();
    }

    private byte[] unprotect(byte[] data) {
        DATA_BLOB in = new DATA_BLOB(data);
        DATA_BLOB out = new DATA_BLOB();

        boolean ok = Crypt32.INSTANCE.CryptUnprotectData(
                in,
                null,
                null,
                null,
                null,
                0,
                out
        );

        if (!ok) {
            int err = Kernel32.INSTANCE.GetLastError();
            throw new Win32Exception(err);
        }

        return out.getData();
    }

    // -----------------------
    // Registry base
    // -----------------------

    private void ensureKeyExists() {
        try {
            if (!Advapi32Util.registryKeyExists(ROOT, "Software")) {
                Advapi32Util.registryCreateKey(ROOT, "Software");
            }

            String level1 = "Software\\Lorttnoc";
            if (!Advapi32Util.registryKeyExists(ROOT, level1)) {
                Advapi32Util.registryCreateKey(ROOT, level1);
            }

            if (!Advapi32Util.registryKeyExists(ROOT, REG_PATH)) {
                Advapi32Util.registryCreateKey(ROOT, REG_PATH);
            }

        } catch (Exception e) {
            logger.warn("Falha ao garantir existência da chave: {}", e.getMessage());
        }
    }

    @Override
    public void save(String key, String json) throws IOException {
        try {
            ensureKeyExists();

            byte[] encrypted = protectMachineWide(
                    json.getBytes(StandardCharsets.UTF_8)
            );

            Advapi32Util.registrySetBinaryValue(ROOT, REG_PATH, key, encrypted);

            logger.info("Valor '{}' salvo em HKLM\\{} (DPAPI machine-wide)", key, REG_PATH);

        } catch (Exception e) {
            throw new IOException("Erro ao salvar no registry HKLM: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<String> load(String key) throws IOException {
        try {
            if (!Advapi32Util.registryValueExists(ROOT, REG_PATH, key)) {
                return Optional.empty();
            }

            byte[] encrypted = Advapi32Util.registryGetBinaryValue(ROOT, REG_PATH, key);

            if (encrypted == null || encrypted.length == 0) {
                return Optional.empty();
            }

            byte[] decrypted;

            try {
                decrypted = unprotect(encrypted);
            } catch (Win32Exception ex) {
                logger.warn("DPAPI falhou ao descriptografar '{}': {}", key, ex.getMessage());
                return Optional.empty();
            }

            if (decrypted == null || decrypted.length == 0) {
                return Optional.empty();
            }

            return Optional.of(new String(decrypted, StandardCharsets.UTF_8));

        } catch (Exception e) {
            throw new IOException("Erro ao ler do registry HKLM: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String key) throws IOException {
        try {
            if (Advapi32Util.registryValueExists(ROOT, REG_PATH, key)) {
                Advapi32Util.registryDeleteValue(ROOT, REG_PATH, key);
            }

        } catch (Exception e) {
            throw new IOException("Erro ao deletar valor no registry: " + e.getMessage(), e);
        }
    }
}