package com.capstone.assessmentportal.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentportal.model.Category;

/**
 *Category entity repository.
*/

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
  /**
    *query to get records of category having given name.
    *@return categoryDetails
    *@param categoryName categoryName
  */
  @Query("select c from Category as c where c.categoryName = :categoryName")
  Optional<Category> getCategoryByName(String categoryName);
}
