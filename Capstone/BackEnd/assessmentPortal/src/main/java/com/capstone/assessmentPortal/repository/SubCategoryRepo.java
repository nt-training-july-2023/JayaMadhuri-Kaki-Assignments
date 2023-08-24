package com.capstone.assessmentPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.SubCategory;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

}
