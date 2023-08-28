package com.capstone.assessmentPortal.service;

import java.util.Map;

import com.capstone.assessmentPortal.model.Users;

public interface UsersService {
  String studentRegistration(Users users);
  Map<String, String> authenticateUser(Users users);
  void deleteStudent(Long studentId);
  Users updateStudentDetails(Long studentId, Users users);
  Users getStudentById(Long studentId);
}
