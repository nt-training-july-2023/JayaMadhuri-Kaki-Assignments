package com.capstone.assessmentportal.service;

import java.util.List;

import com.capstone.assessmentportal.dto.SubCategoryDetailsDto;

/**
 * subCategory service interface.
*/

public interface SubCategoryService {
  /**
   * adding subcategory to table.
   * @return subCategory
   * @param subCategory subCategory
  */
  SubCategoryDetailsDto addSubCategory(SubCategoryDetailsDto subCategory);
  /**
   * get list of all subcategories in table.
   * @return list of subcategories
  */
  List<SubCategoryDetailsDto> getSubCategories();
  /**
   * get subcategory by id.
   * @return subCategory
   * @param subCategoryId subCategoryId
  */
  SubCategoryDetailsDto getSubCategoryById(Long subCategoryId);
  /**
   * update subcategory by id and given details.
   * @return subCategory
   * @param subCategory subCategory
   * @param subCategoryId subCategoryId
  */
  SubCategoryDetailsDto updateSubCategory(SubCategoryDetailsDto subCategory,
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
  List<SubCategoryDetailsDto> getSubCategoryByCategoryId(Long categoryId);
}
