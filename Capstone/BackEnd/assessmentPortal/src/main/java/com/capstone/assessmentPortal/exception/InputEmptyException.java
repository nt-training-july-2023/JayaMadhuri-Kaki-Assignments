package com.capstone.assessmentPortal.exception;

/**
 * Empty Input exception controller class.
*/

public class InputEmptyException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  /**
   *parameter constructor.
   *@param message message
  */
  public InputEmptyException(final String message) {
    super(message);
  }
  /**
   *default constructor.
  */
  public InputEmptyException() {
  }
}
