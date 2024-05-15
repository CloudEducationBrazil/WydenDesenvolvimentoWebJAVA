package com.uniruy.userdept.infra;

import java.time.Instant;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

import com.uniruy.userdept.exceptions.EntityNotFoundException;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
//stack trace do spring - mensagem de erro
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<RestErrorMessage> entityNotFoundException(EntityNotFoundException exception, HttpServletRequest request){
		RestErrorMessage theadResponse = new RestErrorMessage();

		theadResponse.setTimestamp(Instant.now());
		theadResponse.setStatus(HttpStatus.NOT_FOUND.value());
		theadResponse.setError(HttpStatus.NOT_FOUND);
		theadResponse.setMessage(exception.getMessage());
		theadResponse.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(theadResponse);
	}

	@ExceptionHandler(BadRequestException.class)
	private ResponseEntity<RestErrorMessage> badRequestException(BadRequestException exception, HttpServletRequest request){

		RestErrorMessage theadResponse = new RestErrorMessage();

		theadResponse.setTimestamp(Instant.now());
		theadResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		theadResponse.setError(HttpStatus.BAD_REQUEST);
		theadResponse.setMessage("JSON mal formatado");
		theadResponse.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(theadResponse);
	}

	@ExceptionHandler(InternalServerError.class)
	private ResponseEntity<RestErrorMessage> internalServerError(InternalServerError exception, HttpServletRequest request){

		RestErrorMessage theadResponse = new RestErrorMessage();

		theadResponse.setTimestamp(Instant.now());
		theadResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		theadResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR);
		theadResponse.setMessage(exception.getMessage());
		theadResponse.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(theadResponse);
	}
	
	@ExceptionHandler(RuntimeException.class)
	private ResponseEntity<RestErrorMessage> runtimeException(RuntimeException exception, HttpServletRequest request){

		RestErrorMessage theadResponse = new RestErrorMessage();

		theadResponse.setTimestamp(Instant.now());
		theadResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		theadResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR);
		theadResponse.setMessage(exception.getMessage());
		theadResponse.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(theadResponse);
	}
}	