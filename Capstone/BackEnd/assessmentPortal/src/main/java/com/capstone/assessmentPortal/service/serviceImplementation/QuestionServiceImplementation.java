package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.model.Question;
import com.capstone.assessmentPortal.repository.QuestionRepo;
import com.capstone.assessmentPortal.service.QuestionService;

@Service
public class QuestionServiceImplementation implements QuestionService{
  @Autowired
  QuestionRepo questionRepo;

  @Override
  public Question addQuestion(Question question) {
    return questionRepo.save(question);
  }

  @Override
  public List<Question> getQuestionsBySubCategoryId(Long subCategoryId) {
    return questionRepo.getQuestionBySubCategoryId(subCategoryId);
  }

  @Override
  public Question updateQuestion(Long questionId, Question question) {
    Question existingQuestion = questionRepo.findById(questionId).orElseThrow();
	if(existingQuestion!=null) {
	  existingQuestion.setQuestionContent(question.getQuestionContent());
	  existingQuestion.setOptionA(question.getOptionA());
	  existingQuestion.setOptionB(question.getOptionB());
	  existingQuestion.setOptionC(question.getOptionC());
	  existingQuestion.setOptionD(question.getOptionD());
	  existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
	return questionRepo.save(existingQuestion);
	}
	return existingQuestion;
  }

  @Override
  public void deleteQuestion(Long questionId) {
    questionRepo.deleteById(questionId);
  }
  
}
