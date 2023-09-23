package com.capstone.assessmentPortal.service.serviceImplementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentPortal.dto.QuestionDto;
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
   *logger instance.
  */
  private Logger logger = LoggerFactory.getLogger(
          QuestionServiceImplementation.class);
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
  public final QuestionDto addQuestion(final QuestionDto questionDto) {
      SubCategory subCategory = subCategoryRepo.findById(
              questionDto.getSubCategoryId()).orElseThrow(() ->
               new NoSuchElementException());
      logger.info("Question Added");
      Question question = new Question();
      question.setQuestionContent(questionDto.getQuestionContent());
      question.setOptionA(questionDto.getOptionA());
      question.setOptionB(questionDto.getOptionB());
      question.setOptionC(questionDto.getOptionC());
      question.setOptionD(questionDto.getOptionD());
      question.setCorrectAnswer(questionDto.getCorrectAnswer());
      question.setSubCategory(subCategory);
      questionRepo.save(question);
      return questionDto;
  }
  @Override
  public final List<QuestionDto> getQuestionsBySubCategoryId(final
                   Long subCategoryId) {
    subCategoryRepo.findById(subCategoryId).orElseThrow(
                      () -> new NoSuchElementException());
    List<Question> listOfQuestions =
              questionRepo.getQuestionBySubCategoryId(subCategoryId);
    logger.info("Retrieved Question by Quiz id");
    return listOfQuestions.stream()
              .map(this::convertEntityToDto)
              .collect(Collectors.toList());
  }
  /**
   * converting entity to dto for get all method.
   * @return questionDto
   * @param question question
  */
  private QuestionDto convertEntityToDto(final
          Question question) {
    logger.info("Entity to Dto conversion in Questions");
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
          QuestionDto questionDto) {
    Question question = questionRepo
                .findById(questionId).orElseThrow(
                        () -> new NoSuchElementException());
      logger.info("Question with id exists");
      question.setQuestionContent(questionDto.getQuestionContent());
      question.setOptionA(questionDto.getOptionA());
      question.setOptionB(questionDto.getOptionB());
      question.setOptionC(questionDto.getOptionC());
      question.setOptionD(questionDto.getOptionD());
      question.setCorrectAnswer(questionDto.getCorrectAnswer());
      logger.info("Question Updated");
      questionRepo.save(question);
      return questionDto;
  }
  @Override
  public final void deleteQuestion(final Long questionId) {
    questionRepo.findById(questionId).orElseThrow(
            () -> new NoSuchElementException());
    logger.info("Question Deleted");
    questionRepo.deleteById(questionId);
  }
}
