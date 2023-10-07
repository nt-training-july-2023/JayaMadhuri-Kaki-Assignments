package com.capstone.assessmentportal.service.serviceimplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.model.Results;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.model.Users;
import com.capstone.assessmentportal.repository.CategoryRepo;
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
    SubCategory quiz = subCategoryRepo.getSubCategoryByName(
             resultsDto.getQuizName()).orElseThrow(
                     () -> new NoSuchElementException(ValidationMessage
                             .QUIZNAME_NOTFOUND + " "
                             + resultsDto.getQuizName()));
    Category category = categoryRepo.getCategoryByName(
            resultsDto.getCategoryName()).orElseThrow(
    () -> new NoSuchElementException(ValidationMessage.CATEGORYNAME_NOTFOUND
            + " " + resultsDto.getCategoryName()));
    logger.info(ValidationMessage.RESULTS_ADDED);
    Results results = new Results();
    results.setStudentId(user.getUserId());
    results.setStudentName(user.getFirstName() + " " + user.getLastName());
    results.setStudentEmailId(user.getEmailId());
    results.setCategoryName(category.getCategoryName());
    results.setQuizName(quiz.getSubCategoryName());
    results.setMarksObtained(resultsDto.getMarksObtained());
    results.setTotalMarks(resultsDto.getTotalMarks());
    results.setNumOfAttemptedQuestions(resultsDto
             .getNumOfAttemptedQuestions());
    results.setTotalQuestions(resultsDto.getTotalQuestions());
    results.setDateAndTime(resultsDto.setDateAndTimeMethod());
    resultRepo.save(results);
    return resultsDto;
  }

  @Override
  public final List<ResultsDto> getResults() {
    List<Results> listOfResults = resultRepo.findAll();
    logger.info(ValidationMessage.RESULTS_RETRIEVED);
      return listOfResults.stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
  }
  /**
   * converting entity to dto for get all method.
   * @return resultsDto
   * @param Results Results
  */
  private ResultsDto convertEntityToDto(final
        Results Results) {
    logger.info(ValidationMessage.RESULTS_LOGGER_MSG);
    ResultsDto ResultDto = new ResultsDto();
    ResultDto.setResultId(Results.getResultId());
    ResultDto.setStudentId(Results.getStudentId());
    ResultDto.setStudentEmailId(Results.getStudentEmailId());
    ResultDto.setStudentName(Results.getStudentName());
    ResultDto.setQuizName(Results
            .getQuizName());
    ResultDto.setCategoryName(Results
          .getCategoryName());
    ResultDto.setMarksObtained(Results
          .getMarksObtained());
    ResultDto.setNumOfAttemptedQuestions(Results
          .getNumOfAttemptedQuestions());
    ResultDto.setTotalMarks(Results.getTotalMarks());
    ResultDto.setTotalQuestions(Results.getTotalQuestions());
    ResultDto.setDateAndTime(Results.getDateAndTime());
    return ResultDto;
  }
  
  @Override
  public final List<ResultsDto> getResultByStudentEmail(final
        String emailId) {
    List<Results> listOfResults =
            resultRepo.getResultsByStudentEmail(emailId);
    if (listOfResults.isEmpty()) {
        throw new NoSuchElementException(ValidationMessage.RESULTS_EMAIL);
    }
    logger.info(ValidationMessage.RESULTS_RETRIEVED);
              return listOfResults.stream()
                      .map(this::convertEntityToDto)
                      .collect(Collectors.toList());
  }
}
