package com.uniruy.userdept.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super("Bad Request ...");
	}

	public BadRequestException(String message) {
		super(message);
	}	
}