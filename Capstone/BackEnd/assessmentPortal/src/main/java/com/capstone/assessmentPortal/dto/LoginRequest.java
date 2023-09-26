package com.capstone.assessmentPortal.dto;

import com.capstone.assessmentPortal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * users DTO for login class.
*/

@Setter
@Getter
public class LoginRequest {
  /**
   *user email attribute.
 */
  @Column(unique = true, nullable = false)
  @NotBlank(message = ValidationMessage.EMAIL_NOTBLANK)
  private String emailId;
  /**
   *user password attribute.
 */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.PASSWORD_NOTBLANK)
  private String password;
}
