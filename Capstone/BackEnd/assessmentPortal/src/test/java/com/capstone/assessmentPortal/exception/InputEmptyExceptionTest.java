package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InputEmptyExceptionTest {

    @Test
    void testInputEmptyExceptionString() {
        try {
            InputEmptyException ex = new InputEmptyException();
        }catch(Exception e) {
            fail("Exception should not be thrown from default constructor");
        }
    }

    @Test
    void testInputEmptyException() {
        String message = "Inputs are empty";
        InputEmptyException ex = new InputEmptyException(message);
        assertEquals(message, ex.getMessage());
    }

}
