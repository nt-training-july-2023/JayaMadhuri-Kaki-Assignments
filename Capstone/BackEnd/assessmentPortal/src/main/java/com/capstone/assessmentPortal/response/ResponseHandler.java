package com.capstone.assessmentPortal.response;

import java.util.HashMap;
import java.util.Map;

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
   *@param objName objName
   *@param responseObj responseObj
  */
  public static ResponseEntity<Object> generateResponse(
          final String message, final HttpStatus code,
          final String objName, final Object responseObj) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(objName, responseObj);
    map.put("message", message);
    map.put("statusCode", code.value());
    return new ResponseEntity<Object>(map, code);
  }
}
