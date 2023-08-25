package com.capstone.assessmentPortal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.SubCategoryService;

@RestController
public class SubCategoryController {
  @Autowired
  SubCategoryService subCategoryService;
  
  @GetMapping("/allSubCategories")
  public ResponseEntity<Object> getAllSubCategories(){
    try {
	  List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
      return ResponseHandler.generateResponse("Successfully Retrieved All SubCategories",
			HttpStatus.OK, "List of SubCategories", subCategories);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "List of SubCategories", null);
    }
  }
  
  @GetMapping("/subCategory/{subCategoryId}")
  public ResponseEntity<Object> getSubCategoryById(@PathVariable Long subCategoryId){
    try {
	  Optional<SubCategory> subCategory = subCategoryService.getSubCategoryById(subCategoryId);
      return ResponseHandler.generateResponse("Successfully Retrieved SubCategory By Id",
			HttpStatus.OK, "SubCategory", subCategory);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "SubCategory", null);
    }  
  }
  
  @GetMapping("/subCategoryByCategory/{categoryId}")
  public ResponseEntity<Object> getSubCategoryByCategoryId(@PathVariable Long categoryId){
    try {
	  List<SubCategory> subCategory = subCategoryService.getSubCategoryByCategoryId(categoryId);
      return ResponseHandler.generateResponse("Successfully Retrieved SubCategory By Category Id",
			HttpStatus.OK, "SubCategory by category id", subCategory);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "SubCategory by category id", null);
    }  
  }
  
  @PostMapping("/addSubCategory")
  public ResponseEntity<Object> addSubCategory(@RequestBody SubCategory subCategory){
    try {
	  SubCategory newSubCategory = subCategoryService.addSubCategory(subCategory);
      return ResponseHandler.generateResponse("Successfully Added",
			HttpStatus.OK, "SubCategory", newSubCategory);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "SubCategory", null);
    }  
  }
  
  @PutMapping("/updateSubCategory/{subCategoryId}")
  public ResponseEntity<Object> updateSubCategory(@PathVariable Long subCategoryId,
		  @RequestBody SubCategory subCategory){
    try {
    	SubCategory updatedSubCategory = subCategoryService
    			.updateSubCategory(subCategory, subCategoryId);
      return ResponseHandler.generateResponse("Successfully Updated",
			HttpStatus.OK, "SubCategory", updatedSubCategory);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "SubCategory", null);
    }  
  }
  
  @DeleteMapping("/deleteSubCategory/{subCategoryId}")
  public ResponseEntity<Object> deleteSubCategory(@PathVariable Long subCategoryId){
    try {
	  subCategoryService.deleteSubCategory(subCategoryId);
      return ResponseHandler.generateResponse("Successfully Deleted",
			HttpStatus.OK, "SubCategory", null);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "SubCategory", null);
    }  
  }
}
