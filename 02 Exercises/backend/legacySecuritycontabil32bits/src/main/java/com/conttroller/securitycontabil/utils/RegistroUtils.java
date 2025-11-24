	package com.conttroller.securitycontabil.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.conttroller.securitycontabil.services.AppExecutionService;
import com.conttroller.securitycontabil.storage.TokenStorage;

@Component
public class RegistroUtils {
	
    private static final Logger logger = LoggerFactory.getLogger(AppExecutionService.class);
    
    private static final String TOKEN_KEY = "nekot";
    
    @Value("${dev.mode:false}")
    private boolean devMode;    

    private final TokenStorage tokenStorage;

    public RegistroUtils(TokenStorage tokenStorage) {
        this.tokenStorage = tokenStorage;
    }

    public boolean verificarTokenExistente() {
    	
        if (devMode) {
            logger.info("Modo DEV ativo: pulando verificação de token.");
            return true;
        }
        
        try {
            return tokenStorage.load(TOKEN_KEY).isPresent();
        } catch (IOException e) {
            logger.error("Erro ao acessar registro do Windows: " + e.getMessage());
            return false;
        }
    }
}