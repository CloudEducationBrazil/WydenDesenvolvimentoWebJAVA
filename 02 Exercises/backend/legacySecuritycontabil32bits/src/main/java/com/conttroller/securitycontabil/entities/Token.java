package com.conttroller.securitycontabil.entities;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_token",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cnpj", "sistema"})
    }
)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 14, nullable = false)
    private String cnpj;
    private OffsetDateTime validade;

    //@Column(name = "sistema", unique = true)
    @Column(length = 50)
    private String sistema;
    
    @Column(length = 1)
    private String habilitado;
    
    @Column(length = 1)
    private String financeiro;

    public Token() {}

    public Token(String cnpj, OffsetDateTime validade, String sistema, String habilitado, String financeiro) {
    	this.cnpj = cnpj;
    	this.validade = validade;
    	this.sistema = sistema;
        this.habilitado = habilitado;
        this.financeiro = financeiro;
    }

    public Long getId() { return id; }
   
    public String getCnpj() { return cnpj; }
    public OffsetDateTime getValidade() { return validade; }
    public String getSistema() { return sistema; }
    public String getHabilitado() { return habilitado; }
    public String getFinanceiro() { return financeiro; }

	public void setId(Long id) {
		this.id = id;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public void setValidade(OffsetDateTime validade) {
		this.validade = validade;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public void setFinanceiro(String financeiro) {
		this.financeiro = financeiro;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", cnpj =" + cnpj + ", sistema=" + sistema + ", habilitado=" + habilitado + ", financeiro=" + financeiro
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(id, other.id);
	}
}