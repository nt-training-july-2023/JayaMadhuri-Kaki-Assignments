package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.service.FinalResultService;

@Service
public class FinalResultServiceImplementation implements FinalResultService{
  @Autowired
  FinalResultOfQuizRepo finalResultRepo;

  @Override
  public List<FinalResultsOfQuiz> getAllFinalResults() {
	List<FinalResultsOfQuiz> listOfFinalResults = finalResultRepo.findAll();
	if(listOfFinalResults.size() == 0) {
		throw new EmptyListException();
	}else {
      return listOfFinalResults;
	}
  }

  @Override
  public List<FinalResultsOfQuiz> getFinalResultByStudentId(Long studentId) {
	List<FinalResultsOfQuiz> listOfFinalResults = finalResultRepo.getFinalResultsByStudentId(studentId);
	if(listOfFinalResults.size() == 0) {
		throw new EmptyListException();
	}else {
      return listOfFinalResults;
	}
  }

  @Override
  public Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(Long studentId,
		  String subCategoryName) {
	Optional<FinalResultsOfQuiz> finalResults = finalResultRepo.getFinalResultsByStudentIdQuizName(studentId, subCategoryName);
	if(finalResults.get() == null) {
		throw new EmptyListException();
	}else {
      return finalResults;
	}
  }
  
}
