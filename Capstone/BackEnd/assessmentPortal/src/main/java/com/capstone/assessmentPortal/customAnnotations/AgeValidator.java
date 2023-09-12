package com.capstone.assessmentPortal.customAnnotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

/**
 * age validatior class.
*/

public class AgeValidator implements ConstraintValidator<AdultAge, String> {
    @Override
    public void initialize(final AdultAge adultAge) {
    }

    @Override
    public final boolean isValid(final String dateOfBirth,
            final ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false; // Let @NotBlank handle this case
        }
        final float minAge = 18;
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= minAge;
        } catch (Exception e) {
            return false; // Date parsing error, let @Pattern handle this
        }
    }
}
