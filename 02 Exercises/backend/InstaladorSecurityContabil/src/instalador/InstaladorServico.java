package instalador;

import java.io.*;
import java.nio.file.*;

public class InstaladorServico {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("=========================================");
            System.out.println("== Instalador de Serviço Token Contábil ==");
            System.out.println("=========================================\n");

            // Solicita drive
            System.out.print("Digite a unidade onde o sistema está instalado (ex: C ou D): ");
            String drive = reader.readLine().trim().toUpperCase();
            if (!drive.matches("^[A-Z]$")) {
                System.err.println("Unidade inválida! Encerrando instalação.");
                return;
            }

            // Solicita CNPJ
            System.out.print("Digite o CNPJ da empresa (somente números): ");
            String cnpj = reader.readLine().trim();
            if (!cnpj.matches("^\\d{14}$")) {
                System.err.println("CNPJ inválido! Deve conter 14 números.");
                return;
            }

            // Solicita TOKEN
            System.out.print("Digite o TOKEN de autenticação: ");
            String token = reader.readLine().trim();
            if (token.isEmpty()) {
                System.err.println("Token não pode ser vazio! Encerrando instalação.");
                return;
            }

            // Detecta arquitetura do sistema
            String arch = System.getenv("PROCESSOR_ARCHITECTURE");
            String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
            boolean is64bit = (arch != null && arch.endsWith("64")) || (wow64Arch != null && wow64Arch.endsWith("64"));

            // Define caminhos
            String basePath = drive + ":\\CONTTROL";
            String javaPath = basePath + "\\java\\bin\\java.exe";
            String jarPath = basePath + "\\securitycontabil.jar";
            String nssmPath = basePath + "\\nssm\\" + (is64bit ? "win64" : "win32") + "\\nssm.exe";
            String logsPath = basePath + "\\logs";
            String servicoNome = "TokenService";

            System.out.println("\n=========================================");
            System.out.println("Configurações detectadas:");
            System.out.println("Arquitetura do SO: " + (is64bit ? "64 bits" : "32 bits"));
            System.out.println("Drive: " + drive + ":");
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Token: " + token);
            System.out.println("JVM: " + javaPath);
            System.out.println("JAR: " + jarPath);
            System.out.println("NSSM: " + nssmPath);
            System.out.println("=========================================\n");

            // Cria pasta de logs
            Path logsDir = Paths.get(logsPath);
            if (Files.notExists(logsDir)) {
                Files.createDirectories(logsDir);
                System.out.println("Pasta de logs criada em: " + logsDir.toAbsolutePath());
            }

            // Valida existência de arquivos necessários
            if (!Files.exists(Paths.get(javaPath))) {
                System.err.println("Java não encontrado em: " + javaPath);
                return;
            }
            if (!Files.exists(Paths.get(jarPath))) {
                System.err.println("JAR não encontrado em: " + jarPath);
                return;
            }
            if (!Files.exists(Paths.get(nssmPath))) {
                System.err.println("NSSM não encontrado em: " + nssmPath);
                return;
            }

            // Comandos NSSM
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

            // Executa comandos
            executar(installCmd, "Instalando serviço...");
            executar(setParamsCmd, "Configurando parâmetros...");
            executar(setDirCmd, "Configurando diretório...");
            executar(setOutCmd, "Configurando saída padrão...");
            executar(setErrCmd, "Configurando saída de erro...");
            executar(setRotateCmd, "Ativando rotação de logs...");
            executar(setEnvCmd, "Configurando variáveis de ambiente...");
            executar(setAutoCmd, "Definindo inicialização automática...");
            executar(startCmd, "Iniciando serviço...");

            System.out.println("\n=========================================");
            System.out.println("Serviço '" + servicoNome + "' instalado e iniciado com sucesso!");
            System.out.println("=========================================");

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro durante instalação: " + e.getMessage());
        }
    }

    private static void executar(String[] comando, String descricao)
            throws IOException, InterruptedException {
        System.out.println(descricao);
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.inheritIO();
        Process p = pb.start();
        int exitCode = p.waitFor();
        if (exitCode != 0) {
            throw new IOException("Falha ao executar: " + String.join(" ", comando));
        }
    }
}