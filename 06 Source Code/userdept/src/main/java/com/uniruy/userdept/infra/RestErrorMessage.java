package com.uniruy.userdept.infra;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

// instalar o lombok
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

// stack trace do spring - mensagem de erro
// Tratamento de erro: Deixar mensagem padrão do spring 
// ou tratar exceções na camada Controller ou Service
// Tratando as nossas exceções ou as do Java

// https://youtu.be/jqH_UF4vdHw
// https://youtu.be/GmbK-O3v3Gg
// https://youtu.be/ac7JWdD3CZ0

//@AllArgsConstructor
//@Getter
//@Setter
// https://www.zup.com.br/blog/controller-advice
public class RestErrorMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status; // Código de erro
	private HttpStatus error;
	private String message;
	private String path; 

	public RestErrorMessage() {
	}

	public RestErrorMessage(Instant timestamp, Integer status, HttpStatus error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}