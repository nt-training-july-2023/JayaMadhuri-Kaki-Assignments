package com.capstone.assessmentPortal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.FinalResultService;

/**
 * Final results controller class.
*/

@RestController
public class FinalResultController {
  /**
   * autowiring finalresult service.
  */
  @Autowired
  private FinalResultService finalResultService;
  /**
   * get all final results.
   * @return finalResult
  */
  @GetMapping("/getAllFinalResults")
  public final ResponseEntity<Object> getAllFinalResults() {
    List<FinalResultsOfQuiz> finalResult = finalResultService
                     .getAllFinalResults();
    return ResponseHandler.generateResponse("Successfully Retrieved",
        HttpStatus.OK, "FinalResults", finalResult);
  }
  /**
   * get all final results by student id and quiz name.
   * @return finalResult
   * @param studentId studentId
   * @param quizName quizName
  */
  @GetMapping("/getFinalResult/{studentId}/{quizName}")
  public final ResponseEntity<Object> getAllFinalResultByStudentIdAndQuiz(
          @PathVariable final String quizName,
          @PathVariable final Long studentId) {
    Optional<FinalResultsOfQuiz> finalResult = finalResultService
           .getFinalResultsByStudentIdQuizName(studentId, quizName);
    return ResponseHandler.generateResponse("Successfully Retrieved",
         HttpStatus.OK, "FinalResults", finalResult);
  }
  /**
   * get all final results by student id.
   * @return finalResult
   * @param studentId studentId
  */
  @GetMapping("/getAllFinalResults/{studentId}")
  public final ResponseEntity<Object> getAllFinalResultByStudentId(
          @PathVariable final Long studentId) {
    List<FinalResultsOfQuiz> finalResult = finalResultService
                .getFinalResultByStudentId(studentId);
    return ResponseHandler.generateResponse("Successfully Retrieved",
          HttpStatus.OK, "FinalResults", finalResult);
  }
}
