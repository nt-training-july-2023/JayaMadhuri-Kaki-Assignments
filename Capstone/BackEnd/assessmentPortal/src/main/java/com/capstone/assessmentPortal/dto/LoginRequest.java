package com.capstone.assessmentPortal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
  @NotBlank(message = "Email is required")
  @Pattern(regexp = "^[A-Z0-9a-z+_-]+@nucleusteq[.]com$",
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
}
