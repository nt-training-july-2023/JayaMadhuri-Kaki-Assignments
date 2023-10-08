package com.capstone.assessmentportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentportal.model.Results;
/**
 *Result repository.
*/

@Repository
public interface ResultRepo extends JpaRepository<Results, Long> {
  /**
   *Result repository.
   *@return results
   *@param studentId studentId
   *@param quizName quizName
  */
  @Query("select r from Results as r where r.studentId = :studentId "
            + "and r.quizName = :quizName")
  Results findResultsByStudentsAndSubCategory(Long studentId, Long quizName);
  /**
   *query to get records in results table when given student email exists.
   *@return Results
   *@param studentEmailId studentEmailId
  */
  @Query("select f from Results as f where f.studentEmailId = "
          + ":studentEmailId")
  List<Results> getResultsByStudentEmail(String studentEmailId);
}
