package com.capstone.assessmentPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.ResultService;
import com.capstone.assessmentPortal.model.Results;

/**
 *Temporary Results controller class.
*/

@RestController
public class ResultsController {
  /**
   *autowiring result service.
  */
  @Autowired
  private ResultService resultService;
  /**
   *add result to result table.
   *@return newResult
   *@param results results
  */
  @PostMapping("/addResults")
  public final ResponseEntity<Object> addResult(@RequestBody final
                Results results) {
    Results newResult = resultService.addTemporaryResult(results);
    return ResponseHandler.generateResponse("Successfully Added",
               HttpStatus.OK, "Result", newResult);
  }
}
