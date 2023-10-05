package com.capstone.assessmentportal.service;

import java.util.List;

import com.capstone.assessmentportal.dto.FinalResultsDto;
/**
 * Final result of quiz service interface.
*/

public interface FinalResultService {
  /**
   * get all final results from final result table.
   * @return list of finalresults
  */
  List<FinalResultsDto> getFinalResults();
  /**
   * get final result by student emailId.
   * @return list of final results
   * @param emailId emailId
  */
  List<FinalResultsDto> getFinalResultByStudentEmail(String emailId);
}
