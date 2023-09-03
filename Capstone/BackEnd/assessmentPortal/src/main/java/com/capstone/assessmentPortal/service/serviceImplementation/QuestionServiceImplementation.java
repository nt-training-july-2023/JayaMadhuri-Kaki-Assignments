package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.QuestionDto;
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
  /**
   * parameter constructor.
   * @param subCategoryRepo2 subCategoryRepo2
   * @param questionRepo2 questionRepo2
  */
  public QuestionServiceImplementation(final SubCategoryRepo subCategoryRepo2,
          final QuestionRepo questionRepo2) {
    this.subCategoryRepo = subCategoryRepo2;
    this.questionRepo = questionRepo2;
}
@Override
  public final QuestionDto addQuestion(final QuestionDto question) {
    if (question.getQuestionContent().isEmpty()
            || question.getSubCategoryId() == null) {
      throw new InputEmptyException();
    } else {
      SubCategory existingSubCategory = subCategoryRepo.findById(
               question.getSubCategoryId()).orElseThrow(() ->
               new NotFoundException());
      Question que = new Question();
      que.setQuestionContent(question.getQuestionContent());
      que.setOptionA(question.getOptionA());
      que.setOptionB(question.getOptionB());
      que.setOptionC(question.getOptionC());
      que.setOptionD(question.getOptionD());
      que.setCorrectAnswer(question.getCorrectAnswer());
      que.setSubCategory(existingSubCategory);
      System.out.println(existingSubCategory.getSubCategoryName());
      questionRepo.save(que);
      return question;
    }
  }
  @Override
  public final List<QuestionDto> getQuestionsBySubCategoryId(final
                   Long subCategoryId) {
    SubCategory existingSubCategory = subCategoryRepo
              .findById(subCategoryId).orElse(null);
    if (existingSubCategory == null) {
      throw new NoSuchElementException();
    } else {
      List<Question> listOfQuestions =
              questionRepo.getQuestionBySubCategoryId(subCategoryId);
      return listOfQuestions.stream()
              .map(this::convertEntityToDto)
              .collect(Collectors.toList());
    }
  }
  /**
   * converting entity to dto for get all method.
   * @return questionDto
   * @param question question
  */
  private QuestionDto convertEntityToDto(final
          Question question) {
    QuestionDto questionDto = new QuestionDto();
    questionDto.setQuestionId(question.getQuestionId());
    questionDto.setQuestionContent(question.getQuestionContent());
    questionDto.setOptionA(question
              .getOptionA());
    questionDto.setOptionB(question
            .getOptionB());
    questionDto.setOptionC(question
            .getOptionC());
    questionDto.setOptionD(question
            .getOptionD());
    questionDto.setCorrectAnswer(question.getCorrectAnswer());
    questionDto.setSubCategoryId(question.getSubCategory().getSubCategoryId());
    return questionDto;
  }
  @Override
  public final QuestionDto updateQuestion(final Long questionId, final
          QuestionDto question) {
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
        return question;
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
