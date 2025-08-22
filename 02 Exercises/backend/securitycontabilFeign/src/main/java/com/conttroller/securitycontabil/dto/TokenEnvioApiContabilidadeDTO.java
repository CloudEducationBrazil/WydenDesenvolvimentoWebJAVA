package com.conttroller.securitycontabil.dto;

public class TokenEnvioApiContabilidadeDTO {

    private String cnpj;
    private String password;
    
    public TokenEnvioApiContabilidadeDTO() {}
    
    public TokenEnvioApiContabilidadeDTO(String cnpj, String password) {
		this.cnpj = cnpj;
		this.password = password;
	}

	// Getters e Setters
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
	
    @Override
    public String toString() {
        return "EmpresaDTO{cnpj='" + cnpj + "'}";
    }
}