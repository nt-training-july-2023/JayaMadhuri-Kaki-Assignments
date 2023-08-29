package com.capstone.assessmentPortal.exception;

/**
 * Empty list exception controller class.
*/

public class EmptyListException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  /**
   * Parameter constructor.
   * @param message emptyListExceptionMessage
  */
  public EmptyListException(final String message) {
    super(message);
  }
  /**
   * default constructor.
  */
  public EmptyListException() {
  }
}
