package com.project.tailor.exceptionhandeling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFound(BadRequestException e){
		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> badRequestHandler(Exception e){
		
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	//ResponseEntity<ResponseDTO>
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionResponse>  handleValidationExceptions(
			DataIntegrityViolationException	 e) {
		/*
			Map<String, String> errors = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			String message = errors.toString();
			message = message.replace("{","");
			message = message.replace("}","");
			
			return ResponseEntity
				.badRequest()
				.body(new ResponseDTO(message,
						"Validation errors",
						400));
		*/
		ExceptionResponse error= new ExceptionResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
		
	
    /*
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handlebException(Exception e){

		ExceptionResponse error= new ExceptionResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
}
