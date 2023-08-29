package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Results;

/**
 *Temporary Result repository.
*/

@Repository
public interface ResultRepo extends JpaRepository<Results, Long> {
}
