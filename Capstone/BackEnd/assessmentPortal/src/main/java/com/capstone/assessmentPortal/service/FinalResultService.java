package com.capstone.assessmentPortal.service;

import java.util.List;

import java.util.Optional;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;

/**
 * Final result of quiz service interface.
*/

public interface FinalResultService {
  /**
   * get all final results from final result table.
   * @return list of finalresults
  */
  List<FinalResultsOfQuiz> getAllFinalResults();
  /**
   * get final result by student id.
   * @return list of final results
   * @param studentId studentId
  */
  List<FinalResultsOfQuiz> getFinalResultByStudentId(Long studentId);
  /**
   * get final result by student id and quiz .
   * @return finalresult
   * @param studentId studentId
   * @param subCategoryName subCategoryName
  */
  Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(
          Long studentId,
          String subCategoryName);
}
