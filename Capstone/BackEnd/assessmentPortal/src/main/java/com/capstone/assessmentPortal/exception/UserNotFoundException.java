package com.capstone.assessmentPortal.exception;

/**
 *User not found exception controller class.
*/

public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  /**
   *parameter constructor.
   *@param message userNotFoundExceptionMessage
  */
  public UserNotFoundException(final String message) {
    super(message);
  }
  /**
   *default constructor.
  */
  public UserNotFoundException() {
  }
}
