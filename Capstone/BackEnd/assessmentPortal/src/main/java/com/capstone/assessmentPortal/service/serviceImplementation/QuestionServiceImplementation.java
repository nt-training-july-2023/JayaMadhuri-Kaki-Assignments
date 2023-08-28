package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.assessmentPortal.exception.EmptyListException;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.exception.NotFoundException;
import com.capstone.assessmentPortal.model.Question;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.QuestionRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.service.QuestionService;

@Service
public class QuestionServiceImplementation implements QuestionService{
  @Autowired
  QuestionRepo questionRepo;
  @Autowired
  SubCategoryRepo subCategoryRepo;

  @Override
  public Question addQuestion(Question question) {
    SubCategory existingSubCategory = subCategoryRepo.findById(question.getSubCategory().getSubCategoryId()).orElse(null);
    if(question.getQuestionContent() == "" ||question.getOptionA() == "" ||question.getOptionA() == ""
    		||question.getOptionB() == "" ||question.getOptionC() == "" ||question.getOptionD() == ""
    		||question.getCorrectAnswer() == "") {
      throw new InputEmptyException();
    }else {
      if(existingSubCategory!=null) {
        return questionRepo.save(question);
      }else {
    	throw new NotFoundException("SubCategory Not Found");
  	  }
    }
  }

  @Override
  public List<Question> getQuestionsBySubCategoryId(Long subCategoryId) {
    SubCategory existingSubCategory = subCategoryRepo.findById(subCategoryId).orElse(null);
	if(existingSubCategory == null) {
	  throw new NoSuchElementException();
	}else {
	  List<Question> listOfQuestions = questionRepo.getQuestionBySubCategoryId(subCategoryId);
	  if(listOfQuestions.size() == 0) {
	    throw new EmptyListException();
	  }else {
	    return listOfQuestions;
	  }
	}
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
	  if(existingQuestion.getQuestionContent() == "" ||existingQuestion.getOptionA() == ""
			    ||existingQuestion.getOptionA() == ""
	    		||existingQuestion.getOptionB() == ""
	    		||existingQuestion.getOptionC() == ""
	    		||existingQuestion.getOptionD() == ""
	    		||existingQuestion.getCorrectAnswer() == "") {
	    throw new InputEmptyException();
	  }else {
		questionRepo.save(existingQuestion);
		return existingQuestion;
	  }
	}else {
	  throw new NoSuchElementException();
	}
  }

  @Override
  public void deleteQuestion(Long questionId) {
    Question existingQuestion = questionRepo.findById(questionId).orElse(null);
    if(existingQuestion == null) {
	  throw new NoSuchElementException();
	}else {
      questionRepo.deleteById(questionId);
	}
  }
  
}
