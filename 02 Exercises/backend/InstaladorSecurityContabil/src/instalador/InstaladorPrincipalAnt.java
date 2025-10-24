package instalador;

import java.io.*;
import java.nio.file.*;

public class InstaladorPrincipalAnt {

    public static void main(String[] args) {
    	limparTela();
    	
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("==========================================");
            System.out.println("== Instalador Token Contábil             ==");
            System.out.println("==========================================\n");

            System.out.println("Escolha a operação:");
            System.out.println("1 - Solicitar Token");
            System.out.println("2 - Instalar Serviço");
            System.out.print("Opção: ");
            String opcao = reader.readLine().trim();

            if (opcao.equals("1")) {
                solicitarToken(reader);
            } else if (opcao.equals("2")) {
                instalarServico(reader);
            } else {
                System.err.println("Opção inválida. Encerrando.");
            }

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void solicitarToken(BufferedReader reader) throws IOException {
        System.out.println("\n== Solicitação de Token ==");
        System.out.print("Digite a unidade onde o sistema está instalado (ex: C ou D): ");
        String drive = reader.readLine().trim().toUpperCase();
        if (drive.isEmpty()) {
            System.err.println("Unidade inválida. Encerrando instalação.");
            return;
        }

        System.out.print("Digite o CNPJ da empresa (somente números): ");
        String cnpj = reader.readLine().trim();
        if (cnpj.isEmpty() || !cnpj.matches("^\\d{14}$")) {
            System.err.println("CNPJ inválido. Encerrando instalação.");
            return;
        }

        String basePath = drive + ":\\CONTTROL";
        String javaPath = basePath + "\\java\\bin\\java.exe";
        String jarPath = basePath + "\\securitycontabil.jar";
        String caminho = basePath + "\\";
        String gmailAppPassword = "cofyuorxbeuxabbf";

        System.out.println("\nExecutando geração de token...");
        executarProcesso(new String[]{
                javaPath,
                "-Dspring.profiles.active=first",
                "-DGMAIL_APP_PASSWORD=" + gmailAppPassword,
                "-Dspring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration",
                "-jar", jarPath,
                cnpj,
                caminho
        }, "Gerando token e enviando por e-mail...");
    }

    private static void instalarServico(BufferedReader reader) throws IOException {
        System.out.println("\n== Instalação de Serviço ==");
        System.out.print("Digite a unidade onde o sistema está instalado (ex: C ou D): ");
        String drive = reader.readLine().trim().toUpperCase();
        if (!drive.matches("^[A-Z]$")) {
            System.err.println("Unidade inválida! Encerrando instalação.");
            return;
        }

        System.out.print("Digite o CNPJ da empresa (somente números): ");
        String cnpj = reader.readLine().trim();
        if (!cnpj.matches("^\\d{14}$")) {
            System.err.println("CNPJ inválido! Deve conter 14 números.");
            return;
        }

        System.out.print("Digite o TOKEN de autenticação: ");
        String token = reader.readLine().trim();
        if (token.isEmpty()) {
            System.err.println("Token não pode ser vazio! Encerrando instalação.");
            return;
        }

        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
        boolean is64bit = (arch != null && arch.endsWith("64")) || (wow64Arch != null && wow64Arch.endsWith("64"));

        String basePath = drive + ":\\CONTTROL";
        String javaPath = basePath + "\\java\\bin\\java.exe";
        String jarPath = basePath + "\\securitycontabil.jar";
        String nssmPath = basePath + "\\nssm\\" + (is64bit ? "win64" : "win32") + "\\nssm.exe";
        String logsPath = basePath + "\\logs";
        String servicoNome = "TokenService";

        Path logsDir = Paths.get(logsPath);
        if (Files.notExists(logsDir)) Files.createDirectories(logsDir);

        System.out.println("\nExecutando instalação do serviço...");
        String[] installCmd = { nssmPath, "install", servicoNome, javaPath };
        String[] setParamsCmd = {
                nssmPath, "set", servicoNome, "AppParameters",
                String.format("-jar \"%s\" %s \"%s\" \"%s\"", jarPath, cnpj, basePath, token)
        };
        String[] setDirCmd = { nssmPath, "set", servicoNome, "AppDirectory", basePath };
        String[] setOutCmd = { nssmPath, "set", servicoNome, "AppStdout", logsPath + "\\stdout.log" };
        String[] setErrCmd = { nssmPath, "set", servicoNome, "AppStderr", logsPath + "\\stderr.log" };
        String[] setRotateCmd = { nssmPath, "set", servicoNome, "AppRotateFiles", "1" };
        String[] setEnvCmd = { nssmPath, "set", servicoNome, "AppEnvironmentExtra", "-Xms512m -Xmx1024m" };
        String[] setAutoCmd = { nssmPath, "set", servicoNome, "Start", "SERVICE_AUTO_START" };
        String[] startCmd = { nssmPath, "start", servicoNome };

        executarProcesso(installCmd, "Instalando serviço...");
        executarProcesso(setParamsCmd, "Configurando parâmetros...");
        executarProcesso(setDirCmd, "Configurando diretório...");
        executarProcesso(setOutCmd, "Configurando saída padrão...");
        executarProcesso(setErrCmd, "Configurando saída de erro...");
        executarProcesso(setRotateCmd, "Ativando rotação de logs...");
        executarProcesso(setEnvCmd, "Configurando variáveis de ambiente...");
        executarProcesso(setAutoCmd, "Definindo inicialização automática...");
        executarProcesso(startCmd, "Iniciando serviço...");

        System.out.println("\nServiço '" + servicoNome + "' instalado e iniciado com sucesso!");
    }

    private static void executarProcesso(String[] comando, String descricao) {
        try {
            System.out.println(descricao);
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.inheritIO();
            Process p = pb.start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Falha ao executar: " + String.join(" ", comando));
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao executar comando: " + e.getMessage());
        }
    }
    
    private static void limparTela() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Não foi possível limpar a tela: " + ex.getMessage());
        }
    }   
}