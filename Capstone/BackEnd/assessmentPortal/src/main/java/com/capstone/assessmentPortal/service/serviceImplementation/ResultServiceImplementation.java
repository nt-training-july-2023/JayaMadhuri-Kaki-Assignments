package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.ResultsDto;
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
  /**
   * parameter constructor.
   * @param subCategoryRepo2 subCategoryRepo2
   * @param usersRepo2 usersRepo2
   * @param resultRepo2 resultRepo2
   * @param categoryRepo2 categoryRepo2
   * @param finalResultRepo finalResultRepo
  */
  public ResultServiceImplementation(final SubCategoryRepo subCategoryRepo2,
          final UsersRepo usersRepo2, final ResultRepo resultRepo2,
          final CategoryRepo categoryRepo2,
          final FinalResultOfQuizRepo finalResultRepo) {
   this.subCategoryRepo = subCategoryRepo2;
   this.usersRepo = usersRepo2;
   this.resultRepo = resultRepo2;
   this.categoryRepo = categoryRepo2;
   this.finalResultsRepo = finalResultRepo;
}
@Override
  public final ResultsDto addTemporaryResult(final ResultsDto resultsDto) {
    Users user = usersRepo.findById(resultsDto
            .getStudentId()).orElseThrow(
                    () -> new NoSuchElementException());
    SubCategory quiz = subCategoryRepo.findById(
             resultsDto.getSubCategoryId()).orElseThrow(
                     () -> new NoSuchElementException());
    logger.info("Result Added");
    FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz();
    finalResults.setStudentId(resultsDto.getStudentId());
    Optional<Users> users = usersRepo.findById(resultsDto
           .getStudentId());
    finalResults.setStudentEmailId(users.get().getEmailId());
    finalResults.setStudentName(users.get().getFirstName()
           + users.get().getLastName());
    Optional<SubCategory> subCategory = subCategoryRepo
           .findById(resultsDto.getSubCategoryId());
    Optional<Category> category = categoryRepo.findById(resultsDto
           .getCategoryId());
    finalResults.setCategoryName(category.get().getCategoryName());
    finalResults.setQuizName(subCategory.get().getSubCategoryName());
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
