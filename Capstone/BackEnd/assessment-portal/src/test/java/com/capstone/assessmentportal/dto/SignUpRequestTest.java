package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SignUpRequestTest {
    @Test
    void testAllArgsConstructor() {
        SignUpRequest signUpRequest = new SignUpRequest("Madhuri","Kaki","2001-01-23",Gender.female,"madhu@nucleusteq.com","Madhu@123","Student");
        assertEquals(signUpRequest.getFirstName(),"Madhuri");
        assertEquals(signUpRequest.getLastName(),"Kaki");
        assertEquals(signUpRequest.getDateOfBirth(),"2001-01-23");
        assertEquals(signUpRequest.getGender(),Gender.female);
        assertEquals(signUpRequest.getEmailId(),"madhu@nucleusteq.com");
        assertEquals(signUpRequest.getPassword(),"Madhu@123");
        assertEquals(signUpRequest.getUserType(),"Student");
    }
}
