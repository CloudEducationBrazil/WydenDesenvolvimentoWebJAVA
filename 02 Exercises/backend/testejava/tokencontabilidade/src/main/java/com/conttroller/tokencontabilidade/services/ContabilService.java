package com.conttroller.tokencontabilidade.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.conttroller.tokencontabilidade.dto.TokenDTO;

@Service
public class ContabilService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> postTokenContabilidade(TokenDTO body) {
        String url = "https://conttroller.net/api/d3/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TokenDTO> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao chamar o serviço externo: " + e.getMessage());
        }
    }
}