package com.conttroller.securitycontabil.utils;

import java.util.Base64;

public class RC4Util {

    public static byte[] rc4(byte[] data, String key) {
        byte[] s = new byte[256];
        byte[] k = new byte[256];
        int i, j = 0, temp;

        for (i = 0; i < 256; i++) {
            s[i] = (byte) i;
            k[i] = (byte) key.charAt(i % key.length());
        }

        for (i = 0; i < 256; i++) {
            j = (j + s[i] + k[i]) & 0xFF;
            temp = s[i];
            s[i] = s[j];
            s[j] = (byte) temp;
        }

        i = j = 0;
        byte[] result = new byte[data.length];
        for (int x = 0; x < data.length; x++) {
            i = (i + 1) & 0xFF;
            j = (j + s[i]) & 0xFF;
            temp = s[i];
            s[i] = s[j];
            s[j] = (byte) temp;
            int t = (s[i] + s[j]) & 0xFF;
            result[x] = (byte) (data[x] ^ s[t]);
        }

        return result;
    }

    public static String encrypt(String text, String key) {
        byte[] encrypted = rc4(text.getBytes(), key);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String base64Text, String key) {
        byte[] encrypted = Base64.getDecoder().decode(base64Text);
        byte[] decrypted = rc4(encrypted, key);
        return new String(decrypted);
    }
}    