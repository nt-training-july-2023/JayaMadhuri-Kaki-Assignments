package com.capstone.assessmentPortal.service;

import java.util.Optional;

import com.capstone.assessmentPortal.model.Users;

public interface UsersService {
  Users studentRegistration(Users users);
  Users authenticateUser(Users users);
  void deleteStudent(Long studentId);
  Users updateStudentDetails(Long studentId, Users users);
  Optional<Users> getStudentById(Long studentId);
}
