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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
  @NotEmpty(message = "First Name is required")
  private String firstName;
  @Column
  @NotEmpty(message = "Last Name is required")
  private String lastName;
  @Column
  @NotEmpty(message = "Date of birth is required")
  private String dateOfBirth;
  @Column
  @NotEmpty(message = "Gender is required")
  private String gender;
  @Column(unique = true)
  @Email(message = "Not a valid Email")
  @NotEmpty(message = "Email is required")
  private String emailId;
  @Column
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
  message = "Password must be at least 8 characters long and "
             + "contain at least one digit, one lowercase letter, one uppercase letter, "
             + "one special character, and no whitespace")
  @NotEmpty(message = "Password is required")
  private String password;
  @Column(columnDefinition = "varchar(20) default 'Student'")
  private String userType;
  @OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Results> results = new ArrayList<>();
}
