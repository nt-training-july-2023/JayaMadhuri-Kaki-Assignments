package com.capstone.assessmentPortal.dto;

import com.capstone.assessmentPortal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 *users dto class for updating users.
*/

@Setter
@Getter
public class UserDetailsForUpdate {
  /**
   *first name of user attribute.
 */
  @Column
  @NotBlank(message = ValidationMessage.firstNameNotblank)
  private String firstName;
  /**
   *last name of user attribute.
 */
  @Column
  @NotBlank(message = ValidationMessage.lastNameNotblank)
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Column
  @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}",
         message = ValidationMessage.dobPattern)
  @NotBlank(message = ValidationMessage.dobNotblank)
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Column
  @NotBlank(message = ValidationMessage.genderNotblank)
  private String gender;
}
