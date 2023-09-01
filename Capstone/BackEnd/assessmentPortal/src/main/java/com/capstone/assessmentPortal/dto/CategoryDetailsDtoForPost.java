package com.capstone.assessmentPortal.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *category dto class.
*/

@Setter
@Getter
@NoArgsConstructor
public class CategoryDetailsDtoForPost {
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

