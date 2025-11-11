import javax.crypto.*;
import java.security.*;
import java.util.Base64;

public class AES {
    public static void main(String[] args) {
        try {
	    System.out.println("Message Encryption using AES Algorithm\n");
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey Key = keyGen.generateKey();

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            String url = "www.annauniv.edu";
            System.out.println("Original URL: " + url);

            cipher.init(Cipher.ENCRYPT_MODE, Key);
            byte[] encrypted = cipher.doFinal(url.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("Encrypted URL: " + encryptedText);

            cipher.init(Cipher.DECRYPT_MODE, Key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            System.out.println("Decrypted URL: " + new String(decrypted));
  
      } catch (Exception e) {
            e.printStackTrace();
        }
    }
}