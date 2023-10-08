package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserDetailsForUpdateTest {

    @Test
    void testAllArgsConstructor() {
        UserDetailsForUpdate userDetailsForUpdate = new UserDetailsForUpdate("Madhuri","Kaki","2001-01-23",Gender.female);
        assertEquals(userDetailsForUpdate.getFirstName(),"Madhuri");
        assertEquals(userDetailsForUpdate.getLastName(),"Kaki");
        assertEquals(userDetailsForUpdate.getDateOfBirth(),"2001-01-23");
        assertEquals(userDetailsForUpdate.getGender(),Gender.female);
    }

}