package com.capstone.assessmentPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
  @Query("Select q from Question as q where q.subCategory.subCategoryId = :subCategoryId")
  List<Question> getQuestionBySubCategoryId(Long subCategoryId);
}
