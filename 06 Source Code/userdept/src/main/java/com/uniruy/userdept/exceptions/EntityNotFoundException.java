package com.uniruy.userdept.exceptions;

//Custom excetions = exceções customizadas do app, está ligada a regra de negócio
public class EntityNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
		super("Id informado não encontrado ...");
	}

	public EntityNotFoundException(String message) {
		super(message);
	}
}