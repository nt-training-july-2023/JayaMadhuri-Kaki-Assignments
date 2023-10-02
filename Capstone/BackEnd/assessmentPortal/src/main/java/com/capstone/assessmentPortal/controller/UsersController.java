package com.capstone.assessmentPortal.controller;

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

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;
import com.capstone.assessmentPortal.response.CustomResponse;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.response.ValidationMessage;
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_REGISTERED, HttpStatus.OK, null);
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_LOGIN, HttpStatus.OK, userDetails);
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_RETRIEVED_BY_ID, HttpStatus.OK, userDetails);
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_RETRIEVED_BY_EMAIL, HttpStatus.OK, userDetails);
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_EMAIL_VALIDATE, HttpStatus.OK, null);
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_UPDATED, HttpStatus.OK, null);
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
    return ResponseHandler.generateResponse(ValidationMessage
            .USER_DELETED, HttpStatus.OK, null);
  }
}
