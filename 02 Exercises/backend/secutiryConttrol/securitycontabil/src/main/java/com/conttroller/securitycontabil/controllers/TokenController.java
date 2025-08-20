package com.conttroller.securitycontabil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;
import com.conttroller.securitycontabil.dto.TokenRetornoApiContabilidadeDTO;
import com.conttroller.securitycontabil.services.TokenService;

@RestController
@RequestMapping(value="/api/tokencontabilidade")
public class TokenController {
	// https://conttroller.net/api/d3/token
    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<TokenRetornoApiContabilidadeDTO> postTokenContabilidade(
        @RequestBody(required = false) TokenEnvioApiContabilidadeDTO body) {

        if (body == null) {
            body = new TokenEnvioApiContabilidadeDTO("04585532000199", "");
        }

        return tokenService.postTokenContabilidade(body);
    }
}