package instalador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SolicitarToken {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("=========================================");
            System.out.println("== Instalador de Token Contábil        ==");
            System.out.println("=========================================");

            // Solicita drive
            System.out.print("Digite a unidade onde o sistema está instalado (ex: C ou D): ");
            String drive = reader.readLine().trim().toUpperCase();
            if (drive.isEmpty()) {
                System.err.println("Unidade inválida. Encerrando instalação.");
                return;
            }

            // Solicita CNPJ
            System.out.print("Digite o CNPJ da empresa (somente números): ");
            String cnpj = reader.readLine().trim();
            if (cnpj.isEmpty() || !cnpj.matches("^\\d{14}$")) {
                System.err.println("CNPJ inválido. Encerrando instalação.");
                return;
            }

            // Monta caminhos
            String basePath = drive + ":\\CONTTROL";
            String javaPath = basePath + "\\java\\bin\\java.exe";
            String jarPath = basePath + "\\securitycontabil.jar";
            String caminho = basePath + "\\";
            String gmailAppPassword = "cofyuorxbeuxabbf";

            // Exibe resumo
            System.out.println("\n=========================================");
            System.out.println("Configurações detectadas:");
            System.out.println("Drive: " + drive + ":");
            System.out.println("CNPJ: " + cnpj);
            System.out.println("JVM Local: " + javaPath);
            System.out.println("=========================================\n");

            // Executa comando
            System.out.println("Gerando token e enviando por e-mail...\n");

            ProcessBuilder pb = new ProcessBuilder(
                    javaPath,
                    "-Dspring.profiles.active=first",
                    "-DGMAIL_APP_PASSWORD=" + gmailAppPassword,
                    "-Dspring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration",
                    "-jar", jarPath,
                    cnpj,
                    caminho
            );

            pb.inheritIO(); // saída e erro no mesmo console
            Process process = pb.start();
            int exitCode = process.waitFor();

            System.out.println("\n=========================================");
            if (exitCode == 0) {
                System.out.println("Token enviado com sucesso! Verifique seu e-mail.");
            } else {
                System.err.println("Falha ao gerar o token (código " + exitCode + ").");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
        }
    }
}