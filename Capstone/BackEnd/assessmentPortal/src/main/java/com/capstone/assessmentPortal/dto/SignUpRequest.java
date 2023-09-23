package com.capstone.assessmentPortal.dto;

import com.capstone.assessmentPortal.customAnnotations.AdultAge;
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
  @NotBlank(message = "First Name is required")
  private String firstName;
  /**
   *last name of user attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = "Last Name is required")
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Column(nullable = false)
  @AdultAge(message = "User must be at least 18 years old")
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
         message = "Date of birth pattern should be yyyy-mm-dd")
  @NotBlank(message = "Date of birth is required")
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = "Gender is required")
  private String gender;
  /**
   *user email attribute.
 */
  @Column(unique = true, nullable = false)
  @NotBlank(message = "Email is required")
  @Pattern(regexp = "^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$",
          message = "Email is not Valid")
  private String emailId;
  /**
   *user password attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = "Password is required")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
  message = "Password must be at least 8 characters long and "
             + "contain at least one digit, one lowercase letter,"
             + " one uppercase letter, "
             + "one special character, and no whitespace")
  private String password;
  /**
   *role of user attribute.
 */
  @Column
  private String userType = "Student";
}
