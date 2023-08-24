package com.capstone.assessmentPortal.controller;

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

@RestController
public class UsersController {
  @Autowired
  UsersService usersService;
  
  @PostMapping("/studentRegister")
  public ResponseEntity<Object> studentRegistration(@RequestBody Users users){
    try {
	  Users studentDetails = usersService.studentRegistration(users);
	  return ResponseHandler.generateResponse("Successfully Registered",
			HttpStatus.OK, "User", studentDetails);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
			HttpStatus.MULTI_STATUS, "User", "Not a valid Email or Password");
    }
  }
  
  @PostMapping("/userLogin")
  public ResponseEntity<Object> userLogin(@RequestBody Users users){
    try {
	  Users userDetails = usersService.authenticateUser(users);
	  return ResponseHandler.generateResponse("Login Successfull",
			HttpStatus.OK, "User", userDetails);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
			HttpStatus.MULTI_STATUS, "User", null);
    }  
  }
  
  @GetMapping("/getUser/{studentId}")
  public ResponseEntity<Object> getStudentById(@PathVariable Long studentId){
    try {
	  Optional<Users> studentDetails = usersService.getStudentById(studentId);
	  return ResponseHandler.generateResponse("Successfully Retrieved",
			HttpStatus.OK, "Student", studentDetails);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
			HttpStatus.MULTI_STATUS, "Student", null);
    }  
  }
  
  @PutMapping("/updateStudent/{studentId}")
  public ResponseEntity<Object> updateStudentDetails(@PathVariable Long studentId,@RequestBody Users users){
    try {
	  Users studentDetails = usersService.updateStudentDetails(studentId, users);
	  return ResponseHandler.generateResponse("Successfully Updated",
			HttpStatus.OK, "Student", studentDetails);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
			HttpStatus.MULTI_STATUS, "Student", null);
    }  
  }
  
  @DeleteMapping("/deleteStudent/{studentId}")
  public ResponseEntity<Object> deleteStudent(@PathVariable Long studentId){
    try {
	  usersService.deleteStudent(studentId);
	  return ResponseHandler.generateResponse("Successfully Retrieved",
			HttpStatus.OK, "Student", null);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
			HttpStatus.MULTI_STATUS, "Student", null);
    }  
  }
}
