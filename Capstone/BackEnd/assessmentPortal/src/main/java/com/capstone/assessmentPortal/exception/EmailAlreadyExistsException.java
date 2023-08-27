package com.capstone.assessmentPortal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmailAlreadyExistsException extends RuntimeException{
  private static final long serialVersionUID = 1L;
  public EmailAlreadyExistsException(String message) {
    super(message);
  }
  public EmailAlreadyExistsException() {}
}
