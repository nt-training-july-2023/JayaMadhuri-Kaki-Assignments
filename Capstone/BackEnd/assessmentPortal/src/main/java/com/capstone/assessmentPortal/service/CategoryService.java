package com.capstone.assessmentPortal.service;

import java.util.List;
import java.util.Optional;

import com.capstone.assessmentPortal.model.Category;

public interface CategoryService {
  Category addCategory(Category category);
  List<Category> getAllCategories();
  Optional<Category> getCategoryById(Long categoryId);
  void deleteCategory(Long categoryId);
  Category updateCategory(Long categoryId, Category category);
}
