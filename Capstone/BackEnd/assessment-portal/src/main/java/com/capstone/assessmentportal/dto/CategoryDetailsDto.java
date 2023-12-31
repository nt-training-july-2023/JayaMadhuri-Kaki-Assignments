package com.capstone.assessmentportal.dto;

import java.util.Objects;

import com.capstone.assessmentportal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *category dto class.
*/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetailsDto {
  /**
   *categoryId attribute autogenerated.
  */
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long categoryId;
  /**
   *category name attribute.
  */
  @Column(unique = true, nullable = false)
  @NotBlank(message = ValidationMessage.CATEGORYNAME_NOTBLANK)
  private String categoryName;
  /**
   *category description attribute.
  */
  private String categoryDescription;
  /**
   *hashcode method.
  */
  @Override
  public final int hashCode() {
    return Objects.hash(categoryDescription, categoryId, categoryName);
  }
  /**
   *equals method.
  */
  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    CategoryDetailsDto other = (CategoryDetailsDto) obj;
    return Objects.equals(categoryDescription, other.categoryDescription)
            && Objects.equals(categoryId, other.categoryId)
            && Objects.equals(categoryName, other.categoryName);
  }
}
