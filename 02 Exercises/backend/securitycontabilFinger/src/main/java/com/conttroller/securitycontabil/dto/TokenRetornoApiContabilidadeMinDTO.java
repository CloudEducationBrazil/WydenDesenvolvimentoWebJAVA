package com.conttroller.securitycontabil.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.conttroller.securitycontabil.config.OffsetDateTimeWithoutOffsetDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenRetornoApiContabilidadeMinDTO {

	//@NotBlank
    private String id;

	//@NotBlank
    private String cnpj;
    
    @JsonDeserialize(using = OffsetDateTimeWithoutOffsetDeserializer.class)
    private OffsetDateTime validade;
    
    private String assinatura;
    
    private SistemaDTO fiscal;
    private SistemaDTO contabil;
    private SistemaDTO folha;
    
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

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

	public SistemaDTO getFiscal() {
		return fiscal;
	}

	public void setFiscal(SistemaDTO fiscal) {
		this.fiscal = fiscal;
	}

	public SistemaDTO getContabil() {
		return contabil;
	}

	public void setContabil(SistemaDTO contabil) {
		this.contabil = contabil;
	}

	public SistemaDTO getFolha() {
		return folha;
	}

	public void setFolha(SistemaDTO folha) {
		this.folha = folha;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
    public static class SistemaDTO {
        private String statusFin;
        private String habilitado;
        
		public String getStatusFin() {
			return statusFin;
		}
		public void setStatusFin(String statusFin) {
			this.statusFin = statusFin;
		}
		public String getHabilitado() {
			return habilitado;
		}
		public void setHabilitado(String habilitado) {
			this.habilitado = habilitado;
		}
		
		@Override
		public String toString() {
		    return "SistemaDTO{" +
		            "statusFin='" + statusFin + '\'' +
		            ", habilitado='" + habilitado + '\'' +
		            '}';
		}		
    }

	@Override
	public String toString() {
	    return "TokenRetornoApiContabilidadeMinDTO{" +
	            "id='" + id + '\'' +
	            ", cnpj='" + cnpj + '\'' +
	            ", validade=" + validade +
	            ", assinatura='" + assinatura + '\'' +
	            ", fiscal=" + fiscal +
	            ", contabil=" + contabil +
	            ", folha=" + folha +
	            '}';
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
		TokenRetornoApiContabilidadeMinDTO other = (TokenRetornoApiContabilidadeMinDTO) obj;
		return Objects.equals(id, other.id);
	}
}