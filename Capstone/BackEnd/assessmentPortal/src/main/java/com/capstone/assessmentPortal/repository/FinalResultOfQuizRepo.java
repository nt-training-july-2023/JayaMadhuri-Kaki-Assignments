package com.capstone.assessmentPortal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;

@Repository
public interface FinalResultOfQuizRepo extends JpaRepository<FinalResultsOfQuiz, Long> {
	
  @Query("select f from FinalResultsOfQuiz as f where"
  		+ " f.studentId = :studentId and f.quizName = :subCategoryName")
  Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(Long studentId,
		  String subCategoryName);
  
  @Query("select f from FinalResultsOfQuiz as f where f.studentId = :studentId")
  List<FinalResultsOfQuiz> getFinalResultsByStudentId(Long studentId);
  
}
