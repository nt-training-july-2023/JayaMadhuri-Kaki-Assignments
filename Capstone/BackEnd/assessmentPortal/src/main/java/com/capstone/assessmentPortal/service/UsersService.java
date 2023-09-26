package com.capstone.assessmentPortal.service;

import java.util.Map;

import com.capstone.assessmentPortal.dto.LoginRequest;
import com.capstone.assessmentPortal.dto.SignUpRequest;
import com.capstone.assessmentPortal.dto.UserDetails;
import com.capstone.assessmentPortal.dto.UserDetailsForUpdate;

/**
 * Users service interface.
*/

public interface UsersService {
  /**
   * registeration of students.
   * @return users
   * @param signUpRequest signUpRequest
  */
  String studentRegistration(SignUpRequest signUpRequest);
  /**
   * authenticate user by credentials.
   * @return authenticatedUser
   * @param loginRequest loginRequest
  */
  Map<String, String> authenticateUser(LoginRequest loginRequest);
  /**
   * delete student by id.
   * @param studentId studentId
  */
  void deleteStudent(Long studentId);
  /**
   * update student details by id and new details.
   * @return studentDetails
   * @param studentId studentId
   * @param users users
  */
  UserDetailsForUpdate updateStudentDetails(Long studentId,
          UserDetailsForUpdate users);
  /**
   * get student by student id.
   * @return student
   * @param studentId studentId
  */
  UserDetails getStudentById(Long studentId);
  /**
   * get student by email id.
   * @return student
   * @param emailId emailId
  */
  UserDetails getStudentDetailsByEmail(String emailId);
  /**
   * get users by email id.
   * @return string
   * @param emailId emailId
  */
  String getUsersDetailsByEmail(String emailId);
}
