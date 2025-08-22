package com.conttroller.securitycontabil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.conttroller.securitycontabil.dto.TokenEnvioApiContabilidadeDTO;

@Service
public class TokenScheduler {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppContextService contextService;

    // Executa a cada 3 horas (10,800,000 ms)
    // Executa a cada 2 min (120,000 ms)
    @Scheduled(fixedRate = 120000) // 10800000
    public void executarConsulta() {
        String cnpj = contextService.getCnpj();
        
        if (cnpj != null && !cnpj.isEmpty()) {
            System.out.println("Executando tarefa agendada para o CNPJ: " + cnpj);
            tokenService.postTokenContabilidade(new TokenEnvioApiContabilidadeDTO(cnpj, ""));
        } 
        //else {
        //    System.err.println("CNPJ não configurado. Ignorando execução.");
        //}
    }
}