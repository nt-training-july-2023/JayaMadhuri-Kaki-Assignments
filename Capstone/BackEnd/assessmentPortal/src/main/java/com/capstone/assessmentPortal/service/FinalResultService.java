package com.capstone.assessmentPortal.service;

import java.util.List;
import java.util.Optional;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;

public interface FinalResultService {
  List<FinalResultsOfQuiz> getAllFinalResults();
  List<FinalResultsOfQuiz> getFinalResultByStudentId(Long studentId);
  Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(Long studentId,
		  String subCategoryName);
}
