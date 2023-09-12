package com.capstone.assessmentPortal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.dto.FinalResultsDto;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.FinalResultService;

/**
 * Final results controller class.
*/
@CrossOrigin(origins = "*")
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
    List<FinalResultsDto> finalResult = finalResultService
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
    Optional<FinalResultsDto> finalResult = finalResultService
           .getFinalResultsByStudentIdQuizName(studentId, quizName);
    return ResponseHandler.generateResponse("Successfully Retrieved",
         HttpStatus.OK, "FinalResults", finalResult);
  }
  /**
   * get all final results by student id.
   * @return finalResult
   * @param emailId emailId
  */
  @GetMapping("/getAllFinalResults/{emailId}")
  public final ResponseEntity<Object> getAllFinalResultByStudentEmail(
          @PathVariable final String emailId) {
    List<FinalResultsDto> finalResult = finalResultService
                .getFinalResultByStudentEmail(emailId);
    return ResponseHandler.generateResponse("Successfully Retrieved",
          HttpStatus.OK, "FinalResults", finalResult);
  }
}
