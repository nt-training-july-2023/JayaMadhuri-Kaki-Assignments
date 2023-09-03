/**
 * 
 */
package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class AlreadyExistsExceptionTest {

    /**
     * Test method for {@link com.capstone.assessmentPortal.exception.AlreadyExistsException#AlreadyExistsException(java.lang.String)}.
     */
    @Test
    void testAlreadyExistsExceptionString() {
        AlreadyExistsException ex = new AlreadyExistsException();
    }

    /**
     * Test method for {@link com.capstone.assessmentPortal.exception.AlreadyExistsException#AlreadyExistsException()}.
     */
    @Test
    void testAlreadyExistsException() {
        String message = "Element already exists";
        AlreadyExistsException ex = new AlreadyExistsException(message);
        assertEquals(message, ex.getMessage());
    }

}
