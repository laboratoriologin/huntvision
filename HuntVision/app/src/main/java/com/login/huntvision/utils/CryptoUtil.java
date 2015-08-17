package com.login.huntvision.utils;

import java.security.MessageDigest;

/**
 * Created by Ricardo on 19/01/2015.
 */

public final class CryptoUtil {

    /**
     * Method gerarHash
     *
     * @author Henrique Machado
     * @param plainText
     * @param algorithm
     *            The algorithm to use like MD2, MD5, SHA-1, etc.
     * @return String
     * @throws Exception
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String gerarHash(String plainText, String algorithm) throws Exception {

        MessageDigest mdAlgorithm;

        StringBuffer hexString = new StringBuffer();

        try {

            mdAlgorithm = MessageDigest.getInstance(algorithm);

            mdAlgorithm.update(plainText.getBytes());

            byte[] digest = mdAlgorithm.digest();

            for (int i = 0; i < digest.length; i++) {

                plainText = Integer.toHexString(0xFF & digest[i]);

                if (plainText.length() < 2) {

                    plainText = "0" + plainText;
                }

                hexString.append(plainText);
            }

        } catch (Exception e) {

            throw new Exception(e);
        }

        return hexString.toString();
    }
}
