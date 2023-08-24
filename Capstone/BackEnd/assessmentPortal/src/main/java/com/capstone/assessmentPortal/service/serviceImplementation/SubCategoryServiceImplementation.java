package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.service.SubCategoryService;

@Service
public class SubCategoryServiceImplementation implements SubCategoryService{
  @Autowired
  SubCategoryRepo subCategoryRepo;

  @Override
  public SubCategory addSubCategory(SubCategory subCategory) {
    return subCategoryRepo.save(subCategory);
  }

  @Override
  public List<SubCategory> getAllSubCategories() {
    return subCategoryRepo.findAll();
  }

  @Override
  public Optional<SubCategory> getSubCategoryById(Long subCategoryId) {
    return subCategoryRepo.findById(subCategoryId);
  }

  @Override
  public SubCategory updateSubCategory(SubCategory subCategory, Long subCategoryId) {
	SubCategory existingquiz = subCategoryRepo.findById(subCategoryId).orElse(null);
	if(existingquiz!=null) {
	  existingquiz.setSubCategoryName(subCategory.getSubCategoryName());
	  existingquiz.setSubCategoryDescription(subCategory.getSubCategoryDescription());
	  existingquiz.setTimeLimitInMinutes(subCategory.getTimeLimitInMinutes());
	  existingquiz.setAttempted(subCategory.isAttempted());
	  return subCategoryRepo.save(existingquiz);
	}
	return existingquiz;
  }

  @Override
  public void deleteSubCategory(Long subCategoryId) {
	  subCategoryRepo.deleteById(subCategoryId);
  }

  @Override
  public List<SubCategory> getSubCategoryByCategoryId(Long categoryId) {
    return subCategoryRepo.getSubCategoryByCategoryId(categoryId);
  }
  
}
