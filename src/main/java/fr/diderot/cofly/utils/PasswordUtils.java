package fr.diderot.cofly.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordUtils {

    public static byte[] getSecurePassword(String passwordToHash) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            return sb.toString().getBytes();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
