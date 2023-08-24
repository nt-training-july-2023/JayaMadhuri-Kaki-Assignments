package com.capstone.assessmentPortal.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
  public static ResponseEntity<Object> generateResponse(String message, 
		  HttpStatus code, String objName, Object responseObj) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put(objName, responseObj);
    map.put("message", message);
    map.put("statusCode", code.value());
    return new ResponseEntity<Object>(map,code);
  }
}
