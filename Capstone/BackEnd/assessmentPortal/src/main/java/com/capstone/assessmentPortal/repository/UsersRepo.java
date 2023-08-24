package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

}
