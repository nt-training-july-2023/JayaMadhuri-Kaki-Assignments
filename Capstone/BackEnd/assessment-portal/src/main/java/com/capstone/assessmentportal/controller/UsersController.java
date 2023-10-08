package com.capstone.assessmentportal.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentportal.dto.LoginRequest;
import com.capstone.assessmentportal.dto.SignUpRequest;
import com.capstone.assessmentportal.dto.UserDetails;
import com.capstone.assessmentportal.dto.UserDetailsForUpdate;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.UsersService;

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
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(UsersController.class);
  /**
   *registeration of students.
   *@return response
   *@param signUpRequest signUpRequest
  */
  @PostMapping("/register")
  public final CustomResponse<SignUpRequest>
                                              studentRegistration(
            @RequestBody @Valid final SignUpRequest signUpRequest) {
    usersService.studentRegistration(signUpRequest);
    logger.info(ValidationMessage.USER_REGISTERED);
    CustomResponse<SignUpRequest> result = new
            CustomResponse<SignUpRequest>(HttpStatus.OK.value(),
                    ValidationMessage.USER_REGISTERED, null);
    return result;
  }
  /**
   *user login.
   *@return userDetails
   *@param loginRequest loginRequest
  */
  @PostMapping("/login")
  public final CustomResponse<Map<String, String>> userLogin(
                  @RequestBody @Valid final LoginRequest loginRequest) {
    Map<String, String> userDetails = usersService
            .authenticateUser(loginRequest);
    logger.info(ValidationMessage.USER_LOGIN);
    CustomResponse<Map<String, String>> result = new
            CustomResponse<Map<String, String>>(HttpStatus.OK.value(),
                    ValidationMessage.USER_LOGIN, userDetails);
    return result;
  }
  /**
   *get student details by id.
   *@return studentDetails
   *@param studentId studentId
  */
  @GetMapping("/{studentId}")
  public final CustomResponse<UserDetails> getStudentById(
                 @PathVariable final Long studentId) {
    UserDetails userDetails = usersService.getStudentById(studentId);
    logger.info(ValidationMessage.USER_RETRIEVED_BY_ID);
    CustomResponse<UserDetails> result = new
            CustomResponse<UserDetails>(HttpStatus.OK.value(),
                    ValidationMessage.USER_RETRIEVED_BY_ID, userDetails);
    return result;
  }
  /**
   *get student details by email Id.
   *@return studentDetails
   *@param emailId emailId
  */
  @GetMapping("/email/{emailId}")
  public final CustomResponse<UserDetails> getStudentByEmailId(
                 @PathVariable final String emailId) {
    UserDetails userDetails = usersService.getStudentDetailsByEmail(emailId);
    logger.info(ValidationMessage.USER_RETRIEVED_BY_EMAIL);
    CustomResponse<UserDetails> result = new
            CustomResponse<UserDetails>(HttpStatus.OK.value(),
                    ValidationMessage.USER_RETRIEVED_BY_EMAIL, userDetails);
    return result;
  }
  /**
   *get users by email Id.
   *@return string
   *@param emailId emailId
  */
  @GetMapping("/register/{emailId}")
  public final CustomResponse<UserDetails> getUserByEmailId(
                 @PathVariable final String emailId) {
    usersService.getUsersDetailsByEmail(emailId);
    logger.info(ValidationMessage.USER_EMAIL_VALIDATE);
    CustomResponse<UserDetails> result = new
            CustomResponse<UserDetails>(HttpStatus.OK.value(),
                    ValidationMessage.USER_EMAIL_VALIDATE, null);
    return result;
  }

  /**
   * update student details by id.
   * @return studentDetails
   * @param studentId studentId
   * @param users users
  */
  @PutMapping("/{studentId}")
  public final CustomResponse<UserDetailsForUpdate>
                                                 updateStudentDetails(
           @PathVariable final Long studentId,
           @RequestBody @Valid final UserDetailsForUpdate users) {
    usersService
            .updateStudentDetails(studentId, users);
    logger.info(ValidationMessage.USER_UPDATED);
    CustomResponse<UserDetailsForUpdate> result = new
            CustomResponse<UserDetailsForUpdate>(HttpStatus.OK.value(),
                    ValidationMessage.USER_UPDATED, null);
    return result;
  }
  /**
   * delete student details by id.
   * @return deletedStudent
   * @param studentId studentId
  */
  @DeleteMapping("/{studentId}")
  public final CustomResponse<UserDetails> deleteStudent(
           @PathVariable final Long studentId) {
    usersService.deleteStudent(studentId);
    logger.info(ValidationMessage.USER_DELETED);
    CustomResponse<UserDetails> result = new
            CustomResponse<UserDetails>(HttpStatus.OK.value(),
                    ValidationMessage.USER_DELETED, null);
    return result;
  }
}
