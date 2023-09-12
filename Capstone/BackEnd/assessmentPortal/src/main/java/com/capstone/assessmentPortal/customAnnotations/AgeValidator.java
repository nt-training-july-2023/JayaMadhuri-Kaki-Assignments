package com.capstone.assessmentPortal.customAnnotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<AdultAge, String> {
    @Override
    public void initialize(AdultAge adultAge) {
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false; // Let @NotBlank handle this case
        }

        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() >= 18;
        } catch (Exception e) {
            return false; // Date parsing error, let @Pattern handle this
        }
    }
}
