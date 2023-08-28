package com.capstone.assessmentPortal.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;
  @Column
  @NotBlank(message = "First Name is required")
  private String firstName;
  @Column
  @NotBlank(message = "Last Name is required")
  private String lastName;
  @Column
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth pattern should be yyyy-mm-dd")
  @NotBlank(message = "Date of birth is required")
  private String dateOfBirth;
  @Column
  @NotBlank(message = "Gender is required")
  private String gender;
  @Column(unique = true)
  @NotBlank(message = "Email is required")
  @Pattern(regexp = "^[A-Z0-9a-z+_-]+@nucleusteq[.]com$",  message = "Email is not Valid")
  private String emailId;
  @Column
  @NotBlank(message = "Password is required")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
  message = "Password must be at least 8 characters long and "
             + "contain at least one digit, one lowercase letter, one uppercase letter, "
             + "one special character, and no whitespace")
  private String password;
  @Column
  private String userType = "Student";
  @OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Results> results = new ArrayList<>();
}
