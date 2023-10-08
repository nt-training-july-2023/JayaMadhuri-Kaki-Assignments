package com.capstone.assessmentportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentportal.model.SubCategory;

/**
 *SubCategory repository.
*/

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
  /**
   * query to get records from subcategory table when given category id exists.
   * @return subCategories
   * @param categoryId categoryId
  */
  @Query("Select s from SubCategory as s "
          + "where s.category.categoryId = :categoryId")
  List<SubCategory> getSubCategoryByCategoryId(Long categoryId);
  /**
   *query to get records from subcategory table
   * when given category name exists.
   *@return subCategories
   *@param subCategoryName subCategoryName
  */
  @Query("select c from SubCategory as c"
            + " where c.subCategoryName = :subCategoryName")
  Optional<SubCategory> getSubCategoryByName(String subCategoryName);
}
