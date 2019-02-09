package app.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        Hex hex = new Hex();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] hash = digest.digest(
                str.getBytes(StandardCharsets.UTF_8));

        return new String(hex.encode(hash));
    }
}
