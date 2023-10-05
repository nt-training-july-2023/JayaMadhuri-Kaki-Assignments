package com.capstone.assessmentportal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
@SuppressWarnings("unused")
class AlreadyExistsExceptionTest {
    @Test
    void testAlreadyExistsExceptionString() {
        AlreadyExistsException ex = new AlreadyExistsException();
    }
    @Test
    void testAlreadyExistsException() {
        String message = "Element already exists";
        AlreadyExistsException ex = new AlreadyExistsException(message);
        assertEquals(message, ex.getMessage());
    }
}