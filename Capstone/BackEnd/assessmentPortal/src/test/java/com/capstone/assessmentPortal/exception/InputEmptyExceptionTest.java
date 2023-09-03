package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InputEmptyExceptionTest {

    @Test
    void testInputEmptyExceptionString() {
            InputEmptyException ex = new InputEmptyException();
    }

    @Test
    void testInputEmptyException() {
        String message = "Inputs are empty";
        InputEmptyException ex = new InputEmptyException(message);
        assertEquals(message, ex.getMessage());
    }

}
