package com.capstone.assessmentportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentportal.model.Users;

/**
 *users repository.
*/

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
  /**
   *query to find user by given email id.
   *@return users
   *@param emailId emailId
  */
   Optional<Users> findUserByEmailId(String emailId);
}
