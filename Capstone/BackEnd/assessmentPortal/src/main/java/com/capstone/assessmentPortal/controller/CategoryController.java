package com.capstone.assessmentPortal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import jakarta.validation.Valid;
/**
 * Category Controller class.
 */
@CrossOrigin(origins = "*")
@RestController
public class CategoryController {
  /**
  * autowiring category service.
  */
  @Autowired
  private CategoryService categoryService;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(CategoryController.class);
  /**
   *get all categories in category table.
   *@return categories
  */
  @GetMapping("/category")
  public final ResponseEntity<Object> getCategories() {
    List<CategoryDetailsDto> categories = categoryService.getCategories();
    logger.info("Retrieved categories");
    return ResponseHandler.generateResponse("Successfully Retrieved"
                  + " Categories",
                  HttpStatus.OK, "listOfCategories", categories);
  }
  /**
   * get category by id.
   * @return category
   * @param categoryId categoryId
   */
  @GetMapping("/category/{categoryId}")
  public final ResponseEntity<Object> getCategoryById(@PathVariable
                   final Long categoryId) {
    CategoryDetailsDto categoryDto = categoryService.getCategoryById(categoryId);
    logger.info("Retrieved category by id");
    return ResponseHandler.generateResponse("Successfully "
             + "Retrieved Category By Id",
                HttpStatus.OK, "CategoryDetails", categoryDto);
  }
  /**
   *add category to category table.
   *@return newCategory
   *@param category category
  */
  @PostMapping("/category")
  public final ResponseEntity<Object> addCategory(
            @RequestBody @Valid final CategoryDetailsDto category) {
    CategoryDetailsDto categoryDto = categoryService.addCategory(category);
    logger.info("Category added");
    return ResponseHandler.generateResponse("Successfully Added",
          HttpStatus.OK, "CategoryDetails", categoryDto);
  }
  /**
   * update category details by id.
   * @return updatedCategory
   * @param categoryId categoryId
   * @param category category
   */
  @PutMapping("/category/{categoryId}")
  public final ResponseEntity<Object> updateCategory(@PathVariable
           final Long categoryId,
           @RequestBody @Valid final CategoryDetailsDto category) {
    CategoryDetailsDto categoryDto = categoryService.updateCategory(
                   categoryId, category);
    logger.info("Category updated");
    return ResponseHandler.generateResponse("Successfully Updated",
          HttpStatus.OK, "CategoryDetails", categoryDto);
  }
  /**
   * delete category by id.
   * @return deletedCategory
   * @param categoryId categoryId
  */
  @DeleteMapping("/category/{categoryId}")
  public final ResponseEntity<Object> deleteCategory(
           @PathVariable final Long categoryId) {
    categoryService.deleteCategory(categoryId);
    logger.info("Category deleted");
    return ResponseHandler.generateResponse("Successfully Deleted",
    HttpStatus.OK, "CategoryDetails", null);
  }
}
