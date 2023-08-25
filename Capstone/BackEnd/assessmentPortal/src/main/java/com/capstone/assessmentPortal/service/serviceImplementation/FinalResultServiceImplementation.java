package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.service.FinalResultService;

@Service
public class FinalResultServiceImplementation implements FinalResultService{
  @Autowired
  FinalResultOfQuizRepo finalResultRepo;

  @Override
  public List<FinalResultsOfQuiz> getAllFinalResults() {
	return finalResultRepo.findAll();
  }

  @Override
  public List<FinalResultsOfQuiz> getFinalResultByStudentId(Long studentId) {
    return finalResultRepo.getFinalResultsByStudentId(studentId);
  }

  @Override
  public Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(Long studentId,
		  String subCategoryName) {
    return finalResultRepo.getFinalResultsByStudentIdQuizName(studentId, subCategoryName);
  }
  
}
