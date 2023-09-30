package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.FinalResultsDto;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.response.ValidationMessage;
import com.capstone.assessmentPortal.service.FinalResultService;


/**
 * Final result service implementation class.
*/

@Service
public class FinalResultServiceImplementation implements FinalResultService {

  /**
   * autowiring finalresult of quiz repository.
  */
  @Autowired
  private FinalResultOfQuizRepo finalResultRepo;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(
          FinalResultServiceImplementation.class);
@Override
  public final List<FinalResultsDto> getFinalResults() {
    List<FinalResultsOfQuiz> listOfFinalResults = finalResultRepo.findAll();
    logger.info(ValidationMessage.RESULTS_RETRIEVED);
      return listOfFinalResults.stream()
              .map(this::convertEntityToDto)
              .collect(Collectors.toList());
  }
  /**
   * converting entity to dto for get all method.
   * @return finalDto
   * @param finalResults finalResults
  */
  private FinalResultsDto convertEntityToDto(final
          FinalResultsOfQuiz finalResults) {
    logger.info(ValidationMessage.RESULTS_LOGGER_MSG);
    FinalResultsDto finalDto = new FinalResultsDto();
    finalDto.setFinalResultId(finalResults.getFinalResultId());
    finalDto.setStudentId(finalResults.getStudentId());
    finalDto.setStudentEmailId(finalResults.getStudentEmailId());
    finalDto.setStudentName(finalResults.getStudentName());
    finalDto.setQuizName(finalResults
              .getQuizName());
    finalDto.setCategoryName(finalResults
            .getCategoryName());
    finalDto.setMarksObtained(finalResults
            .getMarksObtained());
    finalDto.setNumOfAttemptedQuestions(finalResults
            .getNumOfAttemptedQuestions());
    finalDto.setTotalMarks(finalResults.getTotalMarks());
    finalDto.setTotalQuestions(finalResults.getTotalQuestions());
    finalDto.setDateAndTime(finalResults.getDateAndTime());
    return finalDto;
  }
  @Override
  public final List<FinalResultsDto> getFinalResultByStudentEmail(final
          String emailId) {
      List<FinalResultsOfQuiz> listOfFinalResults =
              finalResultRepo.getFinalResultsByStudentEmail(emailId);
      logger.info(ValidationMessage.RESULTS_RETRIEVED);
                return listOfFinalResults.stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList());
  }
}
