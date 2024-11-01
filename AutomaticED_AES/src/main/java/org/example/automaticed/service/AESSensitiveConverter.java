package org.example.automaticed.service;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Converter
public class AESSensitiveConverter implements AttributeConverter<String, String> {


    private static final String ALGORITHM = "AES";



    private static final String TRANSFORMATION = "AES";

    private static final String SECRET_KEY = "mySuperSecretKey";


    @Override
    public String convertToDatabaseColumn(String input) {

        byte[] keyBytes ;
        //encrypt
        if (input == null) {
            return null;
        }

        if(ALGORITHM.equals("DES")){
            keyBytes = SECRET_KEY.getBytes();
            if (keyBytes.length < 8) {

                byte[] correctKeyBytes = new byte[8];
                System.arraycopy(keyBytes, 0, correctKeyBytes, 0, keyBytes.length);
                keyBytes = correctKeyBytes;
            } else if (keyBytes.length > 8) {

                byte[] correctKeyBytes = Arrays.copyOf(keyBytes, 8);
                keyBytes = correctKeyBytes;
            }
        }else {
            keyBytes=  SECRET_KEY.getBytes();
        }


        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        Cipher cipher = null;
        try {

            cipher = Cipher.getInstance(TRANSFORMATION);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedBytes = cipher.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String convertToEntityAttribute(String input) {
        //decrypt

        byte[] keyBytes ;
        //encrypt
        if (input == null) {
            return null;
        }

        if(ALGORITHM.equals("DES")){
            keyBytes = SECRET_KEY.getBytes();
            if (keyBytes.length < 8) {

                byte[] correctKeyBytes = new byte[8];
                System.arraycopy(keyBytes, 0, correctKeyBytes, 0, keyBytes.length);
                keyBytes = correctKeyBytes;
            } else if (keyBytes.length > 8) {

                byte[] correctKeyBytes = Arrays.copyOf(keyBytes, 8);
                keyBytes = correctKeyBytes;
            }
        }else {
            keyBytes=  SECRET_KEY.getBytes();
        }


        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);


        Cipher cipher = null;
        try {

            cipher = Cipher.getInstance(TRANSFORMATION);

            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(input);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
