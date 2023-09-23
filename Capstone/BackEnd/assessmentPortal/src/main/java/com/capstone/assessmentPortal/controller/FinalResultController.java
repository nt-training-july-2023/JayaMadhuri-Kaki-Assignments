package com.capstone.assessmentPortal.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(FinalResultController.class);
  /**
   * get all final results.
   * @return finalResult
  */
  @GetMapping("/finalResults")
  public final ResponseEntity<Object> getAllFinalResults() {
    List<FinalResultsDto> finalResultDto = finalResultService
                     .getAllFinalResults();
    logger.info("Retrieved all final results");
    return ResponseHandler.generateResponse("Successfully Retrieved",
        HttpStatus.OK, "FinalResults", finalResultDto);
  }
  /**
   * get all final results by student id.
   * @return finalResult
   * @param emailId emailId
  */
  @GetMapping("/finalResults/{emailId}")
  public final ResponseEntity<Object> getAllFinalResultByStudentEmail(
          @PathVariable final String emailId) {
    List<FinalResultsDto> finalResults = finalResultService
                .getFinalResultByStudentEmail(emailId);
    logger.info("Retrieved all final results by student EmailId");
    return ResponseHandler.generateResponse("Successfully Retrieved",
          HttpStatus.OK, "FinalResults", finalResults);
  }
}
