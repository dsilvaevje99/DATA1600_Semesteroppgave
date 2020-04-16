package sample;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

    public byte[] create_password(String input){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte [16];
        random.nextBytes(salt);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md.update(salt);
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
}
