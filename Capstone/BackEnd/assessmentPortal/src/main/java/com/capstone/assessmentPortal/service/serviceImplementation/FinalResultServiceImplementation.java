package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.exception.EmptyListException;
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
  @Override
  public final List<FinalResultsOfQuiz> getAllFinalResults() {
    List<FinalResultsOfQuiz> listOfFinalResults = finalResultRepo.findAll();
    if (listOfFinalResults.size() == 0) {
       throw new EmptyListException();
    } else {
      return listOfFinalResults;
    }
  }
  @Override
  public final List<FinalResultsOfQuiz> getFinalResultByStudentId(
               final Long studentId) {
    List<FinalResultsOfQuiz> listOfFinalResults =
    finalResultRepo.getFinalResultsByStudentId(studentId);
    if (listOfFinalResults.size() == 0) {
      throw new EmptyListException();
    } else {
      return listOfFinalResults;
    }
  }
  @Override
  public final Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(
               final Long studentId, final String subCategoryName) {
    Optional<FinalResultsOfQuiz> finalResults = finalResultRepo
             .getFinalResultsByStudentIdQuizName(studentId, subCategoryName);
    if (finalResults.get() == null) {
      throw new EmptyListException();
    } else {
      return finalResults;
    }
  }
}
