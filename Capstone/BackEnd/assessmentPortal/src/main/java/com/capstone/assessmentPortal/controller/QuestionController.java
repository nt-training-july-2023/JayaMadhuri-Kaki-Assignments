package com.capstone.assessmentPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.model.Question;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.QuestionService;

@RestController
public class QuestionController {
  @Autowired
  QuestionService questionService;
  
  @GetMapping("/getAllQuestions/{subCategoryId}")
  public ResponseEntity<Object> getAllQuestionsBySubCategoryId(@PathVariable Long subCategoryId){
    try {
	  List<Question> question = questionService.getQuestionsBySubCategoryId(subCategoryId);
      return ResponseHandler.generateResponse("Successfully Retrieved Questions By SubCategory Id",
			HttpStatus.OK, "Question by SubCategory id", question);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "Question by SubCategory id", null);
    }  
  }
  
  @PostMapping("/addQuestion")
  public ResponseEntity<Object> addQuestion(@RequestBody Question question){
    try {
	  Question newQuestion = questionService.addQuestion(question);
      return ResponseHandler.generateResponse("Successfully Added",
			HttpStatus.OK, "Question", newQuestion);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "Question", null);
    }  
  }
  
  @PutMapping("/updateQuestion/{questionId}")
  public ResponseEntity<Object> updateQuestion(@PathVariable Long questionId,
		  @RequestBody Question question){
    try {
	  Question updatedQuestion = questionService.updateQuestion(questionId, question);
      return ResponseHandler.generateResponse("Successfully Updated",
			HttpStatus.OK, "Question", updatedQuestion);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "Question", null);
    }    
  }
  
  @DeleteMapping("/deleteQuestion/{questionId}")
  public ResponseEntity<Object> deleteQuestion(@PathVariable Long questionId){
    try {
	  questionService.deleteQuestion(questionId);
      return ResponseHandler.generateResponse("Successfully Deleted",
			HttpStatus.OK, "Question", null);
    }catch(Exception e) {
	  return ResponseHandler.generateResponse(e.getMessage(),
				HttpStatus.MULTI_STATUS, "Question", null);
    }     
  }
}
