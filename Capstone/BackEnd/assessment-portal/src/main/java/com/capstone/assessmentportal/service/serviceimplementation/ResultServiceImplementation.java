package com.capstone.assessmentportal.service.serviceimplementation;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.model.FinalResultsOfQuiz;
import com.capstone.assessmentportal.model.Results;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.model.Users;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentportal.repository.ResultRepo;
import com.capstone.assessmentportal.repository.SubCategoryRepo;
import com.capstone.assessmentportal.repository.UsersRepo;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.ResultService;

/**
 * Result service implementation class.
*/

@Service
public class ResultServiceImplementation implements ResultService {
  /**
   * autowiring result repository.
  */
  @Autowired
  private ResultRepo resultRepo;
  /**
   * autowiring finalresult of quiz repository.
  */
  @Autowired
  private FinalResultOfQuizRepo finalResultsRepo;
  /**
   * autowiring users repository.
  */
  @Autowired
  private UsersRepo usersRepo;
  /**
   * autowiring Category repository.
  */
  @Autowired
  private CategoryRepo categoryRepo;
  /**
   * autowiring subcategory repository.
  */
  @Autowired
  private SubCategoryRepo subCategoryRepo;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(
          ResultServiceImplementation.class);
@Override
  public final ResultsDto addTemporaryResult(final ResultsDto resultsDto) {
    Users user = usersRepo.findById(resultsDto
            .getStudentId()).orElseThrow(
                    () -> new NoSuchElementException(ValidationMessage
                            .USER_NOSUCHELEMENT));
    SubCategory quiz = subCategoryRepo.findById(
             resultsDto.getSubCategoryId()).orElseThrow(
                     () -> new NoSuchElementException(ValidationMessage
                             .QUIZ_NOSUCHELEMENT));
    Category category = categoryRepo.findById(
            resultsDto.getCategoryId()).orElseThrow(
                    () -> new NoSuchElementException(ValidationMessage
                            .CATEGORY_NOSUCHELEMENT));
    logger.info(ValidationMessage.RESULTS_ADDED);
    FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz();
    finalResults.setStudentId(resultsDto.getStudentId());
    finalResults.setStudentEmailId(user.getEmailId());
    finalResults.setStudentName(user.getFirstName() + " "
           + user.getLastName());
    finalResults.setCategoryName(category.getCategoryName());
    finalResults.setQuizName(quiz.getSubCategoryName());
    finalResults.setMarksObtained(resultsDto.getMarksObtained());
    finalResults.setTotalMarks(resultsDto.getTotalMarks());
    finalResults.setNumOfAttemptedQuestions(resultsDto
            .getNumOfAttemptedQuestions());
    finalResults.setTotalQuestions(resultsDto.getTotalQuestions());
    finalResults.setDateAndTime(resultsDto.setDateAndTimeMethod());
    finalResultsRepo.save(finalResults);
    Results results = new Results();
    results.setStudents(user);
    results.setSubCategory(quiz);
    results.setTotalMarks(resultsDto.getTotalMarks());
    results.setMarksObtained(resultsDto.getMarksObtained());
    results.setNumOfAttemptedQuestions(resultsDto
             .getNumOfAttemptedQuestions());
    results.setTotalQuestions(resultsDto.getTotalQuestions());
    results.setDateAndTime(resultsDto.setDateAndTimeMethod());
    resultRepo.save(results);
    return resultsDto;
  }
}