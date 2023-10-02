package com.capstone.assessmentPortal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.response.CustomResponse;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.response.ValidationMessage;
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
  public final CustomResponse<List<CategoryDetailsDto>>
                                  getCategories() {
    List<CategoryDetailsDto> categories = categoryService.getCategories();
    logger.info(ValidationMessage.CATEGORIES_RETRIEVED);
    return ResponseHandler.generateResponse(ValidationMessage
            .CATEGORIES_RETRIEVED, HttpStatus.OK, categories);
  }
  /**
   * get category by id.
   * @return category
   * @param categoryId categoryId
   */
  @GetMapping("/category/{categoryId}")
  public final CustomResponse<CategoryDetailsDto>
                              getCategoryById(@PathVariable
                   final Long categoryId) {
    CategoryDetailsDto categoryDto = categoryService
                                .getCategoryById(categoryId);
    logger.info(ValidationMessage.CATEGORIES_RETRIEVED_BY_ID);
    return ResponseHandler.generateResponse(ValidationMessage
            .CATEGORIES_RETRIEVED_BY_ID, HttpStatus.OK, categoryDto);
  }
  /**
   *add category to category table.
   *@return newCategory
   *@param category category
  */
  @PostMapping("/category")
  public final CustomResponse<CategoryDetailsDto> addCategory(
            @RequestBody @Valid final CategoryDetailsDto category) {
    categoryService.addCategory(category);
    logger.info(ValidationMessage.CATEGORIES_ADDED);
    return ResponseHandler.generateResponse(ValidationMessage
            .CATEGORIES_ADDED, HttpStatus.OK, null);
  }
  /**
   * update category details by id.
   * @return updatedCategory
   * @param categoryId categoryId
   * @param category category
   */
  @PutMapping("/category/{categoryId}")
  public final CustomResponse<CategoryDetailsDto>
           updateCategory(@PathVariable
           final Long categoryId,
           @RequestBody @Valid final CategoryDetailsDto category) {
    categoryService.updateCategory(
                   categoryId, category);
    logger.info(ValidationMessage.CATEGORIES_UPDATED);
    return ResponseHandler.generateResponse(ValidationMessage
            .CATEGORIES_UPDATED, HttpStatus.OK, null);
  }
  /**
   * delete category by id.
   * @return deletedCategory
   * @param categoryId categoryId
  */
  @DeleteMapping("/category/{categoryId}")
  public final CustomResponse<CategoryDetailsDto> deleteCategory(
           @PathVariable final Long categoryId) {
    categoryService.deleteCategory(categoryId);
    logger.info(ValidationMessage.CATEGORIES_DELETED);
    return ResponseHandler.generateResponse(ValidationMessage
            .CATEGORIES_DELETED, HttpStatus.OK, null);
  }
}
