package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserDetailsTest {
    @Test
    void testAllArgsConstructor() {
        UserDetails userDetails = new UserDetails(1L,"Madhuri","Kaki","2001-01-23",Gender.female,"madhu@nucleusteq.com","Madhu@123");
        assertEquals(userDetails.getUserId(),1L);
        assertEquals(userDetails.getFirstName(),"Madhuri");
        assertEquals(userDetails.getLastName(),"Kaki");
        assertEquals(userDetails.getDateOfBirth(),"2001-01-23");
        assertEquals(userDetails.getGender(),Gender.female);
        assertEquals(userDetails.getEmailId(),"madhu@nucleusteq.com");
        assertEquals(userDetails.getUserType(),"Madhu@123");
    }
}
