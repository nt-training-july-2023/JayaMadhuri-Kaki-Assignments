package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.FinalResultsDto;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
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
   * parameter constructor.
   * @param finalResultsRepo2 finalResultsRepo2
  */
  public FinalResultServiceImplementation(final
          FinalResultOfQuizRepo finalResultsRepo2) {
    this.finalResultRepo = finalResultsRepo2;
}
@Override
  public final List<FinalResultsDto> getAllFinalResults() {
    List<FinalResultsOfQuiz> listOfFinalResults = finalResultRepo.findAll();
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
    FinalResultsDto finalDto = new FinalResultsDto();
    finalDto.setFinalResultId(finalResults.getFinalResultId());
    finalDto.setStudentId(finalResults.getStudentId());
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
  public final List<FinalResultsDto> getFinalResultByStudentId(
               final Long studentId) {
    List<FinalResultsOfQuiz> listOfFinalResults =
    finalResultRepo.getFinalResultsByStudentId(studentId);
      return listOfFinalResults.stream()
              .map(this::convertEntityToDto)
              .collect(Collectors.toList());
  }
  @Override
  public final Optional<FinalResultsDto> getFinalResultsByStudentIdQuizName(
               final Long studentId, final String subCategoryName) {
    Optional<FinalResultsOfQuiz> finalResults = finalResultRepo
             .getFinalResultsByStudentIdQuizName(studentId, subCategoryName);
      FinalResultsOfQuiz results = finalResults.get();
      FinalResultsDto finalDto = new FinalResultsDto();
      finalDto.setFinalResultId(results.getFinalResultId());
      finalDto.setStudentId(studentId);
      finalDto.setStudentName(results.getStudentName());
      finalDto.setQuizName(results
                .getQuizName());
      finalDto.setCategoryName(results
              .getCategoryName());
      finalDto.setMarksObtained(results
              .getMarksObtained());
      finalDto.setNumOfAttemptedQuestions(results
              .getNumOfAttemptedQuestions());
      finalDto.setTotalMarks(results.getTotalMarks());
      finalDto.setTotalQuestions(results.getTotalQuestions());
      finalDto.setDateAndTime(results.getDateAndTime());
      return Optional.of(finalDto);
  }
}
