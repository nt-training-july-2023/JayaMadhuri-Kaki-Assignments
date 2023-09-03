package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

    @Test
    void testNotFoundExceptionString() {
            NotFoundException ex = new NotFoundException();
    }

    @Test
    void testNotFoundException() {
        String message = "Not found";
        NotFoundException ex = new NotFoundException(message);
        assertEquals(message, ex.getMessage());
    }

}
