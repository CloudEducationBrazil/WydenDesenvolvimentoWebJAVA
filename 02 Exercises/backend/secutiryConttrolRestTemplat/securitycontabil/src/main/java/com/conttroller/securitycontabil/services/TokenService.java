package com.conttroller.securitycontabil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;
import com.conttroller.securitycontabil.dto.TokenRetornoApiContabilidadeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Service
public class TokenService {
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public ResponseEntity<TokenRetornoApiContabilidadeDTO> postTokenContabilidade(TokenEnvioApiContabilidadeDTO body) { //throws JsonProcessingException {
		String url = "https://conttroller.net/api/d3/token";

		HttpHeaders headers = new HttpHeaders();
		
	    try {
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<TokenEnvioApiContabilidadeDTO> request = new HttpEntity<>(body, headers);

			//System.out.println(new ObjectMapper().writeValueAsString(body));
			logger.info(">>> Enviando JSON para API externa: {}", objectMapper.writeValueAsString(body));

			System.out.println(">>> Enviando JSON para API externa:");
	        System.out.println(objectMapper.writeValueAsString(body));
	        
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			
			 // Desserializa o corpo da resposta JSON em um objeto DTO
			TokenRetornoApiContabilidadeDTO dto = objectMapper.readValue(response.getBody(), TokenRetornoApiContabilidadeDTO.class);
			// Caminho para salvar o arquivo
			String caminhoArquivo = "C:/conttrol/token_contabilidade_" + dto.getCnpj() + ".json";

			// Salva o JSON em disco
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminhoArquivo), dto);
			logger.info(">>> JSON salvo com sucesso em: {}", caminhoArquivo);					
			
			//System.out.println(">>> DTO recebido:");
			//System.out.println(dto);
			logger.info(">>> Servidor DTO recebido: {}", dto.getServidor());
			if (dto.getServidor() != null) {
			    logger.info(">>> Lista de sistemas: {}", dto.getServidor().getSistemas());
			    
			    if (dto.getServidor().getSistemas() != null) {
			        dto.getServidor().getSistemas().forEach(sistema -> {
			            logger.info(">>> Sistema: {}", sistema);
			            if (sistema.getModulos() != null) {
			                sistema.getModulos().forEach(modulo -> {
			                    logger.info(">>> Módulo: {}", modulo);
			                    logger.info(">>> Settings do módulo: {}", modulo.getSettings());
			                });
			            }
			        });
			    }
			}
			
			logger.info(">>> DTO recebido: {}", dto);
			
			// Retorna o objeto DTO diretamente
			return ResponseEntity.status(response.getStatusCode()).body(dto);
			//return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
			
		} catch (Exception e) {
			logger.error(">>> Erro ao chamar API externa ou ao desserializar resposta JSON", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}