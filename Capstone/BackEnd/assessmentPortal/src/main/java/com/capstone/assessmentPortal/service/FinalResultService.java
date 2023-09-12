package com.capstone.assessmentPortal.service;

import java.util.List;

import java.util.Optional;

import com.capstone.assessmentPortal.dto.FinalResultsDto;
/**
 * Final result of quiz service interface.
*/

public interface FinalResultService {
  /**
   * get all final results from final result table.
   * @return list of finalresults
  */
  List<FinalResultsDto> getAllFinalResults();
  /**
   * get final result by student emailId.
   * @return list of final results
   * @param emailId emailId
  */
  List<FinalResultsDto> getFinalResultByStudentEmail(String emailId);
  /**
   * get final result by student id and quiz .
   * @return finalresult
   * @param studentId studentId
   * @param subCategoryName subCategoryName
  */
  Optional<FinalResultsDto> getFinalResultsByStudentIdQuizName(
          Long studentId,
          String subCategoryName);
}
