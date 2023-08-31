package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {

    @Test
    void testUserNotFoundExceptionString() {
        try {
            UserNotFoundException ex = new UserNotFoundException(); 
        } catch(Exception e) {
            fail("Exception should not be thrown from default constructor");
        }
    }

    @Test
    void testUserNotFoundException() {
        String message = "User not found";
        UserNotFoundException ex = new UserNotFoundException(message); 
        assertEquals(message, ex.getMessage());
    }

}
