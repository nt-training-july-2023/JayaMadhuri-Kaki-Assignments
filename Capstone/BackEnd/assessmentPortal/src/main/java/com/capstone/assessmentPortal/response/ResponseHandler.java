package com.capstone.assessmentPortal.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *Response handler class to handle responses.
*/

public final class ResponseHandler {
  /**
   *default constructor.
  */
  private ResponseHandler() {
  }
  /**
   *generate response method will generate custom response.
   *@return response
   *@param message message
   *@param code code
   *@param responseObj responseObj
   *@param <T> <T>
  */
  public static <T> ResponseEntity<CustomResponse<T>> generateResponse(
          final String message, final HttpStatus code,
          final T responseObj) {
    CustomResponse<T> customResponse = new CustomResponse<>(
            code.value(), message, responseObj);
    return new ResponseEntity<>(customResponse, code);
  }
}
