package com.conttroller.securitycontabil.storage;


import com.conttroller.securitycontabil.crypto.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Optional;

@Component
public class FileTokenStorage implements TokenStorage {

    private static final Logger logger = LoggerFactory.getLogger(FileTokenStorage.class);

    @Value("${token.storage.password:}")
    private String password; // prefer set by env var

    @Value("${caminho.default:}")
    private String storageDir;

    private static final String SALT_FILENAME = ".token_salt";

    private SecretKey key;

    private synchronized SecretKey getOrCreateKey() throws Exception {
        if (key != null) return key;
        Path dir = Path.of(storageDir);
        Files.createDirectories(dir);

        Path saltFile = dir.resolve(SALT_FILENAME);
        byte[] salt;
        if (Files.exists(saltFile)) {
            salt = Files.readAllBytes(saltFile);
        } else {
            salt = new byte[16];
            new SecureRandom().nextBytes(salt);
            Files.write(saltFile, salt);
        }

        if (password == null || password.isBlank()) {
            throw new IllegalStateException("token.storage.password não configurado (env var).");
        }

        key = AESUtil.deriveKey(password.toCharArray(), salt);
        return key;
    }

    @Override
    public void save(String keyName, String json) throws IOException {
        try {
            SecretKey sk = getOrCreateKey();
            String cipher = AESUtil.encrypt(sk, json);
            Path file = Path.of(storageDir, keyName + ".enc");
            Files.writeString(file, cipher);
        } catch (Exception e) {
            logger.error("Erro ao salvar em disco (FileTokenStorage): {}", e.getMessage(), e);
            throw new IOException(e);
        }
    }

    @Override
    public Optional<String> load(String keyName) throws IOException {
        try {
            SecretKey sk = getOrCreateKey();
            Path file = Path.of(storageDir, keyName + ".enc");
            if (!Files.exists(file)) return Optional.empty();
            String b64 = Files.readString(file);
            String json = AESUtil.decrypt(sk, b64);
            return Optional.ofNullable(json);
        } catch (Exception e) {
            logger.warn("Falha ao carregar arquivo criptografado: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void delete(String keyName) throws IOException {
        Path file = Path.of(storageDir, keyName + ".enc");
        Files.deleteIfExists(file);
    }
}