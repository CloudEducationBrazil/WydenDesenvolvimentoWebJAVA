package com.conttroller.securitycontabil.services;

import org.springframework.stereotype.Service;

@Service
public class AppContextService {
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}