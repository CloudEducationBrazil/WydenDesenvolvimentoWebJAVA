package com.conttroller.securitycontabil.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppContextService {
	private String cnpj;

	private String empresa;
	
    @Value("${sistema.regPath:}")
	private String regPath;

    @Value("${caminho.default:}")
	private File caminho;
    
    private String inputToken;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
    	if (cnpj != null && !cnpj.isBlank()) {
           this.cnpj = cnpj;
    	}
    }
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
    	if (empresa != null && !empresa.isBlank()) {
           this.empresa = empresa;
    	}
    }

	public File getCaminho() {
		return caminho;
	}

	public void setCaminho(File caminho) {
		if (caminho != null) {
			this.caminho = caminho;
		}
	}

	public String getRegPath() {
		return regPath;
	}

	public void setRegPath(String regPath) {
		if (regPath != null) {
		    this.regPath = regPath;
		}
	}
	
    public String getInputToken() {
        return inputToken;
    }

    public void setInputToken(String inputToken) {
        if (inputToken != null && !inputToken.isBlank()) {
            this.inputToken = inputToken;
        }
    }	
}