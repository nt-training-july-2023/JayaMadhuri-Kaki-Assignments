package com.capstone.assessmentPortal.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.model.Results;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.repository.ResultRepo;
import com.capstone.assessmentPortal.service.ResultService;

@Service
public class ResultServiceImplementation implements ResultService{
  @Autowired
  ResultRepo resultRepo;
  @Autowired
  FinalResultOfQuizRepo finalResultsRepo;

  @Override
  public Results addTemporaryResult(Results results) {
    FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz();
    finalResults.setStudentId(results.getStudents().getUserId());
    finalResults.setStudentName(results.getStudents().getFirstName()
    		+results.getStudents().getLastName());
    finalResults.setCategoryName(results.getSubCategory().getCategory().getCategoryName());
    finalResults.setQuizName(results.getSubCategory().getSubCategoryName());
    finalResults.setMarksObtained(results.getMarksObtained());
    finalResults.setTotalMarks(results.getTotalMarks());
    finalResults.setNumOfAttemptedQuestions(results.getNumOfAttemptedQuestions());
    finalResults.setTotalQuestions(results.getTotalQuestions());
    finalResults.setDateAndTime(results.getDateAndTime());
    finalResultsRepo.save(finalResults);
    return resultRepo.save(results);
  }
  
}
