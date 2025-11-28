package com.conttroller.securitycontabil.services;

import com.conttroller.securitycontabil.entities.Token;
import com.conttroller.securitycontabil.repositories.TokenRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.Objects;

@Service
public class TokenPersistenceService {

    private final TokenRepository tokenRepository;

    public TokenPersistenceService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /*
    @Transactional
    public void salvarOuAtualizar(Token novoToken) {
        Optional<Token> optionalExistente = tokenRepository.findByCnpjAndSistemaForUpdate(
                novoToken.getCnpj(),
                novoToken.getSistema()
        );

        Token existente = optionalExistente.orElse(new Token());
        if (optionalExistente.isEmpty()) {
            existente.setCnpj(novoToken.getCnpj());
            existente.setSistema(novoToken.getSistema());
        }

        boolean mudou = false;
        if (!novoToken.getFinanceiro().equals(existente.getFinanceiro())) {
            existente.setFinanceiro(novoToken.getFinanceiro());
            mudou = true;
        }
        if (!novoToken.getHabilitado().equals(existente.getHabilitado())) {
            existente.setHabilitado(novoToken.getHabilitado());
            mudou = true;
        }
        if (!novoToken.getValidade().equals(existente.getValidade())) {
            existente.setValidade(novoToken.getValidade());
            mudou = true;
        }

        if (mudou || optionalExistente.isEmpty()) {
            tokenRepository.save(existente);
        }
    }
*/
    // 24/10/2025 Heleno
    @Transactional
    public void salvarOuAtualizar(Token novoToken) {
        Optional<Token> optionalExistente = tokenRepository.findByCnpjAndSistemaForUpdate(
                novoToken.getCnpj(),
                novoToken.getSistema()
        );

        Token existente = optionalExistente.orElse(new Token());
        if (optionalExistente.isEmpty()) {
            existente.setCnpj(novoToken.getCnpj());
            existente.setSistema(novoToken.getSistema());
        }

        boolean mudou = false;
        if (!Objects.equals(novoToken.getFinanceiro(), existente.getFinanceiro())) {
            existente.setFinanceiro(novoToken.getFinanceiro());
            mudou = true;
        }
        
        if (!Objects.equals(novoToken.getHabilitado(), existente.getHabilitado())) {
            existente.setHabilitado(novoToken.getHabilitado());
            mudou = true;
        }
        
        if (!Objects.equals(novoToken.getValidade(), existente.getValidade())) {
            existente.setValidade(novoToken.getValidade());
            mudou = true;
        }

        if (mudou || optionalExistente.isEmpty()) {
            tokenRepository.save(existente);
        }
    }    
    // 24/10/2025 Heleno
    
    public boolean isH2Vazio() {
        return tokenRepository.count() == 0;
    }

    public List<Token> listarTodos() {
        return tokenRepository.findAll();
    }
}