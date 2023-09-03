package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyListExceptionTest {

    @Test
    void testEmptyListExceptionString() {
            EmptyListException ex = new EmptyListException();
    }

    @Test
    void testEmptyListException() {
        String message = "List is empty";
        EmptyListException ex = new EmptyListException(message);
        assertEquals(message, ex.getMessage());
    }

}
