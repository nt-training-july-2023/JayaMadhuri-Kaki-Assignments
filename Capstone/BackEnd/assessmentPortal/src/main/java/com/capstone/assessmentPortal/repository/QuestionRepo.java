package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

}
