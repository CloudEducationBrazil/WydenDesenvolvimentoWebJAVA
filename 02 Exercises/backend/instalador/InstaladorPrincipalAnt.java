package instalador;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class InstaladorPrincipal {

    private static final String JAR_NAME = "securitycontabil.jar";
    private static final String SERVICE_NAME = "TokenService";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            limparTela();
            exibirCabecalho();

            if (!isAdmin()) {
                System.out.println(ANSI_RED + "⚠️  Execute este instalador como Administrador!" + ANSI_RESET);
                return;
            }

            System.out.println("1 - Solicitar Token");
            System.out.println("2 - Instalar Serviço");
            System.out.print("\nEscolha uma opção: ");
            String opcao = reader.readLine().trim();

            switch (opcao) {
                case "1" -> solicitarToken(reader);
                case "2" -> instalarServico(reader);
                default -> System.out.println(ANSI_RED + "❌ Opção inválida!" + ANSI_RESET);
            }

        } catch (IOException e) {
            System.err.println(ANSI_RED + "Erro de entrada/saída: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static boolean isAdmin() {
        try {
            Process p = new ProcessBuilder("net", "session").start();
            p.waitFor();
            return p.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static void solicitarToken(BufferedReader reader) throws IOException {
        System.out.println("\n== Solicitação de Token ==");

        // Solicita caminho completo
        System.out.print("Digite o caminho completo onde o sistema está instalado (ex: C:\\conttroller\\conttrol): ");
        String caminhoInformado = reader.readLine().trim();
        if (caminhoInformado.isEmpty()) {
            System.err.println("Caminho inválido. Encerrando instalação.");
            return;
        }

        File dir = new File(caminhoInformado);
        File jarFile = new File(dir, JAR_NAME);

        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println(ANSI_RED + "❌ O diretório informado não existe: " + caminhoInformado + ANSI_RESET);
            return;
        }

        if (!jarFile.exists()) {
            System.err.println(ANSI_RED + "❌ Arquivo " + JAR_NAME + " não encontrado no caminho informado." + ANSI_RESET);
            return;
        }

        // Solicita CNPJ
        System.out.print("Digite o CNPJ da empresa (somente números): ");
        String cnpj = reader.readLine().trim();
        if (cnpj.isEmpty() || !cnpj.matches("^\\d{14}$")) {
            System.err.println("CNPJ inválido. Encerrando instalação.");
            return;
        }

        // Caminhos dinâmicos
        String javaPath = caminhoInformado + "\\java\\bin\\java.exe";
        String gmailAppPassword = "cofyuorxbeuxabbf";
        String logPath = caminhoInformado + "\\install.log";

        System.out.println("\nExecutando geração de token...");

        executarProcesso(new String[]{
                javaPath,
                "-Dspring.profiles.active=first",
                "-DGMAIL_APP_PASSWORD=" + gmailAppPassword,
                "-Dspring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration",
                "-jar", jarFile.getAbsolutePath(),
                cnpj,
                caminhoInformado
        }, "Gerando token e enviando por e-mail...", logPath);
    }

    private static void instalarServico(BufferedReader reader) throws IOException {
        System.out.println("\n== Instalação do Serviço ==");

        // Solicita caminho completo
        System.out.print("Digite o caminho completo onde o sistema está instalado (ex: C:\\conttroller\\conttrol): ");
        String caminhoInformado = reader.readLine().trim();
        if (caminhoInformado.isEmpty()) {
            System.err.println("Caminho inválido. Encerrando instalação.");
            return;
        }

        File dir = new File(caminhoInformado);
        File jarFile = new File(dir, JAR_NAME);

        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println(ANSI_RED + "❌ O diretório informado não existe: " + caminhoInformado + ANSI_RESET);
            return;
        }

        if (!jarFile.exists()) {
            System.err.println(ANSI_RED + "❌ Arquivo " + JAR_NAME + " não encontrado no caminho informado." + ANSI_RESET);
            return;
        }

        // Solicita CNPJ
        System.out.print("Digite o CNPJ da empresa (somente números): ");
        String cnpj = reader.readLine().trim();
        if (cnpj.isEmpty() || !cnpj.matches("^\\d{14}$")) {
            System.err.println("CNPJ inválido. Encerrando instalação.");
            return;
        }

        // Solicita Token
        System.out.print("Digite o TOKEN de autenticação: ");
        String token = reader.readLine().trim();
        if (token.isEmpty() || !token.matches("^[A-Za-z0-9._-]{20,}$")) {
            System.err.println("Token inválido! Encerrando instalação.");
            return;
        }

        String javaPath = caminhoInformado + "\\java\\bin\\java.exe";
        String logPath = caminhoInformado + "\\install.log";

        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64 = System.getenv("PROCESSOR_ARCHITEW6432");
        boolean is64bit = (arch != null && arch.endsWith("64")) || (wow64 != null && wow64.endsWith("64"));
        String nssmPath = caminhoInformado + "\\nssm\\" + (is64bit ? "win64" : "win32") + "\\nssm.exe";

        System.out.println("Caminho do JAR: " + jarFile.getAbsolutePath());
        System.out.println("CNPJ vinculado: " + formatarCnpj(cnpj));

        System.out.print("\nConfirma instalação do serviço? (S/N): ");
        if (!reader.readLine().trim().equalsIgnoreCase("S")) {
            System.out.println(ANSI_YELLOW + "Instalação cancelada pelo usuário." + ANSI_RESET);
            return;
        }

        executarProcesso(new String[]{nssmPath, "stop", SERVICE_NAME}, "Parando serviço existente (se houver)", logPath);
        executarProcesso(new String[]{nssmPath, "remove", SERVICE_NAME, "confirm"}, "Removendo serviço existente", logPath);
        executarProcesso(new String[]{nssmPath, "install", SERVICE_NAME, javaPath}, "Instalando serviço base", logPath);
        executarProcesso(new String[]{nssmPath, "set", SERVICE_NAME, "AppParameters",
                "-jar \"" + jarFile.getAbsolutePath() + "\" " + cnpj + " \"" + caminhoInformado + "\" \"" + token + "\""},
                "Configurando parâmetros do serviço", logPath);
        executarProcesso(new String[]{nssmPath, "set", SERVICE_NAME, "AppDirectory", caminhoInformado},
                "Configurando diretório de trabalho", logPath);
        executarProcesso(new String[]{nssmPath, "set", SERVICE_NAME, "Start", "SERVICE_AUTO_START"},
                "Configurando inicialização automática", logPath);
        executarProcesso(new String[]{nssmPath, "start", SERVICE_NAME}, "Iniciando serviço", logPath);

        System.out.println(ANSI_GREEN + "\n✅ Serviço instalado e iniciado com sucesso!" + ANSI_RESET);
        log("Serviço instalado com sucesso | Caminho: " + caminhoInformado + " | CNPJ: " + cnpj, logPath);
    }

    private static void executarProcesso(String[] comando, String descricao, String logPath) {
        try {
            System.out.println(ANSI_CYAN + "\n> " + descricao + "..." + ANSI_RESET);
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
            log(descricao + ": " + String.join(" ", comando), logPath);
        } catch (Exception e) {
            System.err.println(ANSI_RED + "Erro ao executar comando: " + descricao + " - " + e.getMessage() + ANSI_RESET);
            log("Erro ao executar: " + descricao + " | " + e.getMessage(), logPath);
        }
    }

    private static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ignored) {}
    }

    private static void exibirCabecalho() {
        System.out.println(ANSI_YELLOW + "============================================" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "       INSTALADOR DO SISTEMA CONTÁBIL" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "============================================\n" + ANSI_RESET);
    }

    private static void log(String msg, String logPath) {
        try {
            File logFile = new File(logPath);
            logFile.getParentFile().mkdirs();
            try (FileWriter fw = new FileWriter(logFile, true)) {
                String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                fw.write(data + " - " + msg + System.lineSeparator());
            }
        } catch (IOException ignored) {}
    }

    private static String formatarCnpj(String cnpj) {
        return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}