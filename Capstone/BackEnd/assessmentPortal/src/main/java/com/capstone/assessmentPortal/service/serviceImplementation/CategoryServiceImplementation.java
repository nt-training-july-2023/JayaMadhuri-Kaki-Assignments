package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService{
  @Autowired
  CategoryRepo categoryRepo;

  @Override
  public Category addCategory(Category category) {
	Optional<Category> existingCategory = categoryRepo.getCategoryByName(category.getCategoryName());
	if(existingCategory.isPresent()) {
		throw new AlreadyExistsException();
	}else {
	  if(category.getCategoryName() == "") {
		throw new InputEmptyException();
	  }
      return categoryRepo.save(category);
	}
  }

  @Override
  public List<Category> getAllCategories() {
	List<Category> listOfCategories = categoryRepo.findAll();
	if(listOfCategories.size() == 0) {
	  throw new EmptyListException();
	}else {
	  return listOfCategories;
	}
  }

  @Override
  public Category getCategoryById(Long categoryId) {
    return categoryRepo.findById(categoryId).orElseThrow(()->new NoSuchElementException("Cannot find category with id: "+categoryId));
  }

  @Override
  public void deleteCategory(Long categoryId) {
	Category existingCategory = categoryRepo.findById(categoryId).orElse(null);
	if(existingCategory == null) {
	  throw new NoSuchElementException();
	}else {
      categoryRepo.deleteById(categoryId);
	}
  }

  @Override
  public Category updateCategory(Long categoryId, Category category) {
    Category existingCategory = categoryRepo.findById(categoryId).orElse(null);
	if(existingCategory!=null) {
	  existingCategory.setCategoryName(category.getCategoryName());
	  existingCategory.setCategoryDescription(category.getCategoryDescription());
	  if(existingCategory.getCategoryName() == "") {
	    throw new InputEmptyException();
	  }
	  return categoryRepo.save(existingCategory);
	}else {
	  throw new NoSuchElementException();
	}
  }
}
