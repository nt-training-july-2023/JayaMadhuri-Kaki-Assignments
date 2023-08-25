package com.capstone.assessmentPortal.service;

import java.util.List;

import com.capstone.assessmentPortal.model.Question;

public interface QuestionService {
  Question addQuestion(Question question);
  List<Question> getQuestionsBySubCategoryId(Long subCategoryId);
  Question updateQuestion(Long questionId, Question question);
  void deleteQuestion(Long questionId);
}
