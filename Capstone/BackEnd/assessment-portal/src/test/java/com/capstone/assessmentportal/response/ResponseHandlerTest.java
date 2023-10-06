package com.capstone.assessmentportal.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.capstone.assessmentportal.model.Users;

@SpringBootTest
class ResponseHandlerTest {

    @Test
    void testGenerateResponse() {
        Users users = new Users();
        String message = "Success";
        HttpStatus code = HttpStatus.OK;
        Object responseObj = users;
        CustomResponse<Object> response = ResponseHandler.generateResponse(message, code, responseObj);
        //assertNotNull(response);
        assertEquals(code.value(), response.getStatusCode());
        assertEquals(message, response.getMessage());
        assertEquals(responseObj, response.getResponseData());
    }

}