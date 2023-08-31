package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyListExceptionTest {

    @Test
    void testEmptyListExceptionString() {
        try {
            EmptyListException ex = new EmptyListException();
        }catch(Exception e) {
            fail("Exception should not be thrown from default constructor");
        }
    }

    @Test
    void testEmptyListException() {
        String message = "List is empty";
        EmptyListException ex = new EmptyListException(message);
        assertEquals(message, ex.getMessage());
    }

}
