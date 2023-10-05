package com.capstone.assessmentportal.exception;

/**
 *Element already Exists exception controller class.
*/

public class AlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  /**
   *parameter constructor.
   *@param message alreadyExistsExceptionMessage
  */
  public AlreadyExistsException(final String message) {
    super(message);
  }
  /**
   *default constructor.
  */
  public AlreadyExistsException() {
  }
}
