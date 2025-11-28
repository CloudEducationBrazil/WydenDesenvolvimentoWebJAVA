package com.conttroller.tokencontabilidade.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.conttroller.tokencontabilidade.dto.TokenJsonDTO;
import com.conttroller.tokencontabilidade.enums.SistemaToken;
import com.conttroller.tokencontabilidade.enums.StatusToken;
import com.conttroller.tokencontabilidade.responses.MessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class TokenService {
	private final ObjectMapper mapper = new ObjectMapper();
	
	/*
	@Scheduled(fixedRate = 60000)	A cada 60 segundos (1 min)
	@Scheduled(fixedDelay = 60000)	60 segundos após o fim da última execução
	@Scheduled(cron = "0 0 * * * *")	Todo início de hora
	@Scheduled(cron = "0 0 2 * * *")	Todos os dias às 2h
	*/
	
	@Scheduled(cron = "0 0 2 * * *") // todos os dias às 02:00
	public void tarefaAgendadaParaVerificarToken() {
	    String caminho = "C:\\conttrol\\token.json";

	    try {
	        System.out.println("Executando tarefa agendada para verificar token...");
	        carregarJSON(caminho);
	    } catch (IOException e) {
	        System.err.println("Erro na tarefa agendada: " + e.getMessage());
	    }
	}
	
    public void carregarJSON(String caminhoArquivo) throws IOException {
    	try {
            File arquivo = new File(caminhoArquivo);
            
            if (!arquivo.exists()) {
                throw new IOException("Arquivo JSON não encontrado: " + caminhoArquivo);
            }
		} catch (Exception e) {
			e.printStackTrace(); 
		}
    }

    //Atualizar o campo "status": SISTEMAS LIBERADO + STATUS + DATA LIMITE
    public void atualizarStatus(File arquivo, StatusToken novoStatus) throws IOException {
        
    	if (!arquivo.exists()) {
            throw new IOException("Arquivo não encontrado: " + arquivo.getAbsolutePath());
        }

        // Habilita suporte a LocalDate
        mapper.registerModule(new JavaTimeModule());

        // Lê o DTO
        TokenJsonDTO dto = mapper.readValue(arquivo, TokenJsonDTO.class);

        // Atualiza o status
        dto.setStatus(novoStatus);

        // Grava o DTO de volta no arquivo
        mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, dto);

        //System.out.println("Status atualizado com sucesso para: " + novoStatus);
    }
    
	// (envia JSON para criar o token)
	/*POST http://localhost:8585/api/tokencontabilidade
		{
		  "id": 41720075,
		  "sistema": ["fiscal", "contábil", "folha"],
		  "datalimite": "2025-12-31",
		  "status": "ativo"
		}	*/
    
    public ResponseEntity<?> createTokenDTO() {
    	
   	 try {
 	    //String id = "41720075";
 	    String id = "40040470";
    	    
        	TokenJsonDTO token = new TokenJsonDTO(
                    id,
                    List.of(SistemaToken.FISCAL), //SistemaToken.CONTABIL, SistemaToken.FOLHA
                    LocalDate.of(2025, 12, 31),
                    StatusToken.ATIVO
            );

        	System.out.println(token);
         
     	 } catch (Exception e) {
     	      e.printStackTrace(); // Exibe o erro no log
     	      System.err.println("Erro durante a execução do CommandLineRunner: " + e.getMessage());
     	 }        
   	
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("JSON criado com sucesso."));
    }    
}