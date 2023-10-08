package com.capstone.assessmentportal.customannotations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DateOfBirthValidatorTest {
    
    @Autowired
    AgeValidator ageValidator;
    
    @Test
    public void testValidDOB() {
        String dateOfBirth = "2005-01-01";
        boolean isValid = ageValidator.isValid(dateOfBirth, null);
        assertTrue(isValid);
    }
    @Test
    public void testInvalidDateOfBirth() {
        String dateOfBirth = "2010-01-01";
        boolean isValid = ageValidator.isValid(dateOfBirth, null);
        assertFalse(isValid);
    }
    @Test
    public void testInvalidDateFormat() {
        String dateFormat = "2022-01-32";
        boolean isValid = ageValidator.isValid(dateFormat, null);
        assertFalse(isValid);
    }
}
