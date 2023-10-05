package com.capstone.assessmentportal.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
@SuppressWarnings("unused")
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