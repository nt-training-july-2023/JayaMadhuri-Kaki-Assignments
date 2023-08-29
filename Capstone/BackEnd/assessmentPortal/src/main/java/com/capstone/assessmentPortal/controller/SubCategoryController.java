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

import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.SubCategoryService;

/**
 *SubCategory controller class.
*/

@RestController
public class SubCategoryController {
  /**
   *autowiring subcategory service.
  */
  @Autowired
  private SubCategoryService subCategoryService;
  /**
   *get all subcategories.
   *@return subCategories
  */
  @GetMapping("/allSubCategories")
  public final ResponseEntity<Object> getAllSubCategories() {
    List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
    return ResponseHandler.generateResponse(
          "Successfully Retrieved All SubCategories",
          HttpStatus.OK, "List of SubCategories", subCategories);
  }
  /**
   *get subcategory by subcategory id.
   *@return subCategory
   *@param subCategoryId subCategoryId
  */
  @GetMapping("/subCategory/{subCategoryId}")
  public final ResponseEntity<Object> getSubCategoryById(@PathVariable final
                   Long subCategoryId) {
    SubCategory subCategory = subCategoryService
                   .getSubCategoryById(subCategoryId);
    return ResponseHandler.generateResponse(
         "Successfully Retrieved SubCategory By Id",
         HttpStatus.OK, "SubCategory", subCategory);
  }
  /**
   *get subcategory by category id.
   *@return subCategory
   *@param categoryId categoryId
  */
  @GetMapping("/subCategoryByCategory/{categoryId}")
  public final ResponseEntity<Object> getSubCategoryByCategoryId(
         @PathVariable final Long categoryId) {
    List<SubCategory> subCategory = subCategoryService
         .getSubCategoryByCategoryId(categoryId);
    return ResponseHandler.generateResponse(
         "Successfully Retrieved SubCategory By Category Id",
          HttpStatus.OK, "SubCategory by category id", subCategory);
  }
  /**
   *add subcategory to subcategory table.
   *@return newSubCategoy
   *@param subCategory subCategory
  */
  @PostMapping("/addSubCategory")
  public final ResponseEntity<Object> addSubCategory(
            @RequestBody final SubCategory subCategory) {
    SubCategory newSubCategory = subCategoryService
            .addSubCategory(subCategory);
    return ResponseHandler.generateResponse("Successfully Added",
            HttpStatus.OK, "SubCategory", newSubCategory);
  }
  /**
   *update subcategory by id and given new details.
   *@return updatedSubCategory
   *@param subCategoryId subCategoryId
   *@param subCategory subCategory
  */
  @PutMapping("/updateSubCategory/{subCategoryId}")
  public final ResponseEntity<Object> updateSubCategory(
          @PathVariable final Long subCategoryId,
          @RequestBody final SubCategory subCategory) {
    SubCategory updatedSubCategory = subCategoryService
          .updateSubCategory(subCategory, subCategoryId);
    return ResponseHandler.generateResponse("Successfully Updated",
          HttpStatus.OK, "SubCategory", updatedSubCategory);
  }
  /**
   *delete subcategory by id.
   *@return deletedSubCategory
   *@param subCategoryId subCategoryId
  */
  @DeleteMapping("/deleteSubCategory/{subCategoryId}")
  public final ResponseEntity<Object> deleteSubCategory(
          @PathVariable final Long subCategoryId) {
    subCategoryService.deleteSubCategory(subCategoryId);
    return ResponseHandler.generateResponse("Successfully Deleted",
          HttpStatus.OK, "SubCategory", null);
  }
}
