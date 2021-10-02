package com.project.tailor.exceptionhandeling;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException e){
		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> handlebBadRequest(Exception e){
		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handlebException(Exception e){
		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
