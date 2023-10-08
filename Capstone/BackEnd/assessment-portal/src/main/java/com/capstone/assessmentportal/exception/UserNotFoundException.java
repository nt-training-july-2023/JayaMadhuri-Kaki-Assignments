package com.capstone.assessmentportal.exception;

/**
 *User not found exception controller class.
*/

public class UserNotFoundException extends RuntimeException {
  /**
   *parameter constructor.
   *@param message userNotFoundExceptionMessage
  */
  public UserNotFoundException(final String message) {
    super(message);
  }
}
