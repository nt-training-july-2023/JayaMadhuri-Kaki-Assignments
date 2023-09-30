package com.capstone.assessmentPortal.response;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.assessmentPortal.model.Users;

@SpringBootTest
class ResponseHandlerTest {

    @Test
    void testGenerateResponse() {
        Users users = new Users();
        String message = "Success";
        HttpStatus code = HttpStatus.OK;
        Object responseObj = users;
        ResponseEntity<CustomResponse<Object>> response = ResponseHandler.generateResponse(message, code, responseObj);
        assertNotNull(response);
        assertEquals(code, response.getStatusCode());
        assertEquals(message, response.getBody().getMessage());
        assertEquals(responseObj, response.getBody().getResponseData());
    }

}
