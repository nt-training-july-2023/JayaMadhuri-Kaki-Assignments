package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailAlreadyExistsExceptionTest {

    @Test
    void testEmailAlreadyExistsExceptionString() {
            EmailAlreadyExistsException ex = new EmailAlreadyExistsException();
    }

    @Test
    void testEmailAlreadyExistsException() {
        String message = "Email already exists";
        EmailAlreadyExistsException ex = new EmailAlreadyExistsException(message);
        assertEquals(message, ex.getMessage());
    }

}
