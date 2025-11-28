package com.conttroller.securitycontabil.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.SecureRandom;
import java.util.Base64;

public final class AESUtil {
    private static final String ALGO = "AES";
    private static final String TRANSFORM = "AES/GCM/NoPadding";
    private static final int IV_LEN = 12; // 96 bits - recommended
    private static final int TAG_LEN = 128; // 128 bits
    private static final int KEY_LEN = 256; // bits
    private static final int ITER = 65536;

    private AESUtil() {}

    // derive key from passphrase using PBKDF2
    public static SecretKey deriveKey(char[] password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITER, KEY_LEN);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = skf.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, ALGO);
    }

    public static String encrypt(SecretKey key, String plaintext) throws Exception {
        byte[] iv = new byte[IV_LEN];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(TRANSFORM);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LEN, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] cipherText = cipher.doFinal(plaintext.getBytes("UTF-8"));

        byte[] combined = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);
        return Base64.getEncoder().encodeToString(combined);
    }

    public static String decrypt(SecretKey key, String b64) throws Exception {
        byte[] combined = Base64.getDecoder().decode(b64);
        byte[] iv = new byte[IV_LEN];
        System.arraycopy(combined, 0, iv, 0, IV_LEN);
        int ctLen = combined.length - IV_LEN;
        byte[] cipherText = new byte[ctLen];
        System.arraycopy(combined, IV_LEN, cipherText, 0, ctLen);

        Cipher cipher = Cipher.getInstance(TRANSFORM);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LEN, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] plain = cipher.doFinal(cipherText);
        return new String(plain, "UTF-8");
    }
}