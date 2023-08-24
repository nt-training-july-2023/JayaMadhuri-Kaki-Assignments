package com.capstone.assessmentPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Results;

@Repository
public interface ResultRepo extends JpaRepository<Results, Long> {
  
}
