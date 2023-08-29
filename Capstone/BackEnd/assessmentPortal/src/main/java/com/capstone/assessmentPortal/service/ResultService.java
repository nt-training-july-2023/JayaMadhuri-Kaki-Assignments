package com.capstone.assessmentPortal.service;

import com.capstone.assessmentPortal.model.Results;

/**
 * Result service interface.
*/

public interface ResultService {
  /**
   * adding temporary result.
   * @return temporaryResult
   * @param results results
  */
  Results addTemporaryResult(Results results);
}
