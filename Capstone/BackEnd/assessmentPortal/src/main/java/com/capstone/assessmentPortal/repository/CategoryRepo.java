package com.capstone.assessmentPortal.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
  @Query("select c from Category as c where c.categoryName = :categoryName")
  Optional<Category> getCategoryByName(String categoryName);
}
