import javax.crypto.*;
import java.security.*;
import java.util.Base64;

public class DES {
    public static void main(String[] args) {
        try {
            System.out.println("Message Encryption using DES Algorithm\n");
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey key = keyGen.generateKey();

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            String text = "University College of Engineering Nagercoil";
            System.out.println("Original Message: " + text);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            System.out.println("Encrypted Message:" + Base64.getEncoder().encodeToString(encrypted));

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(encrypted);
            System.out.println("Decrypted Message:" + new String(decrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}