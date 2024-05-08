package com.uniruy.userdept.infra;

import org.springframework.http.HttpStatus;

// instalar o lombok
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

//@AllArgsConstructor
//@Getter
//@Setter
public class RestErrorMessage {
	private HttpStatus status;
	private String message;
}