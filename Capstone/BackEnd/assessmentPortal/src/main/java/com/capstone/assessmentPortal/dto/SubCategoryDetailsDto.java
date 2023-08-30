package com.capstone.assessmentPortal.dto;

import lombok.Data;

/**
 *subcategory dto class.
*/

@Data
public class SubCategoryDetailsDto {
  /**
   *subcategory name attribute.
  */
  private String subCategoryName;
  /**
   *subcategory description attribute.
  */
  private String subCategoryDescription;
  /**
   *subcategory time limit in minutes for quiz attribute.
  */
  private String timeLimitInMinutes;
  /**
   *category Id attribute.
  */
  private Long categoryId;
}
