package com.capstone.assessmentPortal.repository;

import java.util.List;

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
   *query to get records in final results table when given student email exists.
   *@return finalResults
   *@param studentEmailId studentEmailId
  */
  @Query("select f from FinalResultsOfQuiz as f where f.studentEmailId ="
          + ":studentEmailId")
  List<FinalResultsOfQuiz> getFinalResultsByStudentEmail(String studentEmailId);
}
