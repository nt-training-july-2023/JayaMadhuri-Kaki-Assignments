package com.capstone.assessmentPortal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *category dto class.
*/

@Data
public class CategoryDetailsDto {
  /**
   *category name attribute.
  */
  @Column(unique = true)
  @NotEmpty(message = "Category Name is required")
  private String categoryName;
  /**
   *category description attribute.
  */
  @Column(nullable = true)
  private String categoryDescription;
}
