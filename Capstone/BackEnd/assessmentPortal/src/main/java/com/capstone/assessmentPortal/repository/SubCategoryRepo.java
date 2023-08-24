package com.capstone.assessmentPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.SubCategory;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
  @Query("Select s from SubCategory as s where s.category.categoryId = :categoryId")
  List<SubCategory> getSubCategoryByCategoryId(Long categoryId);
}
