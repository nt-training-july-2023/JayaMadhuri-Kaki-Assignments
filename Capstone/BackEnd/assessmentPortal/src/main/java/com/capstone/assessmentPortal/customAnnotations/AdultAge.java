package com.capstone.assessmentPortal.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation interface to check dateofbirth is 18years above or not.
*/

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AdultAge {
    /**
     * default message.
     * @return message
    */
    String message() default "Age must be at least 18 years";
    /**
     * default groups.
     * @return groups
    */
    Class<?>[] groups() default {};
    /**
     * default payload.
     * @return payload
    */
    Class<? extends Payload>[] payload() default {};
}

