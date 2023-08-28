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

@RestController
public class ResultsController {
  @Autowired
  ResultService resultService;
  
  @PostMapping("/addResults")
  public ResponseEntity<Object> addResult(@RequestBody Results Results) {
    Results newResult = resultService.addTemporaryResult(Results);
    return ResponseHandler.generateResponse("Successfully Added",
		HttpStatus.OK, "Result", newResult);
  }
}
