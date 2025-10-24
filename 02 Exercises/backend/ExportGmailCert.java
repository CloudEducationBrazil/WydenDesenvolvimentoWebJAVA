import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class ExportAndImportGmailCert {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        int port = 587;
        String alias = "gmail";
        String certFile = "gmail-cert.pem";

        try {
            // 1️⃣ Conecta ao servidor SMTP
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Resposta inicial: " + in.readLine());

            // EHLO
            out.println("EHLO test");
            out.flush();
            boolean starttlsSupported = false;
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.contains("STARTTLS")) starttlsSupported = true;
                if (line.startsWith("250 ")) break;
            }
            if (!starttlsSupported) throw new Exception("STARTTLS não suportado");

            // STARTTLS
            out.println("STARTTLS");
            out.flush();
            boolean starttlsOk = false;
            while ((line = in.readLine()) != null) {
                System.out.println("STARTTLS response: " + line);
                if (line.startsWith("220")) {
                    starttlsOk = true;
                    break;
                }
            }
            if (!starttlsOk) throw new Exception("STARTTLS não aceito");

            // 2️⃣ Handshake TLS
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(socket, host, port, true);
            sslSocket.startHandshake();
            System.out.println("✅ Handshake TLS concluído");

            SSLSession session = sslSocket.getSession();
            Certificate[] certs = session.getPeerCertificates();

            // 3️⃣ Salva certificado em PEM
            try (FileOutputStream fos = new FileOutputStream(certFile)) {
                for (Certificate cert : certs) {
                    if (cert instanceof X509Certificate) {
                        byte[] encoded = cert.getEncoded();
                        String base64 = Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(encoded);
                        fos.write("-----BEGIN CERTIFICATE-----\n".getBytes());
                        fos.write(base64.getBytes());
                        fos.write("\n-----END CERTIFICATE-----\n".getBytes());
                        break;
                    }
                }
            }
            System.out.println("✅ Certificado salvo em: " + certFile);

            // 4️⃣ Importa automaticamente no cacerts
            String cacertsPath = System.getProperty("java.home") + File.separator + "lib" + File.separator + "security" + File.separator + "cacerts";
            FileInputStream is = new FileInputStream(cacertsPath);
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(is, "changeit".toCharArray()); // senha padrão do cacerts
            is.close();

            for (Certificate cert : certs) {
                if (cert instanceof X509Certificate) {
                    ks.setCertificateEntry(alias, cert);
                    break;
                }
            }

            try (FileOutputStream outKs = new FileOutputStream(cacertsPath)) {
                ks.store(outKs, "changeit".toCharArray());
            }

            System.out.println("✅ Certificado importado no cacerts da JVM com alias '" + alias + "'");

            sslSocket.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
