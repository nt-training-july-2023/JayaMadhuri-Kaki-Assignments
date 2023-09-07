package com.capstone.assessmentPortal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {

    @Test
    void testUserNotFoundExceptionString() {
            UserNotFoundException ex = new UserNotFoundException(); 
    }

    @Test
    void testUserNotFoundException() {
        String message = "User not found";
        UserNotFoundException ex = new UserNotFoundException(message); 
        assertEquals(message, ex.getMessage());
    }

}