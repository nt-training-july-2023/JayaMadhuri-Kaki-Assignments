package com.capstone.assessmentPortal.exception;

public class InputEmptyException extends RuntimeException{
  private static final long serialVersionUID = 1L;
  public InputEmptyException(String message) {
	  super(message);
  }
  public InputEmptyException() {}
}
