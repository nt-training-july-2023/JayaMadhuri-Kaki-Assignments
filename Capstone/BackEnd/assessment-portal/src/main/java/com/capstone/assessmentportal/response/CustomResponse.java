package com.capstone.assessmentportal.response;

import java.util.Objects;

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
    @Override
    public final int hashCode() {
        return Objects.hash(message, responseData, statusCode);
    }
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CustomResponse<?> other = (CustomResponse<?>) obj;
        return Objects.equals(message, other.message)
                && Objects.equals(responseData, other.responseData)
                && statusCode == other.statusCode;
    }
}
