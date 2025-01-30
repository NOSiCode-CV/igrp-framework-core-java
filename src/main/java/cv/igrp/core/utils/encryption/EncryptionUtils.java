package cv.igrp.core.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionUtils {

    // Logger instance
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionUtils.class);

    // Encrypt content using AES with a default key
    public static String encrypt(String content) {
        try {
            return encrypt(content, "h0r1z0n");
        } catch (Exception e) {
            LOGGER.error("Error encrypting content with default secret key", e);
            return null;
        }
    }

    // Encrypt content using AES with a specified secret key
    public static String encrypt(String content, String secretKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(generateKey(secretKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            LOGGER.error("Error encrypting content with secret key: {}", secretKey, e);
            return null;
        }
    }

    // Decrypt content using AES with a default key
    public static String decrypt(String content) {
        try {
            return decrypt(content, "h0r1z0n");
        } catch (Exception e) {
            LOGGER.error("Error decrypting content with default secret key", e);
            return null;
        }
    }

    // Decrypt content using AES with a specified secret key
    public static String decrypt(String content, String secretKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(generateKey(secretKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(content);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("Error decrypting content with secret key: {}", secretKey, e);
            return null;
        }
    }

    // Helper method to generate a secret key from the provided key
    private static byte[] generateKey(String secretKey) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        key = sha.digest(key);
        return key;
    }

}
