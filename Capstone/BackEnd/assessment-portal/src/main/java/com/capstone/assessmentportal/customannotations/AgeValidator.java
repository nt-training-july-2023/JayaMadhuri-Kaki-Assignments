package com.capstone.assessmentportal.customannotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

/**
 * age validatior class.
*/
@Component
public class AgeValidator implements ConstraintValidator<AdultAge, String> {
    @Override
    public final boolean isValid(final String dateOfBirth,
            final ConstraintValidatorContext context) {
        final int minAge = 18;
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= minAge;
        } catch (Exception e) {
            return false;
        }
    }
}
