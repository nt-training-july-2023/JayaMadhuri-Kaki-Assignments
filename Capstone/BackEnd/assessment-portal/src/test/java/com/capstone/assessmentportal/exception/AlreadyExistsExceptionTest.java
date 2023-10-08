package com.capstone.assessmentportal.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AlreadyExistsExceptionTest {
    @Test
    void testAlreadyExistsException() {
        String message = "Element already exists";
        AlreadyExistsException ex = new AlreadyExistsException(message);
        assertEquals(message, ex.getMessage());
    }
}