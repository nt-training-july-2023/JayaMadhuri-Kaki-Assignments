package com.capstone.assessmentPortal.service;

import java.util.List;
import java.util.Optional;

import com.capstone.assessmentPortal.model.SubCategory;

public interface SubCategoryService {
  SubCategory addSubCategory(SubCategory subCategory);
  List<SubCategory> getAllSubCategories();
  Optional<SubCategory> getSubCategoryById(Long subCategoryId);
  SubCategory updateSubCategory(SubCategory subCategory, Long subCategoryId);
  void deleteSubCategory(Long subCategoryId);
  List<SubCategory> getSubCategoryByCategoryId(Long categoryId);
}
