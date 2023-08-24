package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService{
  @Autowired
  CategoryRepo categoryRepo;

  @Override
  public Category addCategory(Category category) {
    return categoryRepo.save(category);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepo.findAll();
  }

  @Override
  public Optional<Category> getCategoryById(Long categoryId) {
    return categoryRepo.findById(categoryId);
  }

  @Override
  public void deleteCategory(Long categoryId) {
    categoryRepo.deleteById(categoryId);
  }

  @Override
  public Category updateCategory(Long categoryId, Category category) {
    Category existingCategory = categoryRepo.findById(categoryId).orElseThrow();
	if(existingCategory!=null) {
	  existingCategory.setCategoryName(category.getCategoryName());
	  existingCategory.setCategoryDescription(category.getCategoryDescription());
	  return categoryRepo.save(existingCategory);
	}else {
	  return null;
	}
  }
}
