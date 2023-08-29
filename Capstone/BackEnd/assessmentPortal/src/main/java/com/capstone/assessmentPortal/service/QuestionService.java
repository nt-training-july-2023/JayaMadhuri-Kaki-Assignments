package com.capstone.assessmentPortal.service;

import java.util.List;

import com.capstone.assessmentPortal.model.Question;

/**
 * question service interface.
*/

public interface QuestionService {
  /**
   * add question to a quiz.
   * @return question
   * @param question question
  */
  Question addQuestion(Question question);
  /**
   * get list of question by subcategory id.
   * @return questions
   * @param subCategoryId subCategoryId
  */
  List<Question> getQuestionsBySubCategoryId(Long subCategoryId);
  /**
   * update questions by question id and new details.
   * @return question
   * @param questionId questionId
   * @param question question
  */
  Question updateQuestion(Long questionId, Question question);
  /**
   * delete question by question id.
   * @param questionId questionId
  */
  void deleteQuestion(Long questionId);
}
