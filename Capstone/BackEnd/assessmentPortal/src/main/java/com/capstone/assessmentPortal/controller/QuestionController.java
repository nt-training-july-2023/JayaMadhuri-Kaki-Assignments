package com.capstone.assessmentPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentPortal.dto.QuestionDto;
import com.capstone.assessmentPortal.response.ResponseHandler;
import com.capstone.assessmentPortal.service.QuestionService;

/**
 *Question controller class.
*/
@CrossOrigin(origins = "*")
@RestController
public class QuestionController {
  /**
   *autowiring question service.
  */
  @Autowired
  private QuestionService questionService;
  /**
   * get all questions by sub category id.
   * @return question
   * @param subCategoryId subCategoryId
  */
  @GetMapping("/getAllQuestions/{subCategoryId}")
  public final ResponseEntity<Object> getAllQuestionsBySubCategoryId(
          @PathVariable final Long subCategoryId) {
    List<QuestionDto> question = questionService
          .getQuestionsBySubCategoryId(subCategoryId);
    return ResponseHandler.generateResponse(
           "Successfully Retrieved Questions By SubCategory Id",
           HttpStatus.OK, "QuestionBySubCategoryId", question);
  }
  /**
   *add questions to questions table.
   *@return newQuestion
   *@param question question
  */
  @PostMapping("/addQuestion")
  public final ResponseEntity<Object> addQuestion(
            @RequestBody final QuestionDto question) {
    QuestionDto newQuestion = questionService.addQuestion(question);
    return ResponseHandler.generateResponse("Successfully Added",
            HttpStatus.OK, "Question", newQuestion);
  }
  /**
   *update questions by question id and given details.
   *@return updatedQuestion
   *@param questionId questionId
   *@param question question
  */
  @PutMapping("/updateQuestion/{questionId}")
  public final ResponseEntity<Object> updateQuestion(
          @PathVariable final Long questionId,
          @RequestBody final QuestionDto question) {
    QuestionDto updatedQuestion = questionService.updateQuestion(questionId,
                       question);
    return ResponseHandler.generateResponse("Successfully Updated",
          HttpStatus.OK, "Question", updatedQuestion);
  }
  /**
   *delete questions by question id.
   *@return deletedQuestion
   *@param questionId questionId
  */
  @DeleteMapping("/deleteQuestion/{questionId}")
  public final ResponseEntity<Object> deleteQuestion(
         @PathVariable final Long questionId) {
    questionService.deleteQuestion(questionId);
    return ResponseHandler.generateResponse("Successfully Deleted",
         HttpStatus.OK, "Question", null);
  }
}
