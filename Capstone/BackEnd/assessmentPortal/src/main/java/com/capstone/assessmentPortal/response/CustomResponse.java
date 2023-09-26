package com.capstone.assessmentPortal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *custom response.
 *@param <T> <T>
*/
@AllArgsConstructor
@Setter
@Getter
public class CustomResponse<T> {
    /**
     *message attribute.
    */
    private String message;
    /**
     *statuscode attribute.
    */
    private int statusCode;
    /**
     *responseDate entity.
    */
    private T responseData;
}
