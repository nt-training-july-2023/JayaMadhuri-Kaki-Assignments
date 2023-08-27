package com.capstone.assessmentPortal.exception;

public class EmptyListException extends RuntimeException{
  private static final long serialVersionUID = 1L;
  public EmptyListException(String message) {
	  super(message);
  }
  public EmptyListException() {}
}
