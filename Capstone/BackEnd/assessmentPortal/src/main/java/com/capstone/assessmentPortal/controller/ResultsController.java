package com.capstone.assessmentPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.ResultService;
import com.capstone.assessmentPortal.dto.ResultsDto;

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
   *add result to result table.
   *@return newResult
   *@param results results
  */
  @PostMapping("/addResults")
  public final ResponseEntity<Object> addResult(@RequestBody final
                ResultsDto results) {
    ResultsDto newResult = resultService.addTemporaryResult(results);
    return ResponseHandler.generateResponse("Successfully Added",
               HttpStatus.OK, "Result", newResult);
  }
  /**
   *check status of user whether he attempted quiz or not.
   *@return true or false
   *@param userId userId
   *@param subCategoryId subCategoryId
  */
  @GetMapping("/status/{userId}/{subCategoryId}")
  public final ResponseEntity<Object> checkAttemptOrNot(@PathVariable final
                          Long userId, @PathVariable final Long subCategoryId) {
    boolean result = resultService
         .findResultsByUserAndSubCategory(userId, subCategoryId);
    return ResponseHandler.generateResponse("Retrieved Status",
            HttpStatus.OK, "status", result);
  }
}
