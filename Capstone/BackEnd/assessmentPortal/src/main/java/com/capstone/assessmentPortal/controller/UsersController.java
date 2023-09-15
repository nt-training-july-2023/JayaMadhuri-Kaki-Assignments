package com.capstone.assessmentPortal.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.UsersService;

import jakarta.validation.Valid;

/**
 *Users controller class.
*/
@CrossOrigin(origins = "*")
@RestController
public class UsersController {
  /**
   *autowiring user service.
  */
  @Autowired
  private UsersService usersService;
  /**
   *registeration of students.
   *@return response
   *@param signUpRequest signUpRequest
  */
  @PostMapping("/users/register")
  public final ResponseEntity<Object> studentRegistration(
            @RequestBody @Valid final SignUpRequest signUpRequest) {
    String response = usersService.studentRegistration(signUpRequest);
    return ResponseHandler.generateResponse("Successfully Registered",
            HttpStatus.OK, "UserDetails", response);
  }
  /**
   *user login.
   *@return userDetails
   *@param loginRequest loginRequest
  */
  @PostMapping("/users/login")
  public final ResponseEntity<Object> userLogin(
                  @RequestBody final LoginRequest loginRequest) {
    Map<String, String> userDetails = usersService
            .authenticateUser(loginRequest);
    return ResponseHandler.generateResponse("Login Successfull",
                  HttpStatus.OK, "UserDetails", userDetails);
  }
  /**
   *get student details by id.
   *@return studentDetails
   *@param studentId studentId
  */
  @GetMapping("/users/{studentId}")
  public final ResponseEntity<Object> getStudentById(
                 @PathVariable final Long studentId) {
    UserDetails studentDetails = usersService.getStudentById(studentId);
    return ResponseHandler.generateResponse("Successfully Retrieved",
                 HttpStatus.OK, "StudentDetails", studentDetails);
  }
  /**
   *get student details by email Id.
   *@return studentDetails
   *@param emailId emailId
  */
  @GetMapping("/users/{emailId}")
  public final ResponseEntity<Object> getStudentByEmailId(
                 @PathVariable final String emailId) {
    UserDetails studentDetails = usersService.getStudentDetailsByEmail(emailId);
    return ResponseHandler.generateResponse("Successfully Retrieved",
                 HttpStatus.OK, "StudentDetails", studentDetails);
  }
  /**
   * update student details by id.
   * @return studentDetails
   * @param studentId studentId
   * @param users users
  */
  @PutMapping("/users/update/{studentId}")
  public final ResponseEntity<Object> updateStudentDetails(
           @PathVariable final Long studentId,
           @RequestBody final UserDetailsForUpdate users) {
    UserDetailsForUpdate studentDetails = usersService
            .updateStudentDetails(studentId, users);
    return ResponseHandler.generateResponse("Successfully Updated",
           HttpStatus.OK, "StudentDetails", studentDetails);
  }
  /**
   * delete student details by id.
   * @return deletedStudent
   * @param studentId studentId
  */
  @DeleteMapping("/users/delete/{studentId}")
  public final ResponseEntity<Object> deleteStudent(
           @PathVariable final Long studentId) {
    usersService.deleteStudent(studentId);
    return ResponseHandler.generateResponse("Successfully Deleted",
           HttpStatus.OK, "StudentDetails", null);
  }
}
