package com.dev.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;

public class HashUtil {
    private static Logger logger = Logger.getLogger(HashUtil.class);
    private static final String SHA = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());

            for (byte b : digest) {
                hashedPassword.append(String.format("%2x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Can't hash password", e);
        }
        return hashedPassword.toString();
    }
}
