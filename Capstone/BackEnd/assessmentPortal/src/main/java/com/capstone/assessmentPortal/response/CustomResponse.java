package com.capstone.assessmentPortal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CustomResponse<T> {
    private String message;
    private int statusCode;
    private T responseData;
}
