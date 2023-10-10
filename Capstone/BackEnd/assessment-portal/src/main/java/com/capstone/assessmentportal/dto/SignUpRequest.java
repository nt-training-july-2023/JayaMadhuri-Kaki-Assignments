package com.capstone.assessmentportal.dto;

import com.capstone.assessmentportal.customannotations.AdultAge;
import com.capstone.assessmentportal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * users Dto class for registration.
*/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
  /**
   *first name of user attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.FIRSTNAME_NOTBLANK)
  private String firstName;
  /**
   *last name of user attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.LASTNAME_NOTBLANK)
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Column(nullable = false)
  @AdultAge(message = ValidationMessage.AGE_VALIDATION)
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
         message = ValidationMessage.DOB_PATTERN)
  @NotBlank(message = ValidationMessage.DOB_NOTBLANK)
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;
  /**
   *user email attribute.
 */
  @Column(unique = true, nullable = false)
  @NotBlank(message = ValidationMessage.EMAIL_NOTBLANK)
  @Pattern(regexp = "^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$",
          message = ValidationMessage.EMAIL_PATTERN)
  private String emailId;
  /**
   *user password attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.PASSWORD_NOTBLANK)
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
  message = ValidationMessage.PASSWORD_PATTERN)
  private String password;
  /**
   *role of user attribute.
 */
  private String userType = "Student";
}
