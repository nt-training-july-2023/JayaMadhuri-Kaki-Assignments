package com.capstone.assessmentPortal.controller;

import java.util.List;

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

import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.CategoryService;

@RestController
public class CategoryController {
  @Autowired
  CategoryService categoryService;
  
  @GetMapping("/allCategories")
  public ResponseEntity<Object> getAllCategories() {
    List<Category> categories = categoryService.getAllCategories();
    return ResponseHandler.generateResponse("Successfully Retrieved All Categories",
		  HttpStatus.OK, "List of Categories", categories);
  }
  
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<Object> getCategoryById(@PathVariable Long categoryId) {
    Category category = categoryService.getCategoryById(categoryId);
    return ResponseHandler.generateResponse("Successfully Retrieved Category By Id",
		HttpStatus.OK, "Category Details", category);
  }
  
  @PostMapping("/addCategory")
  public ResponseEntity<Object> addCategory(@RequestBody Category category) {
    Category newCategory = categoryService.addCategory(category);
    return ResponseHandler.generateResponse("Successfully Added",
		HttpStatus.OK, "Category Details", newCategory);
  }
  
  @PutMapping("/updateCategory/{categoryId}")
  public ResponseEntity<Object> updateCategory(@PathVariable Long categoryId,
		  @RequestBody Category category) {
    Category updatedCategory = categoryService.updateCategory(categoryId, category);
    return ResponseHandler.generateResponse("Successfully Updated",
		HttpStatus.OK, "Category Details", updatedCategory);
  }
  
  @DeleteMapping("/deleteCategory/{categoryId}")
  public ResponseEntity<Object> deleteCategory(@PathVariable Long categoryId) {
	  categoryService.deleteCategory(categoryId);
    return ResponseHandler.generateResponse("Successfully Deleted",
		HttpStatus.OK, "Category Details", null);
  }
}