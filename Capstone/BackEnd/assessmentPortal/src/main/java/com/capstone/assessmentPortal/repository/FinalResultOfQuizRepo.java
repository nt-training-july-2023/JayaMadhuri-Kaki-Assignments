package com.capstone.assessmentPortal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;

/**
 *Final result of quiz entity repository.
*/

@Repository
public interface FinalResultOfQuizRepo extends JpaRepository
                                               <FinalResultsOfQuiz, Long> {
  /**
   *query to get records in final result of quiz table
   *when given category name and
   *student id is equal.
   *@return finalResults
   *@param studentId studentId
   *@param subCategoryName subCategoryName
  */
  @Query("select f from FinalResultsOfQuiz as f where"
           + " f.studentId = :studentId and f.quizName = :subCategoryName")
  Optional<FinalResultsOfQuiz> getFinalResultsByStudentIdQuizName(Long
           studentId, String subCategoryName);
  /**
   *query to get records in final results table when given student id exists.
   *@return finalResults
   *@param studentId studentId
  */
  @Query("select f from FinalResultsOfQuiz as f where f.studentId = :studentId")
  List<FinalResultsOfQuiz> getFinalResultsByStudentId(Long studentId);
}
