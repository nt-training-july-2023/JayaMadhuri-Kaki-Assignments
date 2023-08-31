package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

    @Test
    void testNotFoundExceptionString() {
        try {
            NotFoundException ex = new NotFoundException();
        }catch(Exception e) {
            fail("Exception should not be thrown from default constructor");
        }
    }

    @Test
    void testNotFoundException() {
        String message = "Not found";
        NotFoundException ex = new NotFoundException(message);
        assertEquals(message, ex.getMessage());
    }

}
