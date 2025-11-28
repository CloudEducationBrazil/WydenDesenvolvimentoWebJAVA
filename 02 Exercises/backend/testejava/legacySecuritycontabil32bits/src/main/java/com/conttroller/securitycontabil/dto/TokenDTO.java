package com.conttroller.securitycontabil.dto;

import com.conttroller.securitycontabil.entities.Token;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

import org.springframework.beans.BeanUtils;

public class TokenDTO {
    private String id;
    private String cnpj;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime validade;
    
    private String sistema;
    private String habilitado;
    private String financeiro;
    
    public TokenDTO() {
	}
    
    public TokenDTO(Token entity) {
    	BeanUtils.copyProperties(entity, this);
	}

    public String getId() {
		return id;
	}

    public void setId(String id) {
		this.id = id;
	}

    public String getCnpj() {
		return cnpj;
	}

    public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

    public OffsetDateTime getValidade() {
		return validade;
	}

    public void setValidade(OffsetDateTime validade) {
		this.validade = validade;
	}

    public String getSistema() {
		return sistema;
	}

    public void setSistema(String sistema) {
		this.sistema = sistema;
	}

    public String getHabilitado() {
		return habilitado;
	}

    public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

    public String getFinanceiro() {
		return financeiro;
	}

    public void setFinanceiro(String financeiro) {
		this.financeiro = financeiro;
	}    
}