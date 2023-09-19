package com.capstone.assessmentPortal.service;

import com.capstone.assessmentPortal.dto.ResultsDto;

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
