package com.capstone.assessmentportal.dto;

import com.capstone.assessmentportal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *subcategory dto class.
*/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDetailsDto {
    /**
     * subcategory id autogenerated attribute.
    */
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long subCategoryId;
  /**
   *subcategory name attribute.
  */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessage.QUIZNAME_NOTBLANK)
  private String subCategoryName;
  /**
   *subcategory description attribute.
  */
  private String subCategoryDescription;
  /**
   *subcategory time limit in minutes for quiz attribute.
  */
  @Column(nullable = false)
  @NotEmpty(message = ValidationMessage.TIMELIMIT_NOTBLANK)
  private String timeLimitInMinutes;
  /**
   *category Id attribute.
  */
  @Column(nullable = false)
  @NotNull(message = ValidationMessage.CATEGORY_NOTNULL)
  private Long categoryId;
}