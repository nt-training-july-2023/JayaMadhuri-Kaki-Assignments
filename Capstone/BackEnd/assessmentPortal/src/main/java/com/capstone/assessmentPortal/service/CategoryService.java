package com.capstone.assessmentPortal.service;

import java.util.List;

import com.capstone.assessmentPortal.model.Category;

/**
 *category service interface.
*/

public interface CategoryService {
  /**
   *adding category.
   *@return category
   *@param category category
  */
  Category addCategory(Category category);
  /**
   *get all categories in category table.
   *@return list of categories
  */
  List<Category> getAllCategories();
  /**
   *get category by category id.
   *@return category
   *@param categoryId categoryId
  */
  Category getCategoryById(Long categoryId);
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
  Category updateCategory(Long categoryId, Category category);
}
