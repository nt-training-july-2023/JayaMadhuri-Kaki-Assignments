package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.exception.NotFoundException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.service.SubCategoryService;

@Service
public class SubCategoryServiceImplementation implements SubCategoryService{
  @Autowired
  SubCategoryRepo subCategoryRepo;
  @Autowired
  CategoryRepo categoryRepo;

  @Override
  public SubCategory addSubCategory(SubCategory subCategory) {
  Optional<SubCategory> existingSubCategory = subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName());
	if(existingSubCategory.isPresent()) {
		throw new AlreadyExistsException();
	}else {
	  if(subCategory.getSubCategoryName() == "") {
		throw new InputEmptyException();
	  }else {
		if(subCategory.getCategory() == null) {
		  throw new NotFoundException();
		}else {
		  if(subCategory.getCategory().getCategoryId() == null) {
		    throw new NotFoundException();
		  }else {
			Category existingCategory = categoryRepo.findById(subCategory.getCategory().getCategoryId()).orElse(null);
			if(existingCategory!=null) {
	        return subCategoryRepo.save(subCategory);
			}else {
			  throw new NotFoundException();
			}
		  }
		}
	  }
	}
  }

  @Override
  public List<SubCategory> getAllSubCategories() {
  List<SubCategory> listOfSubCategories = subCategoryRepo.findAll();
	if(listOfSubCategories.size() == 0) {
	  throw new EmptyListException();
	}else {
      return subCategoryRepo.findAll();
	}
  }

  @Override
  public SubCategory getSubCategoryById(Long subCategoryId) {
    return subCategoryRepo.findById(subCategoryId).orElseThrow(()->new NoSuchElementException("Cannot find Subcategory with id: "+subCategoryId));
  }

  @Override
  public SubCategory updateSubCategory(SubCategory subCategory, Long subCategoryId) {
	SubCategory existingquiz = subCategoryRepo.findById(subCategoryId).orElse(null);
	if(existingquiz!=null) {
	  existingquiz.setSubCategoryName(subCategory.getSubCategoryName());
	  existingquiz.setSubCategoryDescription(subCategory.getSubCategoryDescription());
	  existingquiz.setTimeLimitInMinutes(subCategory.getTimeLimitInMinutes());
	  existingquiz.setAttempted(subCategory.isAttempted());
	  if(existingquiz.getSubCategoryName() == ""|| existingquiz.getTimeLimitInMinutes() == "") {
		    throw new InputEmptyException();
	  }else {
	    Optional<SubCategory> existingSubCategory = subCategoryRepo.getSubCategoryByName(existingquiz.getSubCategoryName());
		if(existingSubCategory.isPresent()) {
			throw new AlreadyExistsException();
		}else {
	    return subCategoryRepo.save(existingquiz);
	    }
	  }
	 }else {
	  throw new NoSuchElementException();
	}
  }

  @Override
  public void deleteSubCategory(Long subCategoryId) {
    SubCategory existingquiz = subCategoryRepo.findById(subCategoryId).orElse(null);
    if(existingquiz == null) {
	  throw new NoSuchElementException();
	}else {
	  subCategoryRepo.deleteById(subCategoryId);
	}
  }

  @Override
  public List<SubCategory> getSubCategoryByCategoryId(Long categoryId) {
	Category existingCategory = categoryRepo.findById(categoryId).orElse(null);
	if(existingCategory == null) {
	  throw new NoSuchElementException();
	}else {
	  List<SubCategory> listOfSubCategories = subCategoryRepo.getSubCategoryByCategoryId(categoryId);
	  if(listOfSubCategories.size() == 0) {
		  throw new EmptyListException();
	  }else {
	    return listOfSubCategories;
	  }
	}
  }
}
