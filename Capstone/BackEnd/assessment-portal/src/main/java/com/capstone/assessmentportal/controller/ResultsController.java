package com.capstone.assessmentportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.response.ResponseHandler;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.ResultService;

import jakarta.validation.Valid;

/**
 *Temporary Results controller class.
*/
@CrossOrigin(origins = "*")
@RestController
public class ResultsController {
  /**
   *autowiring result service.
  */
  @Autowired
  private ResultService resultService;
  /**
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(ResultsController.class);
  /**
   *add result to result table.
   *@return newResult
   *@param results results
  */
  @PostMapping("/results")
  public final CustomResponse<ResultsDto>
                              addResult(@RequestBody @Valid final
                              ResultsDto results) {
    resultService.addTemporaryResult(results);
    logger.info(ValidationMessage.RESULTS_ADDED);
    return ResponseHandler.generateResponse(ValidationMessage.RESULTS_ADDED,
               HttpStatus.OK, null);
  }
}
