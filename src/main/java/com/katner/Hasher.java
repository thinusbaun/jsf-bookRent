package com.katner;
/* Taken from: https://gist.github.com/lukaszb/1af1bd4233326e37a8a0 */

/* Example implementation of password hasher similar on Django's PasswordHasher
 * Requires Java8 (but should be easy to port to older JREs)
 * Currently it would work only for pbkdf2_sha256 algorithm
 *
 * Django code: https://github.com/django/django/blob/1.6.5/django/contrib/auth/hashers.py#L221
 */

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;


public class Hasher {
    public static final Integer DEFAULT_ITERATIONS = 20000;
    public static final String algorithm = "pbkdf2_sha256";
    private static final SecureRandom random = new SecureRandom();
    private static final String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Hasher() {
    }

    public static String getEncodedHash(String password, String salt, int iterations) {
        // Returns only the last part of whole encoded password
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could NOT retrieve PBKDF2WithHmacSHA256 algorithm");
            System.exit(1);
        }
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(Charset.forName("UTF-8")), iterations, 256);
        SecretKey secret = null;
        try {
            secret = keyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            System.out.println("Could NOT generate secret key");
            e.printStackTrace();
        }

        byte[] rawHash = secret.getEncoded();
        byte[] hashBase64 = Base64.getEncoder().encode(rawHash);

        return new String(hashBase64);
    }

    public static String encode(String password, String salt, int iterations) {
        // returns hashed password, along with algorithm, number of iterations and salt
        String hash = getEncodedHash(password, salt, iterations);
        return String.format("%s$%d$%s$%s", algorithm, iterations, salt, hash);
    }

    public static String encode(String password) {
        return encode(password, getRandomString(12), DEFAULT_ITERATIONS);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        // hashedPassword consist of: ALGORITHM, ITERATIONS_NUMBER, SALT and
        // HASH; parts are joined with dollar character ("$")
        String[] parts = hashedPassword.split("\\$");
        if (parts.length != 4) {
            // wrong hash format
            return false;
        }
        Integer iterations = Integer.parseInt(parts[1]);
        String salt = parts[2];
        String hash = encode(password, salt, iterations);

        return hash.equals(hashedPassword);
    }

    private static String getRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
//            builder.append(allowedChars.charAt(random.nextInt(256) % allowedChars.length() - 1));
            builder.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }
        return builder.toString();
    }

}