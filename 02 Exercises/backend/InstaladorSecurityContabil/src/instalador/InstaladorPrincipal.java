package instalador;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class InstaladorPrincipal {

    // ==============================
    // 🔧 CONSTANTES DE CONFIGURAÇÃO
    // ==============================
    private static final String BASE_DIR = "CONTTROL";
    private static final String JAR_NAME = "securitycontabil.jar";
    private static final String SERVICE_NAME = "TokenService";
    private static final String LOG_PATH = "C:\\CONTTROL\\install.log";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";

    // ==============================
    // 🚀 MÉTODO PRINCIPAL
    // ==============================
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            limparTela();
            exibirCabecalho();

            if (!isAdmin()) {
                System.out.println(ANSI_RED + "⚠️  É necessário executar este instalador como Administrador!" + ANSI_RESET);
                return;
            }

            System.out.println("1 - Solicitar Token");
            System.out.println("2 - Instalar Serviço");
            System.out.print("\nEscolha uma opção: ");
            String opcao = reader.readLine().trim();

            // Captura drive e CNPJ em ambos os fluxos
            String drive = solicitarDrive(reader);
            if (drive == null) return;

            String cnpj = solicitarCnpj(reader);
            if (cnpj == null) return;

            switch (opcao) {
                case "1" -> solicitarToken(cnpj, drive);
                case "2" -> instalarServico(cnpj, drive, reader);
                default -> System.out.println(ANSI_RED + "❌ Opção inválida!" + ANSI_RESET);
            }

        } catch (IOException e) {
            System.err.println(ANSI_RED + "Erro de entrada/saída: " + e.getMessage() + ANSI_RESET);
        }
    }

    // ==============================
    // 🔐 VERIFICAÇÃO DE ADMINISTRADOR
    // ==============================
    private static boolean isAdmin() {
        try {
            Process p = new ProcessBuilder("net", "session").start();
            p.waitFor();
            return p.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ==============================
    // 🧾 SOLICITAÇÃO DE TOKEN
    // ==============================
    private static void solicitarToken(String cnpj, String drive) {
        limparTela();
        System.out.println("== Solicitação de Token ==");

        // Simula geração de token
        String token = UUID.randomUUID().toString().replace("-", "");
        System.out.println(ANSI_GREEN + "\nToken gerado com sucesso!" + ANSI_RESET);
        System.out.println("Drive: " + drive + ":\\");
        System.out.println("CNPJ: " + formatarCnpj(cnpj));
        System.out.println("Token: " + token);

        log("Token gerado para CNPJ: " + cnpj + " | Drive: " + drive + " | Token: " + token);
    }

    // ==============================
    // ⚙️ INSTALAÇÃO DO SERVIÇO
    // ==============================
    private static void instalarServico(String cnpj, String drive, BufferedReader reader) throws IOException {
        limparTela();
        System.out.println("== Instalação do Serviço ==");

        String basePath = drive + ":\\" + BASE_DIR;
        String jarPath = basePath + "\\" + JAR_NAME;

        System.out.println("Caminho do JAR: " + jarPath);
        System.out.println("CNPJ vinculado: " + formatarCnpj(cnpj));

        // Confirma instalação
        System.out.print("\nConfirma instalação do serviço? (S/N): ");
        if (!reader.readLine().trim().equalsIgnoreCase("S")) {
            System.out.println(ANSI_YELLOW + "Instalação cancelada pelo usuário." + ANSI_RESET);
            return;
        }

        executarProcesso(List.of("nssm", "stop", SERVICE_NAME), "Parando serviço existente (se houver)");
        executarProcesso(List.of("nssm", "remove", SERVICE_NAME, "confirm"), "Removendo serviço existente");
        executarProcesso(List.of("nssm", "install", SERVICE_NAME, "java", "-jar", jarPath), "Instalando serviço");
        executarProcesso(List.of("nssm", "set", SERVICE_NAME, "Start", "SERVICE_AUTO_START"), "Configurando inicialização automática");
        executarProcesso(List.of("nssm", "start", SERVICE_NAME), "Iniciando serviço");

        System.out.println(ANSI_GREEN + "\n✅ Serviço instalado e iniciado com sucesso!" + ANSI_RESET);
        log("Serviço instalado com sucesso | Drive: " + drive + " | CNPJ: " + cnpj);
    }

    // ==============================
    // 📥 ENTRADAS DE USUÁRIO
    // ==============================
    private static String solicitarDrive(BufferedReader reader) throws IOException {
        System.out.print("Digite a unidade onde o sistema está instalado (ex: C ou D): ");
        String drive = reader.readLine().trim().toUpperCase();

        if (drive.isEmpty() || !drive.matches("[A-Z]")) {
            System.out.println(ANSI_RED + "❌ Unidade inválida!" + ANSI_RESET);
            return null;
        }
        return drive;
    }

    private static String solicitarCnpj(BufferedReader reader) throws IOException {
        System.out.print("Digite o CNPJ (somente números): ");
        String cnpj = reader.readLine().trim();

        if (!Pattern.matches("\\d{14}", cnpj)) {
            System.out.println(ANSI_RED + "❌ CNPJ inválido!" + ANSI_RESET);
            return null;
        }
        return cnpj;
    }

    // ==============================
    // 🧰 EXECUÇÃO DE PROCESSOS
    // ==============================
    private static void executarProcesso(List<String> comando, String descricao) {
        try {
            System.out.println(ANSI_CYAN + "\n> " + descricao + "..." + ANSI_RESET);
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
            log(descricao + ": " + String.join(" ", comando));
        } catch (Exception e) {
            System.err.println(ANSI_RED + "Erro ao executar comando: " + descricao + " - " + e.getMessage() + ANSI_RESET);
            log("Erro ao executar: " + descricao + " | " + e.getMessage());
        }
    }

    // ==============================
    // 🧹 UTILITÁRIOS
    // ==============================
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

    private static void log(String msg) {
        try (FileWriter fw = new FileWriter(LOG_PATH, true)) {
            fw.write(new Date() + " - " + msg + System.lineSeparator());
        } catch (IOException ignored) {}
    }

    private static String formatarCnpj(String cnpj) {
        return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                "$1.$2.$3/$4-$5");
    }
}