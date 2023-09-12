package com.capstone.assessmentPortal.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AdultAge {
    String message() default "Age must be at least 18 years";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

