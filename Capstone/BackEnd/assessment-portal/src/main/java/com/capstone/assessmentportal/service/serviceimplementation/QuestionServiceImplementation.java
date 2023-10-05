package com.capstone.assessmentportal.service.serviceimplementation;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.assessmentportal.dto.QuestionDto;
import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.model.Question;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.repository.QuestionRepo;
import com.capstone.assessmentportal.repository.SubCategoryRepo;
import com.capstone.assessmentportal.response.ValidationMessage;
import com.capstone.assessmentportal.service.QuestionService;


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
@Override
  public final QuestionDto addQuestion(final QuestionDto questionDto) {
      SubCategory subCategory = subCategoryRepo.findById(
              questionDto.getSubCategoryId()).orElseThrow(() ->
               new NoSuchElementException(ValidationMessage
                       .QUIZ_NOSUCHELEMENT));
      Set<String> options = new HashSet<>();
      options.add(questionDto.getOptionA());
      options.add(questionDto.getOptionB());
      options.add(questionDto.getOptionC());
      options.add(questionDto.getOptionD());
      final int number = 4;
      if (options.size() < number) {
          throw new AlreadyExistsException(ValidationMessage
                  .QUESTION_ALREADYEXISTS);
      }
      Question question = new Question();
      question.setQuestionContent(questionDto.getQuestionContent());
      question.setOptionA(questionDto.getOptionA());
      question.setOptionB(questionDto.getOptionB());
      question.setOptionC(questionDto.getOptionC());
      question.setOptionD(questionDto.getOptionD());
      question.setCorrectAnswer(questionDto.getCorrectAnswer());
      question.setSubCategory(subCategory);
      logger.info(ValidationMessage.QUESTIONS_ADDED);
      questionRepo.save(question);
      return questionDto;
  }
  @Override
  public final List<QuestionDto> getQuestionsBySubCategoryId(final
                   Long subCategoryId) {
    subCategoryRepo.findById(subCategoryId).orElseThrow(
                      () -> new NoSuchElementException(ValidationMessage
                              .QUIZ_NOSUCHELEMENT));
    List<Question> listOfQuestions =
              questionRepo.getQuestionBySubCategoryId(subCategoryId);
    logger.info(ValidationMessage.QUESTIONS_RETRIEVED);
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
    logger.info(ValidationMessage.QUESTIONS_LOGGER_MSG);
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
                        () -> new NoSuchElementException(ValidationMessage
                                .QUESTION_NOSUCHELEMENT));
      question.setQuestionContent(questionDto.getQuestionContent());
      Set<String> options = new HashSet<>();
      options.add(questionDto.getOptionA());
      options.add(questionDto.getOptionB());
      options.add(questionDto.getOptionC());
      options.add(questionDto.getOptionD());
      final int number = 4;
      if (options.size() < number) {
          throw new AlreadyExistsException(ValidationMessage
                  .QUESTION_ALREADYEXISTS);
      }
      question.setOptionA(questionDto.getOptionA());
      question.setOptionB(questionDto.getOptionB());
      question.setOptionC(questionDto.getOptionC());
      question.setOptionD(questionDto.getOptionD());
      question.setCorrectAnswer(questionDto.getCorrectAnswer());
      logger.info(ValidationMessage.QUESTIONS_UPDATED);
      questionRepo.save(question);
      return questionDto;
  }
  @Override
  public final void deleteQuestion(final Long questionId) {
    questionRepo.findById(questionId).orElseThrow(
            () -> new NoSuchElementException(ValidationMessage
                    .QUESTION_NOSUCHELEMENT));
    logger.info(ValidationMessage.QUESTIONS_DELETED);
    questionRepo.deleteById(questionId);
  }
}
