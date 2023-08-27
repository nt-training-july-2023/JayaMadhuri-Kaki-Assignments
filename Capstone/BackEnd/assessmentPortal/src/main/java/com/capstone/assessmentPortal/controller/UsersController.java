package com.capstone.assessmentPortal.controller;

import java.util.Map;
import java.util.Optional;

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

@RestController
public class UsersController {
  @Autowired
  UsersService usersService;
  
  @PostMapping("/studentRegister")
  public ResponseEntity<Object> studentRegistration(@RequestBody @Valid Users users) {
	String response = usersService.studentRegistration(users);
    return ResponseHandler.generateResponse("Successfully Registered",
		HttpStatus.OK, "User Details", response);
  }
  
  @PostMapping("/userLogin")
  public ResponseEntity<Object> userLogin(@RequestBody Users users) {
    Map<String, String> userDetails = usersService.authenticateUser(users);
    return ResponseHandler.generateResponse("Login Successfull",
		HttpStatus.OK, "User Details", userDetails);
  }
  
  @GetMapping("/getUser/{studentId}")
  public ResponseEntity<Object> getStudentById(@PathVariable Long studentId) {
    Users studentDetails = usersService.getStudentById(studentId);
    return ResponseHandler.generateResponse("Successfully Retrieved",
		HttpStatus.OK, "Student Details", studentDetails);
  }
  
  @PutMapping("/updateStudent/{studentId}")
  public ResponseEntity<Object> updateStudentDetails(@PathVariable Long studentId,
		   @RequestBody Users users) {
    Users studentDetails = usersService.updateStudentDetails(studentId, users);
    return ResponseHandler.generateResponse("Successfully Updated",
		HttpStatus.OK, "Student Details", studentDetails);  
  }
  
  @DeleteMapping("/deleteStudent/{studentId}")
  public ResponseEntity<Object> deleteStudent(@PathVariable Long studentId) {
    usersService.deleteStudent(studentId);
    return ResponseHandler.generateResponse("Successfully Deleted",
		HttpStatus.OK, "Student Details", null);
  }
}
