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

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.CategoryService;
/**
 * Category Controller class.
 */
@RestController
public class CategoryController {
  /**
  * autowiring category service.
  */
  @Autowired
  private CategoryService categoryService;
  /**
   *get all categories in category table.
   *@return categories
  */
  @GetMapping("/allCategories")
  public final ResponseEntity<Object> getAllCategories() {
    List<CategoryDetailsDto> categories = categoryService.getAllCategories();
    return ResponseHandler.generateResponse("Successfully Retrieved"
                  + " All Categories",
                  HttpStatus.OK, "List of Categories", categories);
  }
  /**
   * get category by id.
   * @return category
   * @param categoryId categoryId
   */
  @GetMapping("/category/{categoryId}")
  public final ResponseEntity<Object> getCategoryById(@PathVariable
                   final Long categoryId) {
    CategoryDetailsDto category = categoryService.getCategoryById(categoryId);
    return ResponseHandler.generateResponse("Successfully "
             + "Retrieved Category By Id",
                HttpStatus.OK, "Category Details", category);
  }
  /**
   *add category to category table.
   *@return newCategory
   *@param category category
  */
  @PostMapping("/addCategory")
  public final ResponseEntity<Object> addCategory(
            @RequestBody final CategoryDetailsDto category) {
    CategoryDetailsDto newCategory = categoryService.addCategory(category);
    return ResponseHandler.generateResponse("Successfully Added",
          HttpStatus.OK, "Category Details", newCategory);
  }
  /**
   * update category details by id.
   * @return updatedCategory
   * @param categoryId categoryId
   * @param category category
   */
  @PutMapping("/updateCategory/{categoryId}")
  public final ResponseEntity<Object> updateCategory(@PathVariable
           final Long categoryId,
           @RequestBody final CategoryDetailsDto category) {
    CategoryDetailsDto updatedCategory = categoryService.updateCategory(
                   categoryId, category);
    return ResponseHandler.generateResponse("Successfully Updated",
          HttpStatus.OK, "Category Details", updatedCategory);
  }
  /**
   * delete category by id.
   * @return deletedCategory
   * @param categoryId categoryId
  */
  @DeleteMapping("/deleteCategory/{categoryId}")
  public final ResponseEntity<Object> deleteCategory(
           @PathVariable final Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseHandler.generateResponse("Successfully Deleted",
    HttpStatus.OK, "Category Details", null);
  }
}
