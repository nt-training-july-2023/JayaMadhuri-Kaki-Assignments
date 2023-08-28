package com.capstone.assessmentPortal.controllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmailAlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.exception.NotFoundException;
import com.capstone.assessmentPortal.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String,String> handleEmptyInput(MethodArgumentNotValidException exception) {
	  Map<String,String> errorMap = new HashMap<>();
	  exception.getBindingResult().getFieldErrors().forEach(error->{
		  errorMap.put(error.getField(),error.getDefaultMessage());
	  });
	  errorMap.put("StatusCode", "400");
	  return errorMap;
  }
  
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> handleNoSuchElement(NoSuchElementException exception) {
	return new ResponseEntity<String>("No value is present in Db with this ID, please change your request", HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception) {
	return new ResponseEntity<String>("Invalid user credentials", HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<String> handleAlreadyExistsException(EmailAlreadyExistsException exception) {
	return new ResponseEntity<String>("An account is already registered with this Email", HttpStatus.CONFLICT);
  }
  
  @ExceptionHandler(InputEmptyException.class)
  public ResponseEntity<String> handleInputEmptyException(InputEmptyException exception) {
	return new ResponseEntity<String>("Input fields are empty! Fill the mandatory fields", HttpStatus.CONFLICT);
  }
  
  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException exception) {
	return new ResponseEntity<String>("Data already exists with same name", HttpStatus.CONFLICT);
  }
  
  @ExceptionHandler(EmptyListException.class)
  public ResponseEntity<String> handleEmptyListException(EmptyListException exception) {
	return new ResponseEntity<String>("List is Empty", HttpStatus.OK);
  }
  
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
	return new ResponseEntity<String>("Super class Id Not exists", HttpStatus.NOT_FOUND);
  }
 
}
