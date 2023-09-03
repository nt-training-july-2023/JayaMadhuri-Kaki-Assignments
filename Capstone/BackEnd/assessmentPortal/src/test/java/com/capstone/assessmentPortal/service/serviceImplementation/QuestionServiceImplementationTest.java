package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.assessmentPortal.dto.QuestionDto;
import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.model.Question;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.QuestionRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;

import java.util.List;

@SpringBootTest
class QuestionServiceImplementationTest {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuestionServiceImplementation questionService;
    @Autowired
    SubCategoryRepo subCategoryRepo;
    @BeforeEach
    void setUp() {
        subCategoryRepo = mock(SubCategoryRepo.class);
        questionRepo = mock(QuestionRepo.class);
        questionService = new QuestionServiceImplementation(subCategoryRepo,questionRepo);
    }
    @Test
    void testAddQuestionIfFieldsEmpty() {
       Long questionId = 1L;
       QuestionDto questionDto = new QuestionDto();
       questionDto.setQuestionContent("");
       Question question = new Question();
       question.setQuestionId(questionId);
       question.setQuestionContent(questionDto.getQuestionContent());
       when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
       assertThrows(InputEmptyException.class, () -> questionService.addQuestion(questionDto));
    }
    
    @Test
    void testAddQuestionIfSubcategoryIdNotFound() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContent("");
        questionDto.setOptionA("a");
        questionDto.setOptionB("b");
        questionDto.setOptionC("c");
        questionDto.setOptionD("d");
        questionDto.setCorrectAnswer("c");
        questionDto.setSubCategoryId(10L);
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionContent(questionDto.getQuestionContent());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        question.setCorrectAnswer(question.getCorrectAnswer());
        when(subCategoryRepo.findById(questionDto.getSubCategoryId())).thenReturn(Optional.empty());
        assertThrows(InputEmptyException.class, () -> questionService.addQuestion(questionDto));
    }
    
    @Test
    void testAddQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContent("what is array");
        questionDto.setOptionA("a");
        questionDto.setOptionB("b");
        questionDto.setOptionC("c");
        questionDto.setOptionD("d");
        questionDto.setCorrectAnswer("c");
        questionDto.setSubCategoryId(10L);
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionContent(questionDto.getQuestionContent());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        question.setCorrectAnswer(questionDto.getCorrectAnswer());
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(questionDto.getSubCategoryId());
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
        when(subCategoryRepo.findById(questionDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        question.setSubCategory(subCategory);
        questionRepo.save(question);
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        QuestionDto questionDto1 = questionService.addQuestion(questionDto);
        assertEquals(question.getCorrectAnswer(), questionDto1.getCorrectAnswer());
    }

    @Test
    void testGetQuestionsBySubCategoryIdNotExists() {
       Long subCategoryId = 1L;
       when(subCategoryRepo.findById(subCategoryId)).thenReturn(Optional.empty());
       assertThrows(NoSuchElementException.class, () -> questionService.getQuestionsBySubCategoryId(subCategoryId));
    }
    
    @Test
    void testGetQuestionsBySubCategoryId() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContent("what is array");
        questionDto.setOptionA("a");
        questionDto.setOptionB("b");
        questionDto.setOptionC("c");
        questionDto.setOptionD("d");
        questionDto.setCorrectAnswer("c");
        questionDto.setSubCategoryId(10L);
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionContent(questionDto.getQuestionContent());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        question.setCorrectAnswer(questionDto.getCorrectAnswer());
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(questionDto.getSubCategoryId());
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
        when(subCategoryRepo.findById(questionDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        question.setSubCategory(subCategory);
        List<Question> questionsList = new ArrayList<>();
        questionsList.add(question);
        questionRepo.save(question);
        when(questionRepo.getQuestionBySubCategoryId(questionDto.getSubCategoryId())).thenReturn(questionsList);
        List<QuestionDto> questionListDto = questionService.getQuestionsBySubCategoryId(questionDto.getSubCategoryId());
        assertEquals("what is array", questionListDto.get(0).getQuestionContent());
    }

    @Test
    void testUpdateQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContent("what is array");
        questionDto.setOptionA("a");
        questionDto.setOptionB("b");
        questionDto.setOptionC("c");
        questionDto.setOptionD("d");
        questionDto.setCorrectAnswer("c");
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionContent(questionDto.getQuestionContent());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        question.setCorrectAnswer(questionDto.getCorrectAnswer());
        
        QuestionDto questionDto1 = new QuestionDto();
        questionDto1.setQuestionContent("what is array");
        questionDto1.setOptionA("d");
        questionDto1.setOptionB("b");
        questionDto1.setOptionC("c");
        questionDto1.setOptionD("a");
        questionDto1.setCorrectAnswer("c");
        Question question1 = new Question();
        question1.setQuestionId(questionId);
        question1.setQuestionContent(questionDto1.getQuestionContent());
        question1.setOptionA(questionDto1.getOptionA());
        question1.setOptionB(questionDto1.getOptionB());
        question1.setOptionC(questionDto1.getOptionC());
        question1.setOptionD(questionDto1.getOptionD());
        question1.setCorrectAnswer(questionDto1.getCorrectAnswer());
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        when(questionRepo.save(question)).thenReturn(question1);
        QuestionDto questiondto = questionService.updateQuestion(questionId, questionDto1);
        assertEquals(question1.getOptionA(),questiondto.getOptionA());
    }
    
    @Test
    void updateQuestionIfIdNotExists() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        when(questionRepo.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> questionService.updateQuestion(questionId,questionDto)); 
    }
    
    @Test
    void updateQuestionIfEmptyFields() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContent("");
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionContent(questionDto.getQuestionContent());
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        assertThrows(InputEmptyException.class, () -> questionService.updateQuestion(questionId,questionDto));
    }

    @Test
    void testDeleteQuestionIfIdNotExists() {
        Long questionId = 1L;
        when(questionRepo.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> questionService.deleteQuestion(questionId)); 
    }

    @Test
    void testDelete() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContent("what is array");
        questionDto.setOptionA("a");
        questionDto.setOptionB("b");
        questionDto.setOptionC("c");
        questionDto.setOptionD("d");
        questionDto.setCorrectAnswer("c");
        questionDto.setSubCategoryId(10L);
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestionContent(questionDto.getQuestionContent());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        questionService.deleteQuestion(questionId);
        assertFalse(questionRepo.existsById(questionId));
    }
}
