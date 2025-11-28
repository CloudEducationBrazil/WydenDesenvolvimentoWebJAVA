package com.conttroller.securitycontabil.controllers;

import org.springframework.web.bind.annotation.*;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;
import com.conttroller.securitycontabil.dto.TokenRetornoApiContabilidadeDTO;
import com.conttroller.securitycontabil.services.TokenService;

@RestController
@RequestMapping("/api/tokencontabilidade")
public class TokenController {

//    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public TokenRetornoApiContabilidadeDTO postTokenContabilidade(
            @RequestBody(required = false) TokenEnvioApiContabilidadeDTO body) throws Exception {

        //logger.warn("Camada Controller caminho: " + body);
        
        // Se body não for enviado
        if (body == null) {
        	body = new TokenEnvioApiContabilidadeDTO(
                    "00000000000199",  // CNPJ padrão
                    "",                 // Senha padrão (será preenchida no TokenService High Commerce)
                    null // Caminho
            );
        }

        return tokenService.postTokenContabilidade(body);
    }
}