package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.model.Results;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.repository.ResultRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.repository.UsersRepo;
import com.capstone.assessmentPortal.service.ResultService;

@Service
public class ResultServiceImplementation implements ResultService{
  @Autowired
  ResultRepo resultRepo;
  @Autowired
  FinalResultOfQuizRepo finalResultsRepo;
  @Autowired
  UsersRepo usersRepo;
  @Autowired
  SubCategoryRepo subCategoryRepo;
  @Autowired
  CategoryRepo categoryRepo;

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
    Users existinguser = usersRepo.findById(results.getStudents().getUserId()).orElse(null);
    if(existinguser == null) {
	  throw new NoSuchElementException();
    }else {
	  SubCategory existingquiz = subCategoryRepo.findById(results.getSubCategory().getSubCategoryId()).orElse(null);
	  if(existingquiz!=null) {
        Category existingCategory = categoryRepo.findById(results.getSubCategory().getCategory().getCategoryId()).orElse(null);
	    if(existingCategory != null) {
          finalResultsRepo.save(finalResults);
          return resultRepo.save(results);
	    }else {
		   throw new NoSuchElementException();
	    }
	  }else {
	    throw new NoSuchElementException();
	  }
    }
  }
}
