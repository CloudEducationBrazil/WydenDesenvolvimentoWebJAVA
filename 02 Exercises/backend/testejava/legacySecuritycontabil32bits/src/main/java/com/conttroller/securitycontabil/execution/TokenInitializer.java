package com.conttroller.securitycontabil.execution;

import com.conttroller.securitycontabil.services.TokenExecutorService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@org.springframework.context.annotation.Profile("!first") // só roda se o profile NÃO for "first"
public class TokenInitializer {

    private static final Logger logger = LoggerFactory.getLogger(TokenInitializer.class);

    private final TokenExecutorService tokenExecutorService;
    private boolean tokensCarregados = false;

    @Value("${token.api.url}")
    private String tokenApiUrl;

    public TokenInitializer(TokenExecutorService tokenExecutorService) {
        this.tokenExecutorService = tokenExecutorService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public synchronized void initTokens() {
        if (tokensCarregados) return;

        if (tokenExecutorService.isStorageEmpty()) {
            tokenExecutorService.restaurarTokensDoRegistro();
        }

        try {
            logger.info("[TOKEN INIT] === Iniciando carregamento automático de tokens ===");

            ConexaoStatus status = verificarConexaoComRetry(3);

/*            switch (status) {
                case ONLINE_OK -> {
                    logger.info("[TOKEN INIT][ONLINE_OK] Conexão detectada: buscando tokens na API...");
                    tokenExecutorService.executarTokenReal();
                }
                case ONLINE_ENDPOINT_INDISPONIVEL -> {
                    logger.warn("[TOKEN INIT][ENDPOINT_4XX] Conexão com internet ok, mas o endpoint está indisponível. Restaurando tokens locais...");
                    tokenExecutorService.restaurarTokensDoRegistro();
                }
                case SEM_INTERNET -> {
                    logger.warn("[TOKEN INIT][NO_INTERNET] Sem conexão com a internet ou falha no servidor. Restaurando tokens locais...");
                    tokenExecutorService.restaurarTokensDoRegistro();
                }
            }
*/
            switch (status) {
            case ONLINE_OK:
                logger.info("[TOKEN INIT][ONLINE_OK] Conexão detectada: buscando tokens na API...");
                tokenExecutorService.executarTokenReal();
                break;

            case ONLINE_ENDPOINT_INDISPONIVEL:
                logger.warn("[TOKEN INIT][ENDPOINT_4XX] Conexão com internet ok, mas o endpoint está indisponível. Restaurando tokens locais...");
                tokenExecutorService.restaurarTokensDoRegistro();
                break;

            case SEM_INTERNET:
                logger.warn("[TOKEN INIT][NO_INTERNET] Sem conexão com a internet ou falha no servidor. Restaurando tokens locais...");
                tokenExecutorService.restaurarTokensDoRegistro();
                break;

            default:
                // opcional, se quiser garantir robustez
                logger.warn("[TOKEN INIT] Status desconhecido: " + status);
                tokenExecutorService.restaurarTokensDoRegistro();
                break;
            } 
            
            tokensCarregados = true;
            logger.info("[TOKEN INIT][OK] Tokens carregados e processados com sucesso.");

        } catch (Exception e) {
            logger.error("[TOKEN INIT][FAIL] Falha ao carregar/processar tokens: {}", e.getMessage(), e);
        }
    }

    private enum ConexaoStatus {
        ONLINE_OK,
        ONLINE_ENDPOINT_INDISPONIVEL,
        SEM_INTERNET
    }

    private ConexaoStatus verificarConexao() {
        String url = tokenApiUrl;
        HttpClient client = HttpClient.newBuilder()
                                      .connectTimeout(Duration.ofSeconds(3))
                                      .build();

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .timeout(Duration.ofSeconds(3))
                                         .GET()
                                         .build();

        try {
            long start = System.currentTimeMillis();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            long duration = System.currentTimeMillis() - start;
            int status = response.statusCode();

            if (status == 200) {
                logger.info("[TOKEN INIT][ONLINE_OK] Ping bem-sucedido para {} ({} ms)", url, duration);
                return ConexaoStatus.ONLINE_OK;
            } else if (status >= 500) {
                logger.warn("[TOKEN INIT][NO_INTERNET] Ping falhou com status {} (erro de servidor remoto, {} ms).", status, duration);
                return ConexaoStatus.SEM_INTERNET;
            } else {
                logger.warn("[TOKEN INIT][ENDPOINT_4XX] Ping retornou status {} ({} ms). Conexão existe, mas recurso não disponível.", status, duration);
                return ConexaoStatus.ONLINE_ENDPOINT_INDISPONIVEL;
            }

        } catch (Exception e) {
            logger.warn("[TOKEN INIT][NO_INTERNET] Falha ao testar conexão com {}: {}", url, e.getMessage());
            return ConexaoStatus.SEM_INTERNET;
        }
    }

    private ConexaoStatus verificarConexaoComRetry(int tentativas) {
        for (int i = 1; i <= tentativas; i++) {
            ConexaoStatus status = verificarConexao();
            if (status == ConexaoStatus.ONLINE_OK) {
                return status;
            } else {
                logger.info("[TOKEN INIT] Tentativa {} de {} falhou, aguardando 1s antes da próxima...", i, tentativas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }
        logger.warn("[TOKEN INIT] Todas as tentativas de conexão falharam. Usando tokens locais se disponíveis.");
        return ConexaoStatus.SEM_INTERNET;
    }
}