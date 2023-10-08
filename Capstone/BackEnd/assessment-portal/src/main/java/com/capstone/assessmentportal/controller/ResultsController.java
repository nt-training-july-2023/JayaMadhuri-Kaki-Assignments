package com.capstone.assessmentportal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.response.CustomResponse;
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
    resultService.addResult(results);
    logger.info(ValidationMessage.RESULTS_ADDED);
    CustomResponse<ResultsDto> result = new
            CustomResponse<ResultsDto>(HttpStatus.OK.value(),
                    ValidationMessage.RESULTS_ADDED, null);
    return result;
  }
  /**
   * get all results.
   * @return Result
  */
  @GetMapping("/results")
  public final CustomResponse<List<ResultsDto>> getResults() {
    List<ResultsDto> resultDto = resultService
                     .getResults();
    logger.info(ValidationMessage.RESULTS_RETRIEVED);
    CustomResponse<List<ResultsDto>> result = new
            CustomResponse<List<ResultsDto>>(HttpStatus.OK.value(),
                    ValidationMessage.RESULTS_RETRIEVED, resultDto);
    return result;
  }
  /**
   * get all final results by student id.
   * @return Result
   * @param emailId emailId
  */
  @GetMapping("/results/{emailId}")
  public final CustomResponse<List<ResultsDto>>
          getResultByStudentEmail(
          @PathVariable final String emailId) {
    List<ResultsDto> results = resultService
                .getResultByStudentEmail(emailId);
    logger.info(ValidationMessage.RESULTS_RETRIEVED);
    CustomResponse<List<ResultsDto>> result = new
            CustomResponse<List<ResultsDto>>(HttpStatus.OK.value(),
                    ValidationMessage.RESULTS_RETRIEVED, results);
    return result;
  }
}
