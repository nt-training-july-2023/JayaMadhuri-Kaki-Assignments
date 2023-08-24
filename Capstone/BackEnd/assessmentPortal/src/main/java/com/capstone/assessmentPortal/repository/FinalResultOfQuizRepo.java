package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;

@Repository
public interface FinalResultOfQuizRepo extends JpaRepository<FinalResultsOfQuiz, Long> {

}
