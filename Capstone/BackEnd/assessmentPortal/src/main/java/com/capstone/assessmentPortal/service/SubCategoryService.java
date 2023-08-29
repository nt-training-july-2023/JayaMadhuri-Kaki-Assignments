package com.capstone.assessmentPortal.service;

import java.util.List;

import com.capstone.assessmentPortal.model.SubCategory;

/**
 * subCategory service interface.
*/

public interface SubCategoryService {
  /**
   * adding subcategory to table.
   * @return subCategory
   * @param subCategory subCategory
  */
  SubCategory addSubCategory(SubCategory subCategory);
  /**
   * get list of all subcategories in table.
   * @return list of subcategories
  */
  List<SubCategory> getAllSubCategories();
  /**
   * get subcategory by id.
   * @return subCategory
   * @param subCategoryId subCategoryId
  */
  SubCategory getSubCategoryById(Long subCategoryId);
  /**
   * update subcategory by id and given details.
   * @return subCategory
   * @param subCategory subCategory
   * @param subCategoryId subCategoryId
  */
  SubCategory updateSubCategory(SubCategory subCategory,
                 Long subCategoryId);
  /**
   * delete subcategory by id.
   * @param subCategoryId subCategoryId
  */
  void deleteSubCategory(Long subCategoryId);
  /**
   * list of subcategories by category id.
   * @return subCategory
   * @param categoryId categoryId
  */
  List<SubCategory> getSubCategoryByCategoryId(Long categoryId);
}
