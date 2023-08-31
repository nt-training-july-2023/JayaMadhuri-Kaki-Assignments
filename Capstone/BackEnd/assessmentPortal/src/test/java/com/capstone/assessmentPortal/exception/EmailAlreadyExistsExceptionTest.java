package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailAlreadyExistsExceptionTest {

    @Test
    void testEmailAlreadyExistsExceptionString() {
        try {
            EmailAlreadyExistsException ex = new EmailAlreadyExistsException();
        }catch(Exception e) {
            fail("Exception should not be thrown from default constructor");
        }
    }

    @Test
    void testEmailAlreadyExistsException() {
        String message = "Email already exists";
        EmailAlreadyExistsException ex = new EmailAlreadyExistsException(message);
        assertEquals(message, ex.getMessage());
    }

}
