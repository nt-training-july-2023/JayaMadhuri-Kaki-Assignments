package com.capstone.assessmentportal.exception;

/**
 *Element already Exists exception controller class.
*/

public class AlreadyExistsException extends RuntimeException {
  /**
   *parameter constructor.
   *@param message alreadyExistsExceptionMessage
  */
  public AlreadyExistsException(final String message) {
    super(message);
  }
}
