package com.capstone.assessmentPortal.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *subcategory dto class.
*/

@Setter
@Getter
public class SubCategoryDetailsDto {
    /**
     * subcategory id autogenerated attribute.
    */
   @Id
   private Long subCategoryId;
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
