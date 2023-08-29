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


/**
 * Question service implementation class.
*/

@Service
public class QuestionServiceImplementation implements QuestionService {

  /**
   * autowiring question repository.
  */
  @Autowired
  private QuestionRepo questionRepo;
  /**
   * autowiring subcategory repository.
  */
  @Autowired
  private SubCategoryRepo subCategoryRepo;
  @Override
  public final Question addQuestion(final Question question) {
    SubCategory existingSubCategory = subCategoryRepo.findById(
           question.getSubCategory().getSubCategoryId()).orElse(null);
    if (question.getQuestionContent().isEmpty()
            || question.getOptionA().isEmpty()
            || question.getOptionB().isEmpty()
            || question.getOptionC().isEmpty()
            || question.getOptionD().isEmpty()
            || question.getCorrectAnswer().isEmpty()) {
      throw new InputEmptyException();
    } else {
      if (existingSubCategory != null) {
        return questionRepo.save(question);
      } else {
          throw new NotFoundException("SubCategory Not Found");
      }
    }
  }
  @Override
  public final List<Question> getQuestionsBySubCategoryId(final
                   Long subCategoryId) {
    SubCategory existingSubCategory = subCategoryRepo
              .findById(subCategoryId).orElse(null);
    if (existingSubCategory == null) {
      throw new NoSuchElementException();
    } else {
      List<Question> listOfQuestions =
              questionRepo.getQuestionBySubCategoryId(subCategoryId);
      if (listOfQuestions.size() == 0) {
        throw new EmptyListException();
      } else {
        return listOfQuestions;
      }
    }
  }
  @Override
  public final Question updateQuestion(final Long questionId, final
                  Question question) {
    Question existingQuestion = questionRepo
                .findById(questionId).orElseThrow();
    if (existingQuestion != null) {
      existingQuestion.setQuestionContent(question.getQuestionContent());
      existingQuestion.setOptionA(question.getOptionA());
      existingQuestion.setOptionB(question.getOptionB());
      existingQuestion.setOptionC(question.getOptionC());
      existingQuestion.setOptionD(question.getOptionD());
      existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
      if (existingQuestion.getQuestionContent().isEmpty()
          || existingQuestion.getOptionA().isEmpty()
          || existingQuestion.getOptionB().isEmpty()
          || existingQuestion.getOptionC().isEmpty()
          || existingQuestion.getOptionD().isEmpty()
          || existingQuestion.getCorrectAnswer().isEmpty()) {
        throw new InputEmptyException();
      } else {
        questionRepo.save(existingQuestion);
        return existingQuestion;
  }
} else {
  throw new NoSuchElementException();
}
  }
  @Override
  public final void deleteQuestion(final Long questionId) {
    Question existingQuestion = questionRepo.findById(questionId).orElse(null);
    if (existingQuestion == null) {
      throw new NoSuchElementException();
    } else {
      questionRepo.deleteById(questionId);
    }
  }
}
