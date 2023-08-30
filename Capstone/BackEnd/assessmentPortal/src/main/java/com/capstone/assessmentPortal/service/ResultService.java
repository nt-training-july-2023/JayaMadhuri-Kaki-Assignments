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
  /**
   * adding temporary result.
   * @return results
   * @param userId userId
   * @param subCategoryId subCategoryId
  */
  boolean findResultsByUserAndSubCategory(Long userId,
             Long subCategoryId);
}
