package org.example.automaticed.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "mySuperSecretKey1234567890123456";

    public static String encrypt(String input) {
        try {

            byte[] keyBytes = SECRET_KEY.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);


            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);


            byte[] encryptedBytes = cipher.doFinal(input.getBytes());


            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error: " + e.getMessage(), e);
        }
    }

    public static String decrypt(String input) {
        try {

            byte[] keyBytes = SECRET_KEY.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);


            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);


            byte[] decodedBytes = Base64.getDecoder().decode(input);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);


            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Decryption error: " + e.getMessage(), e);
        }
    }


    public static void main(String[] args) {
        String text = "TestMessage";
        try {

            String encrypted = CryptoUtil.encrypt(text);
            System.out.println("Encrypted: " + encrypted);


            String decrypted = CryptoUtil.decrypt(encrypted);
            System.out.println("Decrypted: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


