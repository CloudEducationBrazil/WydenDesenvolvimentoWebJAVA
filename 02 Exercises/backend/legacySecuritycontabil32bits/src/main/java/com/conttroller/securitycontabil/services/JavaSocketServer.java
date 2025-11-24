package com.conttroller.securitycontabil.services;

import com.conttroller.securitycontabil.entities.Token;
import com.conttroller.securitycontabil.repositories.TokenRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

@Service
public class JavaSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(TokenExecutorService.class);

    private ServerSocket serverSocket;
    private final int porta = 8587;
    private final TokenRepository tokenRepository;

    public JavaSocketServer(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    
    @Autowired
    private AppContextService appContextService;    

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(porta, 50, null);
                //logger.info("Socket rodando na porta " + porta);

                while (!serverSocket.isClosed()) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        new Thread(() -> handleClient(clientSocket)).start();
                    } catch (IOException e) {
                        if (!serverSocket.isClosed()) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (IOException e) {
            	logger.error("Não foi possível abrir a porta " + porta + ": " + e.getMessage());
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String command = in.readLine();
            logger.info("Comando recebido: " + command);

            switch (command.toUpperCase()) {
                case "GET_FISCAL":
                    enviarToken("FISCAL", out);
                    break;
                case "GET_CONTABIL":
                    enviarToken("CONTABIL", out);
                    break;
                case "GET_FOLHA":
                    enviarToken("FOLHA", out);
                    break;
                default:
                    out.println("COMANDO DESCONHECIDO");
            }

            out.println("END");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { clientSocket.close(); } catch (IOException ignored) {}
        }
    }
    
    @PreDestroy
    public void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                logger.info("Socket fechado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarToken(String sistema, PrintWriter out) {
        Optional<Token> optToken = tokenRepository.findByCnpjAndSistema(appContextService.getCnpj(), sistema);

        if (optToken.isPresent()) {
            Token token = optToken.get();
            out.println(token.getSistema() + ";" + token.getValidade() + ";" 
                        + token.getHabilitado() + ";" + token.getFinanceiro());
        } else {
            out.println("TOKEN NÃO ENCONTRADO");
        }
    }
}