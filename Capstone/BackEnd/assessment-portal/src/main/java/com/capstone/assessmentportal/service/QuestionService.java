package com.capstone.assessmentportal.service;

import java.util.List;

import com.capstone.assessmentportal.dto.QuestionDto;

/**
 * question service interface.
*/

public interface QuestionService {
  /**
   * add question to a quiz.
   * @return question
   * @param question question
  */
  QuestionDto addQuestion(QuestionDto question);
  /**
   * get list of question by subcategory id.
   * @return questions
   * @param subCategoryId subCategoryId
  */
  List<QuestionDto> getQuestionsBySubCategoryId(Long subCategoryId);
  /**
   * update questions by question id and new details.
   * @return question
   * @param questionId questionId
   * @param question question
  */
  QuestionDto updateQuestion(Long questionId, QuestionDto question);
  /**
   * delete question by question id.
   * @param questionId questionId
  */
  void deleteQuestion(Long questionId);
}
