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

@RestController
public class FinalResultController {
  @Autowired
  FinalResultService finalResultService;
  
  @GetMapping("/getAllFinalResults")
  public ResponseEntity<Object> getAllFinalResults(){
    try {
	  List<FinalResultsOfQuiz> finalResult = finalResultService.getAllFinalResults();
      return ResponseHandler.generateResponse("Successfully Retrieved",
			HttpStatus.OK, "FinalResults", finalResult);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "FinalResults", null);
    }  
  }
  
  @GetMapping("/getFinalResult/{studentId}/{quizName}")
  public ResponseEntity<Object> getAllFinalResultByStudentIdAndQuiz(@PathVariable String quizName,
		  @PathVariable Long studentId){
    try {
	  Optional<FinalResultsOfQuiz> finalResult = finalResultService
			  .getFinalResultsByStudentIdQuizName(studentId, quizName);
      return ResponseHandler.generateResponse("Successfully Retrieved",
			HttpStatus.OK, "FinalResults", finalResult);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "FinalResults", null);
    }  
  }
  
  @GetMapping("/getAllFinalResults/{studentId}")
  public ResponseEntity<Object> getAllFinalResultByStudentId(@PathVariable Long studentId){
    try {
	  List<FinalResultsOfQuiz> finalResult = finalResultService.getFinalResultByStudentId(studentId);
      return ResponseHandler.generateResponse("Successfully Retrieved",
			HttpStatus.OK, "FinalResults", finalResult);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "FinalResults", null);
    }  
  }
}
