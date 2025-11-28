package com.conttroller.securitycontabil.dto;

import java.io.File;

public class TokenEnvioApiContabilidadeDTO {

    private String cnpj;
    private String password;
    private File caminho;
    
    public TokenEnvioApiContabilidadeDTO() {}
    
    public TokenEnvioApiContabilidadeDTO(String cnpj, String password, File caminho) {
		this.cnpj = cnpj;
		this.password = password;
		this.caminho = caminho;
	}

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
    public File getCaminho() {
        return caminho;
    }

    public void setCaminho(File caminho) {
        this.caminho = caminho;
    }
	
    @Override
    public String toString() {
        return "TokenEnvioApiContabilidadeDTO{" +
                "cnpj='" + cnpj + '\'' +
                ", password='" + password + '\'' +
                ", caminho=" + (caminho != null ? caminho.getAbsolutePath() : "null") +
                '}';
    }
}