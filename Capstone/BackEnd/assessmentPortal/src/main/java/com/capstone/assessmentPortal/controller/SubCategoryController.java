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

import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.SubCategoryService;

import jakarta.validation.Valid;

/**
 *SubCategory controller class.
*/
@CrossOrigin(origins = "*")
@RestController
public class SubCategoryController {
  /**
   *autowiring subcategory service.
  */
  @Autowired
  private SubCategoryService subCategoryService;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
  /**
   *get all subcategories.
   *@return subCategories
  */
  @GetMapping("/subCategory/all")
  public final ResponseEntity<Object> getAllSubCategories() {
    List<SubCategoryDetailsDto> subCategories = subCategoryService
              .getAllSubCategories();
    logger.info("Retrieved all quizes");
    return ResponseHandler.generateResponse(
          "Successfully Retrieved All SubCategories",
          HttpStatus.OK, "ListOfSubCategories", subCategories);
  }
  /**
   *get subcategory by subcategory id.
   *@return subCategory
   *@param subCategoryId subCategoryId
  */
  @GetMapping("/subCategory/{subCategoryId}")
  public final ResponseEntity<Object> getSubCategoryById(@PathVariable final
                   Long subCategoryId) {
    SubCategoryDetailsDto subCategoryDto = subCategoryService
                   .getSubCategoryById(subCategoryId);
    logger.info("Retrieved quiz by id");
    return ResponseHandler.generateResponse(
         "Successfully Retrieved SubCategory By Id",
         HttpStatus.OK, "SubCategory", subCategoryDto);
  }
  /**
   *get subcategory by category id.
   *@return subCategory
   *@param categoryId categoryId
  */
  @GetMapping("/subCategory/subCategoryByCategory/{categoryId}")
  public final ResponseEntity<Object> getSubCategoryByCategoryId(
         @PathVariable final Long categoryId) {
    List<SubCategoryDetailsDto> subCategories = subCategoryService
         .getSubCategoryByCategoryId(categoryId);
    logger.info("Retrieved quizes by category id");
    return ResponseHandler.generateResponse(
         "Successfully Retrieved SubCategory By Category Id",
          HttpStatus.OK, "SubCategoryByCategoryId", subCategories);
  }
  /**
   *add subcategory to subcategory table.
   *@return newSubCategoy
   *@param subCategory subCategory
  */
  @PostMapping("/subCategory/add")
  public final ResponseEntity<Object> addSubCategory(
            @RequestBody @Valid final SubCategoryDetailsDto subCategory) {
    SubCategoryDetailsDto subCategoryDto = subCategoryService
            .addSubCategory(subCategory);
    logger.info("Quiz Added");
    return ResponseHandler.generateResponse("Successfully Added",
            HttpStatus.OK, "SubCategory", subCategoryDto);
  }
  /**
   *update subcategory by id and given new details.
   *@return updatedSubCategory
   *@param subCategoryId subCategoryId
   *@param subCategory subCategory
  */
  @PutMapping("/subCategory/update/{subCategoryId}")
  public final ResponseEntity<Object> updateSubCategory(
          @PathVariable final Long subCategoryId,
          @RequestBody @Valid final SubCategoryDetailsDto subCategory) {
    SubCategoryDetailsDto subCategoryDto = subCategoryService
          .updateSubCategory(subCategory, subCategoryId);
    logger.info("Quiz Updated");
    return ResponseHandler.generateResponse("Successfully Updated",
          HttpStatus.OK, "SubCategory", subCategoryDto);
  }
  /**
   *delete subcategory by id.
   *@return deletedSubCategory
   *@param subCategoryId subCategoryId
  */
  @DeleteMapping("/subCategory/delete/{subCategoryId}")
  public final ResponseEntity<Object> deleteSubCategory(
          @PathVariable final Long subCategoryId) {
    subCategoryService.deleteSubCategory(subCategoryId);
    logger.info("Quiz Deleted");
    return ResponseHandler.generateResponse("Successfully Deleted",
          HttpStatus.OK, "SubCategory", null);
  }
}
