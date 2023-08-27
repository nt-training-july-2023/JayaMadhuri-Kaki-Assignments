package com.capstone.assessmentPortal.service;

import java.util.List;

import com.capstone.assessmentPortal.model.Category;

public interface CategoryService {
  Category addCategory(Category category);
  List<Category> getAllCategories();
  Category getCategoryById(Long categoryId);
  void deleteCategory(Long categoryId);
  Category updateCategory(Long categoryId, Category category);
}
