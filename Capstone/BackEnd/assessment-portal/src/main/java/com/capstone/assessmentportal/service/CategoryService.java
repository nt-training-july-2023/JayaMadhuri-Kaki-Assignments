package com.capstone.assessmentportal.service;

import java.util.List;

import com.capstone.assessmentportal.dto.CategoryDetailsDto;

/**
 *category service interface.
*/

public interface CategoryService {
  /**
   *adding category.
   *@return category
   *@param category category
  */
  CategoryDetailsDto addCategory(CategoryDetailsDto category);
  /**
   *get all categories in category table.
   *@return list of categories
  */
  List<CategoryDetailsDto> getCategories();
  /**
   *get category by category id.
   *@return category
   *@param categoryId categoryId
  */
  CategoryDetailsDto getCategoryById(Long categoryId);
  /**
   *delete category by category id.
   *@param categoryId categoryId
  */
  void deleteCategory(Long categoryId);
  /**
   *update category by id and given details.
   *@return category
   *@param categoryId categoryId
   *@param category category
  */
  CategoryDetailsDto updateCategory(Long categoryId,
                     CategoryDetailsDto category);
}
