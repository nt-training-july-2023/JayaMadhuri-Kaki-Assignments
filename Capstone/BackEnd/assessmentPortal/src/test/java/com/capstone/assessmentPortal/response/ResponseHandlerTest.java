package com.capstone.assessmentPortal.response;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.assessmentPortal.model.Users;

class ResponseHandlerTest {

    @Test
    void testGenerateResponse() {
        Users users = new Users();
        String message = "Success";
        HttpStatus code = HttpStatus.OK;
        String objName = "Jaya";
        Object responseObj = users;
        ResponseEntity<Object> response = ResponseHandler.generateResponse(message, code, objName, responseObj);
        assertNotNull(response);
        assertEquals(code, response.getStatusCode());
    }

}
