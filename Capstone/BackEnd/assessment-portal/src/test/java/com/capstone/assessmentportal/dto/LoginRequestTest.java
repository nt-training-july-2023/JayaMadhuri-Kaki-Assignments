package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginRequestTest {
    @Test
    void testAllArgsConstructor() {
        LoginRequest loginRequest = new LoginRequest("admin@nucleusteq.com","Madhu@123");
        assertEquals(loginRequest.getEmailId(),"admin@nucleusteq.com");
        assertEquals(loginRequest.getPassword(),"Madhu@123");
    }
}
