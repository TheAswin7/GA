import java.security.*;
import java.util.*;

public class CreatingDigitalSignature {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter some text: ");
        String msg = sc.nextLine();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(2048);
        PrivateKey priv = kpg.generateKeyPair().getPrivate();

        Signature sig = Signature.getInstance("SHA256withDSA");
        sig.initSign(priv);
        sig.update(msg.getBytes());

        String encoded = Base64.getEncoder().encodeToString(sig.sign());
        System.out.println("Digital signature for given text: " + encoded);
    }
}