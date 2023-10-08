package com.capstone.assessmentportal.service;

import java.util.List;

import com.capstone.assessmentportal.dto.ResultsDto;

/**
 * Result service interface.
*/

public interface ResultService {
  /**
   * adding temporary result.
   * @return temporaryResult
   * @param results results
  */
  ResultsDto addResult(ResultsDto results);
  /**
   * get all results from final result table.
   * @return list of finalresults
  */
  List<ResultsDto> getResults();
  /**
   * get result by student emailId.
   * @return list of final results
   * @param emailId emailId
  */
  List<ResultsDto> getResultByStudentEmail(String emailId);
}
