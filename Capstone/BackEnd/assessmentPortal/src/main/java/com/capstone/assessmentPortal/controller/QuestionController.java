package com.capstone.assessmentPortal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.validation.Valid;

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
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(QuestionController.class);
  /**
   * get all questions by sub category id.
   * @return question
   * @param subCategoryId subCategoryId
  */
  @GetMapping("/questions/{subCategoryId}")
  public final ResponseEntity<Object> getAllQuestionsBySubCategoryId(
          @PathVariable final Long subCategoryId) {
    List<QuestionDto> questions = questionService
          .getQuestionsBySubCategoryId(subCategoryId);
    logger.info("Retrieved all questions by quiz id");
    return ResponseHandler.generateResponse(
           "Successfully Retrieved Questions By SubCategory Id",
           HttpStatus.OK, "QuestionBySubCategoryId", questions);
  }
  /**
   *add questions to questions table.
   *@return newQuestion
   *@param question question
  */
  @PostMapping("/questions/add")
  public final ResponseEntity<Object> addQuestion(
            @RequestBody @Valid final QuestionDto question) {
    QuestionDto questionDto = questionService.addQuestion(question);
    logger.info("Question Added");
    return ResponseHandler.generateResponse("Successfully Added",
            HttpStatus.OK, "Question", questionDto);
  }
  /**
   *update questions by question id and given details.
   *@return updatedQuestion
   *@param questionId questionId
   *@param question question
  */
  @PutMapping("/questions/update/{questionId}")
  public final ResponseEntity<Object> updateQuestion(
          @PathVariable final Long questionId,
          @RequestBody @Valid final QuestionDto question) {
    QuestionDto questionDto = questionService.updateQuestion(questionId,
                       question);
    logger.info("Question Updated");
    return ResponseHandler.generateResponse("Successfully Updated",
          HttpStatus.OK, "Question", questionDto);
  }
  /**
   *delete questions by question id.
   *@return deletedQuestion
   *@param questionId questionId
  */
  @DeleteMapping("/questions/delete/{questionId}")
  public final ResponseEntity<Object> deleteQuestion(
         @PathVariable final Long questionId) {
    questionService.deleteQuestion(questionId);
    logger.info("Question Deleted");
    return ResponseHandler.generateResponse("Successfully Deleted",
         HttpStatus.OK, "Question", null);
  }
}
