package com.capstone.assessmentPortal.exception;

/**
 * Duplicate email exception controller class.
*/

public class EmailAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  /**
   * parameter constructor.
   * @param message emailAlreadyExistsExceptionMessage
  */
  public EmailAlreadyExistsException(final String message) {
    super(message);
  }
  /**
   * default constructor.
  */
  public EmailAlreadyExistsException() {
  }
}
