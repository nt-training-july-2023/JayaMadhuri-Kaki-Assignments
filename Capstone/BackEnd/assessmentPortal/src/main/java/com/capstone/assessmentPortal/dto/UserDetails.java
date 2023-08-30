package com.capstone.assessmentPortal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * users Dto class for getting user details.
*/

@Data
public class UserDetails {
  /**
   *first name of user attribute.
 */
  @Column
  @NotBlank(message = "First Name is required")
  private String firstName;
  /**
   *last name of user attribute.
 */
  @Column
  @NotBlank(message = "Last Name is required")
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Column
  @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}",
         message = "Date of birth pattern should be yyyy-mm-dd")
  @NotBlank(message = "Date of birth is required")
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Column
  @NotBlank(message = "Gender is required")
  private String gender;
  /**
   *user email attribute.
 */
  @Column(unique = true)
  @NotBlank(message = "Email is required")
  @Pattern(regexp = "^[A-Z0-9a-z+_-]+@nucleusteq[.]com$",
          message = "Email is not Valid")
  private String emailId;
  /**
   *role of user attribute.
 */
  @Column
  private String userType = "Student";
}
