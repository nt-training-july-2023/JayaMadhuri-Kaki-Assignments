package com.capstone.assessmentportal.dto;

import com.capstone.assessmentportal.response.ValidationMessage;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class UserDetailsForUpdate {
  /**
   *first name of user attribute.
 */
  @NotBlank(message = ValidationMessage.FIRSTNAME_NOTBLANK)
  private String firstName;
  /**
   *last name of user attribute.
 */
  @NotBlank(message = ValidationMessage.LASTNAME_NOTBLANK)
  private String lastName;
  /**
   *date of birth(dd-mm-yyyy) attribute.
 */
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
         message = ValidationMessage.DOB_PATTERN)
  @NotBlank(message = ValidationMessage.DOB_NOTBLANK)
  private String dateOfBirth;
  /**
   *user gender attribute.
 */
  @Enumerated(EnumType.STRING)
  private Gender gender;
}
