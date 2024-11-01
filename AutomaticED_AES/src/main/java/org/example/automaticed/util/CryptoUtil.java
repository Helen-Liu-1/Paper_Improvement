package org.example.automaticed.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {


    // private static final String TRANSFORMATION = "DES"; 
    private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding"; //The TRANSFORMATION should be DES/ECB/PKCS5Padding, which is just like in the DESSensitiveConverter

    // private static final String SECRET_KEY = "mySuperSecretKey";
    private static final String SECRET_KEY = "mySuperS"; //The SECRET_KEY should be 8 bytes long
    
    public static String encrypt(String input,String algorithm) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String input,String algorithm) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), algorithm);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] decodedBytes = Base64.getDecoder().decode(input);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
