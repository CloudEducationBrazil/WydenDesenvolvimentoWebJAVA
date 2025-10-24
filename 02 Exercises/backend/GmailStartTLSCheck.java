import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;

public class GmailStartTLSCheck {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        int port = 587;

        try {
            System.out.println("Conectando a " + host + ":" + port + " ...");
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 1️⃣ Lê a mensagem inicial do servidor
            String line = in.readLine();
            System.out.println("Resposta inicial: " + line);

            // 2️⃣ Envia EHLO
            out.println("EHLO teste");
            out.flush();
            System.out.println("\nResposta EHLO:");
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.contains("STARTTLS")) {
                    System.out.println("✅ STARTTLS suportado pelo servidor.");
                }
                if (!line.startsWith("250-") && !line.startsWith("250 ")) {
                    break;
                }
            }

            // 3️⃣ Envia STARTTLS
            out.println("STARTTLS");
            out.flush();
            line = in.readLine();
            System.out.println("\nResposta STARTTLS: " + line);

            if (line != null && line.startsWith("220")) {
                System.out.println("✅ STARTTLS está liberado na rede!");
            } else {
                System.err.println("❌ STARTTLS bloqueado ou interceptado na rede!");
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Falha na conexão. Possível bloqueio de rede ou firewall.");
        }
    }
}