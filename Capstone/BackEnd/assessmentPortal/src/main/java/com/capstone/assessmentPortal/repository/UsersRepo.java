package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Users;

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
   Users findUserByEmailId(String emailId);
}
