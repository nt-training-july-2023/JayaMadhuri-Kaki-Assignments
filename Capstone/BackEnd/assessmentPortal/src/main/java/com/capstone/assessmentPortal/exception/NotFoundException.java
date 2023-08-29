package com.capstone.assessmentPortal.exception;

/**
 * Element with id Not Found exception controller class.
*/

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  /**
   *parameter constructor.
   *@param message notFoundExceptionMessage
  */
  public NotFoundException(final String message) {
    super(message);
  }
  /**
   *default constructor.
  */
  public NotFoundException() {
  }
}
