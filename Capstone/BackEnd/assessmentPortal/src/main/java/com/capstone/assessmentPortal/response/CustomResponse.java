package com.capstone.assessmentPortal.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *custom response.
 *@param <T> <T>
*/
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {
    /**
     *statuscode attribute.
    */
    private int statusCode;
    /**
     *message attribute.
    */
    private String message;
    /**
     *responseDate entity.
    */
    private T responseData;
    /**
     *parameter constructor.
     *@param statusCodeParam statusCodeParam
     *@param messageParam messageParam
    */
    public CustomResponse(final int statusCodeParam,
            final String messageParam) {
        super();
        this.statusCode = statusCodeParam;
        this.message = messageParam;
    }
}
