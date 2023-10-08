package com.capstone.assessmentportal.controller;

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

import com.capstone.assessmentportal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.SubCategoryService;

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
  @GetMapping("/subCategory")
  public final CustomResponse<List<SubCategoryDetailsDto>>
                                                  getSubCategories() {
    List<SubCategoryDetailsDto> subCategories = subCategoryService
              .getSubCategories();
    logger.info(ValidationMessage.QUIZ_RETRIEVED);
    CustomResponse<List<SubCategoryDetailsDto>> result = new
            CustomResponse<List<SubCategoryDetailsDto>>(HttpStatus.OK.value(),
                    ValidationMessage.QUIZ_RETRIEVED, subCategories);
    return result;
  }
  /**
   *get subcategory by subcategory id.
   *@return subCategory
   *@param subCategoryId subCategoryId
  */
  @GetMapping("/subCategory/{subCategoryId}")
  public final CustomResponse<SubCategoryDetailsDto>
                                  getSubCategoryById(@PathVariable final
                   Long subCategoryId) {
    SubCategoryDetailsDto subCategoryDto = subCategoryService
                   .getSubCategoryById(subCategoryId);
    logger.info(ValidationMessage.QUIZ_RETRIEVED_BY_ID);
    CustomResponse<SubCategoryDetailsDto> result = new
            CustomResponse<SubCategoryDetailsDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUIZ_RETRIEVED_BY_ID, subCategoryDto);
    return result;
  }
  /**
   *get subcategory by category id.
   *@return subCategory
   *@param categoryId categoryId
  */
  @GetMapping("/subCategory/subCategoryByCategory/{categoryId}")
  public final CustomResponse<List<SubCategoryDetailsDto>>
                                          getSubCategoryByCategoryId(
         @PathVariable final Long categoryId) {
    List<SubCategoryDetailsDto> subCategories = subCategoryService
         .getSubCategoryByCategoryId(categoryId);
    logger.info(ValidationMessage.QUIZ_RETRIEVED_BY_CATEGORY_ID);
    CustomResponse<List<SubCategoryDetailsDto>> result = new
            CustomResponse<List<SubCategoryDetailsDto>>(HttpStatus.OK.value(),
            ValidationMessage.QUIZ_RETRIEVED_BY_CATEGORY_ID, subCategories);
    return result;
  }
  /**
   *add subcategory to subcategory table.
   *@return newSubCategoy
   *@param subCategory subCategory
  */
  @PostMapping("/subCategory")
  public final CustomResponse<SubCategoryDetailsDto>
                                                  addSubCategory(
            @RequestBody @Valid final SubCategoryDetailsDto subCategory) {
    subCategoryService
            .addSubCategory(subCategory);
    logger.info(ValidationMessage.QUIZ_ADDED);
    CustomResponse<SubCategoryDetailsDto> result = new
            CustomResponse<SubCategoryDetailsDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUIZ_ADDED, null);
    return result;
  }
  /**
   *update subcategory by id and given new details.
   *@return updatedSubCategory
   *@param subCategoryId subCategoryId
   *@param subCategory subCategory
  */
  @PutMapping("/subCategory/{subCategoryId}")
  public final CustomResponse<SubCategoryDetailsDto>
                                              updateSubCategory(
          @PathVariable final Long subCategoryId,
          @RequestBody @Valid final SubCategoryDetailsDto subCategory) {
    subCategoryService
          .updateSubCategory(subCategory, subCategoryId);
    logger.info(ValidationMessage.QUIZ_UPDATED);
    CustomResponse<SubCategoryDetailsDto> result = new
            CustomResponse<SubCategoryDetailsDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUIZ_UPDATED, null);
    return result;
  }
  /**
   *delete subcategory by id.
   *@return deletedSubCategory
   *@param subCategoryId subCategoryId
  */
  @DeleteMapping("/subCategory/{subCategoryId}")
  public final CustomResponse<SubCategoryDetailsDto>
                                          deleteSubCategory(
          @PathVariable final Long subCategoryId) {
    subCategoryService.deleteSubCategory(subCategoryId);
    logger.info(ValidationMessage.QUIZ_DELETED);
    CustomResponse<SubCategoryDetailsDto> result = new
            CustomResponse<SubCategoryDetailsDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUIZ_DELETED, null);
    return result;
  }
}
