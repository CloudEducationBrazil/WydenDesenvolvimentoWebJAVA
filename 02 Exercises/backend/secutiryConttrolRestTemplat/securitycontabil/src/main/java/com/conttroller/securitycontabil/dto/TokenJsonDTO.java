package com.conttroller.securitycontabil.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.conttroller.securitycontabil.enums.SistemaToken;
import com.conttroller.securitycontabil.enums.StatusToken;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenJsonDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("sistema")
	private List<SistemaToken> sistema; // ["fiscal", "contábil", "folha"]
	
	@JsonProperty("datalimite")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate datalimite;
	
	@JsonProperty("status")
	private StatusToken status;

	public TokenJsonDTO() {}

	public TokenJsonDTO(String id, List<SistemaToken> sistema, LocalDate datalimite, StatusToken status) {
		this.id = id;
		this.sistema = sistema;
		this.datalimite = datalimite;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SistemaToken> getSistema() {
		return sistema;
	}

	public void setSistema(List<SistemaToken> sistema) {
		this.sistema = sistema;
	}

	public LocalDate getDatalimite() {
		return datalimite;
	}

	public void setDatalimite(LocalDate datalimite) {
		this.datalimite = datalimite;
	}

	public StatusToken getStatus() {
		return status;
	}

	public void setStatus(StatusToken status) {
		this.status = status;
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
		TokenJsonDTO other = (TokenJsonDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "JsonToken [id=" + id + ", sistema=" + sistema + ", datalimite=" + datalimite + ", status=" + status
				+ "]";
	}
}