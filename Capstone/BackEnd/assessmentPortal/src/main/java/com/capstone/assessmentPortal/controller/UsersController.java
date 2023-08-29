package com.capstone.assessmentPortal.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.UsersService;

import jakarta.validation.Valid;

/**
 *Users controller class.
*/

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
   *@param users users
  */
  @PostMapping("/studentRegister")
  public final ResponseEntity<Object> studentRegistration(
            @RequestBody @Valid final Users users) {
    String response = usersService.studentRegistration(users);
    return ResponseHandler.generateResponse("Successfully Registered",
            HttpStatus.OK, "User Details", response);
  }
  /**
   *user login.
   *@return userDetails
   *@param users users
  */
  @PostMapping("/userLogin")
  public final ResponseEntity<Object> userLogin(
                  @RequestBody final Users users) {
    Map<String, String> userDetails = usersService.authenticateUser(users);
    return ResponseHandler.generateResponse("Login Successfull",
                  HttpStatus.OK, "User Details", userDetails);
  }
  /**
   *get student details by id.
   *@return studentDetails
   *@param studentId studentId
  */
  @GetMapping("/getUser/{studentId}")
  public final ResponseEntity<Object> getStudentById(
                 @PathVariable final Long studentId) {
    Users studentDetails = usersService.getStudentById(studentId);
    return ResponseHandler.generateResponse("Successfully Retrieved",
                 HttpStatus.OK, "Student Details", studentDetails);
  }
  /**
   * update student details by id.
   * @return studentDetails
   * @param studentId studentId
   * @param users users
  */
  @PutMapping("/updateStudent/{studentId}")
  public final ResponseEntity<Object> updateStudentDetails(
           @PathVariable final Long studentId,
           @RequestBody final Users users) {
    Users studentDetails = usersService.updateStudentDetails(studentId, users);
    return ResponseHandler.generateResponse("Successfully Updated",
           HttpStatus.OK, "Student Details", studentDetails);
  }
  /**
   * delete student details by id.
   * @return deletedStudent
   * @param studentId studentId
  */
  @DeleteMapping("/deleteStudent/{studentId}")
  public final ResponseEntity<Object> deleteStudent(
           @PathVariable final Long studentId) {
    usersService.deleteStudent(studentId);
    return ResponseHandler.generateResponse("Successfully Deleted",
           HttpStatus.OK, "Student Details", null);
  }
}
