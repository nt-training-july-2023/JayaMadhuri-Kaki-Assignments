package com.capstone.assessmentPortal.dto;

import com.capstone.assessmentPortal.customAnnotations.AdultAge;
import com.capstone.assessmentPortal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * users Dto class for registration.
*/

@Setter
@Getter
public class SignUpRequest {
  /**
   *first name of user attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.firstNameNotblank)
  private String firstName;
  /**
   *last name of user attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.lastNameNotblank)
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Column(nullable = false)
  @AdultAge(message = ValidationMessage.ageValidation)
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
         message = ValidationMessage.dobPattern)
  @NotBlank(message = ValidationMessage.dobNotblank)
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.genderNotblank)
  private String gender;
  /**
   *user email attribute.
 */
  @Column(unique = true, nullable = false)
  @NotBlank(message = ValidationMessage.emailNotblank)
  @Pattern(regexp = "^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$",
          message = ValidationMessage.emailPattern)
  private String emailId;
  /**
   *user password attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.passwordNotblank)
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
  message = ValidationMessage.passwordPattern)
  private String password;
  /**
   *role of user attribute.
 */
  @Column
  private String userType = "Student";
}
