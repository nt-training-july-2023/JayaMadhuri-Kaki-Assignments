package com.capstone.assessmentPortal.service;

import java.util.Map;

import com.capstone.assessmentPortal.model.Users;

/**
 * Users service interface.
*/

public interface UsersService {
  /**
   * registeration of students.
   * @return users
   * @param users users
  */
  String studentRegistration(Users users);
  /**
   * authenticate user by credentials.
   * @return authenticatedUser
   * @param users users
  */
  Map<String, String> authenticateUser(Users users);
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
  Users updateStudentDetails(Long studentId, Users users);
  /**
   * get student by student id.
   * @return student
   * @param studentId studentId
  */
  Users getStudentById(Long studentId);
}
