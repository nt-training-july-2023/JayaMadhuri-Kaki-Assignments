package com.capstone.assessmentPortal.dto;

import com.capstone.assessmentPortal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *users dto class for updating users.
*/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsForUpdate {
  /**
   *first name of user attribute.
 */
  @Column
  @NotBlank(message = ValidationMessage.FIRSTNAME_NOTBLANK)
  private String firstName;
  /**
   *last name of user attribute.
 */
  @Column
  @NotBlank(message = ValidationMessage.LASTNAME_NOTBLANK)
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Column
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
         message = ValidationMessage.DOB_PATTERN)
  @NotBlank(message = ValidationMessage.DOB_NOTBLANK)
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Column
  @NotBlank(message = ValidationMessage.GENDER_NOTBLANK)
  private String gender;
}
