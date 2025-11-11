import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Client {
    public static void main(String[] args) throws Exception {
        
        KeyStore ts = KeyStore.getInstance("JKS");
        ts.load(new FileInputStream("clienttruststore.jks"), "password123".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ts);

        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, tmf.getTrustManagers(), null);

        try (SSLSocket s = (SSLSocket) ctx.getSocketFactory().createSocket("localhost", 8443);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            s.startHandshake();
            System.out.println("Connected. Type messages (exit to quit):");

            for (String msg; !(msg = console.readLine()).equalsIgnoreCase("exit"); ) {
                out.println(msg);
                System.out.println("Server: " + in.readLine());
            }
        }
    }
}
