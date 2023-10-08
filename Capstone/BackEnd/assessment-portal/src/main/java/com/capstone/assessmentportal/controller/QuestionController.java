package com.capstone.assessmentportal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.assessmentportal.dto.QuestionDto;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.QuestionService;

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
  public final CustomResponse<List<QuestionDto>>
                                      getQuestionsBySubCategoryId(
          @PathVariable final Long subCategoryId) {
    List<QuestionDto> questions = questionService
          .getQuestionsBySubCategoryId(subCategoryId);
    logger.info(ValidationMessage.QUESTIONS_RETRIEVED);
    CustomResponse<List<QuestionDto>> result = new
            CustomResponse<List<QuestionDto>>(HttpStatus.OK.value(),
                    ValidationMessage.QUESTIONS_RETRIEVED, questions);
    return result;
  }
  /**
   *add questions to questions table.
   *@return newQuestion
   *@param question question
  */
  @PostMapping("/questions")
  public final CustomResponse<QuestionDto> addQuestion(
            @RequestBody @Valid final QuestionDto question) {
    questionService.addQuestion(question);
    logger.info(ValidationMessage.QUESTIONS_ADDED);
    CustomResponse<QuestionDto> result = new
            CustomResponse<QuestionDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUESTIONS_ADDED, null);
    return result;
  }
  /**
   *update questions by question id and given details.
   *@return updatedQuestion
   *@param questionId questionId
   *@param question question
  */
  @PutMapping("/questions/{questionId}")
  public final CustomResponse<QuestionDto> updateQuestion(
          @PathVariable final Long questionId,
          @RequestBody @Valid final QuestionDto question) {
    questionService.updateQuestion(questionId,
                       question);
    logger.info(ValidationMessage.QUESTIONS_UPDATED);
    CustomResponse<QuestionDto> result = new
            CustomResponse<QuestionDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUESTIONS_UPDATED, null);
    return result;
  }
  /**
   *delete questions by question id.
   *@return deletedQuestion
   *@param questionId questionId
  */
  @DeleteMapping("/questions/{questionId}")
  public final CustomResponse<QuestionDto> deleteQuestion(
         @PathVariable final Long questionId) {
    questionService.deleteQuestion(questionId);
    logger.info(ValidationMessage.QUESTIONS_DELETED);
    CustomResponse<QuestionDto> result = new
            CustomResponse<QuestionDto>(HttpStatus.OK.value(),
                    ValidationMessage.QUESTIONS_DELETED, null);
    return result;
  }
}
