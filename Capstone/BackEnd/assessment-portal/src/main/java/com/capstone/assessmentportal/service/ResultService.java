package com.capstone.assessmentportal.service;

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
  ResultsDto addTemporaryResult(ResultsDto results);
}
