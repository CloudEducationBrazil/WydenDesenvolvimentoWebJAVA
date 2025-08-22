package com.conttroller.securitycontabil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import feign.FeignException;
import jakarta.annotation.PostConstruct;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;
import com.conttroller.securitycontabil.dto.TokenRetornoApiContabilidadeDTO;
import com.conttroller.securitycontabil.dto.TokenRetornoApiContabilidadeMinDTO;
import com.conttroller.securitycontabil.interfaces.TokenClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

@Service
public class TokenService {
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

	@Autowired
	private TokenClient tokenClient;
	
	@Autowired
	private ObjectMapper objectMapper;
	
    @Value("${token.salvar.caminho}")
    private String pasta;
    
    @PostConstruct
    public void init() {
        new File(pasta).mkdirs();
        logger.info(">>> Diretório de salvamento verificado/criado: {}", pasta);
    }    
	
	public ResponseEntity<TokenRetornoApiContabilidadeDTO> postTokenContabilidade(TokenEnvioApiContabilidadeDTO body) {

		if (body.getCnpj() == null || body.getCnpj().isBlank()) {
		    logger.warn("CNPJ inválido no corpo da requisição.");
		    return ResponseEntity.badRequest().build();
		}
		
		TokenRetornoApiContabilidadeDTO dtoCompleto = null;
		
	    try {
			logger.info(">>> Enviando JSON para API externa: {}", objectMapper.writeValueAsString(body));

			 // Desserializa o corpo da resposta JSON em um objeto DTO
			 dtoCompleto = tokenClient.postToken(body);
			
			// Mapeia para o DTO reduzido
			TokenRetornoApiContabilidadeMinDTO dtoMin = mapearParaMinDTO(dtoCompleto);
		
			// Salva o JSON em disco
			salvarTokenEmDisco(dtoCompleto, dtoMin);

			logDetalhesDto(dtoCompleto);
			
		} catch (FeignException e) {
	        logger.error(">>> Erro ao chamar API externa: Erro de cliente HTTP: {}", e.getMessage(), e);
	        return ResponseEntity.status(e.status()).build();
	        
	    } catch (JsonProcessingException e) {
	        logger.error(">>> Erro ao chamar API externa: Erro ao processar JSON: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		} catch (Exception e) {
		    logger.error(">>> Erro inesperado ao chamar API ou processar resposta: {}", e.getMessage(), e);
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	    // Retorna o objeto DTO diretamente
		return ResponseEntity.ok(dtoCompleto);
	}

	private TokenRetornoApiContabilidadeMinDTO mapearParaMinDTO(TokenRetornoApiContabilidadeDTO dtoCompleto) {
		TokenRetornoApiContabilidadeMinDTO dtoMin = new TokenRetornoApiContabilidadeMinDTO();
		
		dtoMin.setId(dtoCompleto.getId());
		dtoMin.setCnpj(dtoCompleto.getCnpj());
		dtoMin.setValidade(dtoCompleto.getValidade());
		dtoMin.setAssinatura(dtoCompleto.getAssinatura());		

		// Mapeia dados dos sistemas (fiscal, contábil, folha)
		if (dtoCompleto.getServidor() != null && dtoCompleto.getServidor().getSistemas() != null) {
			dtoCompleto.getServidor().getSistemas().forEach(sistema -> {
				String nome = sistema.getSistema();
				if (sistema.getModulos() != null && !sistema.getModulos().isEmpty()) {
					var settings = sistema.getModulos().get(0).getSettings();
					if (settings != null) {
						TokenRetornoApiContabilidadeMinDTO.SistemaDTO sistemaMin = new TokenRetornoApiContabilidadeMinDTO.SistemaDTO();
						sistemaMin.setStatusFin(settings.getStatusFin());
						sistemaMin.setHabilitado(settings.getHabilitado());

						switch (nome.toUpperCase()) {
							case "FISCAL" -> dtoMin.setFiscal(sistemaMin);
							case "CONTABIL" -> dtoMin.setContabil(sistemaMin);
							case "FOLHA" -> dtoMin.setFolha(sistemaMin);
						}
					}
				}
			});
		}
		return dtoMin;
	}

	private void logDetalhesDto(TokenRetornoApiContabilidadeDTO dtoCompleto) {
		if (dtoCompleto.getServidor() != null) {
			logger.info("Token gerado com sucesso para CNPJ: {}", dtoCompleto.getCnpj());
		    logger.info(">>> Lista de sistemas: {}", dtoCompleto.getServidor().getSistemas());
			logger.info(">>> Servidor DTO recebido: {}", dtoCompleto.getServidor());
			logger.info(">>> DTO recebido: {}", dtoCompleto);
		}
		
	    if (dtoCompleto.getServidor().getSistemas() != null) {
	    	dtoCompleto.getServidor().getSistemas().forEach(sistema -> {
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
	
	private void salvarTokenEmDisco(TokenRetornoApiContabilidadeDTO completo, TokenRetornoApiContabilidadeMinDTO reduzido)  throws IOException {
		// String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String arqOriginal = String.format("%stoken_contabilidade_ori_%s.json", pasta, completo.getCnpj());
	    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(arqOriginal), completo);

	    String arqReduzido = String.format("%stoken_contabilidade_red_%s.json", pasta, completo.getCnpj());
	    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(arqReduzido), reduzido);
	    
		logger.info(">>> JSON salvo com sucesso em: {}", arqOriginal);					
		logger.info(">>> JSON salvo com sucesso em: {}", arqReduzido);					
	}	
}