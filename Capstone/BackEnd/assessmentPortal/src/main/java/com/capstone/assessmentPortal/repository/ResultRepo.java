package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Results;
/**
 *Temporary Result repository.
*/

@Repository
public interface ResultRepo extends JpaRepository<Results, Long> {
  /**
   *Temporary Result repository.
   *@return results
   *@param userId userId
   *@param subCategoryId subCategoryId
  */
  @Query("select r from Results as r where r.students.userId = :userId "
            + "and r.subCategory.subCategoryId = :subCategoryId")
  Results findResultsByStudentsAndSubCategory(Long userId, Long subCategoryId);
}
