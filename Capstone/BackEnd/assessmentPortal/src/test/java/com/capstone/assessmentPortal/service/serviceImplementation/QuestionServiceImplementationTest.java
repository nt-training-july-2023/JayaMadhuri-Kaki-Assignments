package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.assessmentPortal.dto.QuestionDto;
import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.model.Question;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.QuestionRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;

import java.util.List;

@SpringBootTest
class QuestionServiceImplementationTest {
    @Mock
    QuestionRepo questionRepo;
    @InjectMocks
    QuestionServiceImplementation questionService;
    @Mock
    SubCategoryRepo subCategoryRepo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddQuestionIfSubcategoryIdNotFound() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(questionId);
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
        question.setCorrectAnswer(question.getCorrectAnswer());
        when(subCategoryRepo.findById(questionDto.getSubCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> questionService.addQuestion(questionDto));
    }
    
    @Test
    void testAddQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        Question question = new Question(questionDto.getQuestionContent(),
                questionDto.getOptionA(),questionDto.getOptionB(),
                questionDto.getOptionC(),questionDto.getOptionD(),questionDto.getCorrectAnswer());
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
    void testAddQuestionIfSameOption() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","a","c","d","c",10L);
        Question question = new Question(questionDto.getQuestionContent(),
                questionDto.getOptionA(),questionDto.getOptionB(),
                questionDto.getOptionC(),questionDto.getOptionD(),questionDto.getCorrectAnswer());
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(questionDto.getSubCategoryId());
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
        when(subCategoryRepo.findById(questionDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        question.setSubCategory(subCategory);
        questionRepo.save(question);
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        assertThrows(AlreadyExistsException.class, () -> questionService.addQuestion(questionDto));
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
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        Question question = new Question(questionDto.getQuestionContent(),
                questionDto.getOptionA(),questionDto.getOptionB(),
                questionDto.getOptionC(),questionDto.getOptionD(),questionDto.getCorrectAnswer());
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
        assertEquals(null,questionListDto.get(0).getQuestionId());
    }

    @Test
    void testUpdateQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        Question question = new Question(questionDto.getQuestionContent(),
                questionDto.getOptionA(),questionDto.getOptionB(),questionDto.getOptionC(),
                questionDto.getOptionD(),questionDto.getCorrectAnswer());
        QuestionDto questionDto1 = new QuestionDto();
        questionDto1.setQuestionId(questionId);
        questionDto1.setQuestionContent("what is array");
        questionDto1.setOptionA("d");
        questionDto1.setOptionB("b");
        questionDto1.setOptionC("c");
        questionDto1.setOptionD("a");
        questionDto1.setCorrectAnswer("c");
        Question question1 = new Question(questionDto1.getQuestionContent(),
                questionDto1.getOptionA(),questionDto1.getOptionB(),
                questionDto1.getOptionC(),questionDto1.getOptionD(),questionDto1.getCorrectAnswer());
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        when(questionRepo.save(question)).thenReturn(question1);
        QuestionDto questiondto = questionService.updateQuestion(questionId, questionDto1);
        assertEquals(question1.getOptionA(),questiondto.getOptionA());
    }
    
    @Test
    void testUpdateQuestionIfSameOption() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        Question question = new Question(questionDto.getQuestionContent(),
                questionDto.getOptionA(),questionDto.getOptionB(),questionDto.getOptionC(),
                questionDto.getOptionD(),questionDto.getCorrectAnswer());
        QuestionDto questionDto1 = new QuestionDto();
        questionDto1.setQuestionId(questionId);
        questionDto1.setQuestionContent("what is array");
        questionDto1.setOptionA("d");
        questionDto1.setOptionB("d");
        questionDto1.setOptionC("c");
        questionDto1.setOptionD("a");
        questionDto1.setCorrectAnswer("c");
        Question question1 = new Question(questionDto1.getQuestionContent(),
                questionDto1.getOptionA(),questionDto1.getOptionB(),
                questionDto1.getOptionC(),questionDto1.getOptionD(),questionDto1.getCorrectAnswer());
        when(questionRepo.findById(questionId)).thenReturn(Optional.of(question));
        when(questionRepo.save(question)).thenReturn(question1);
        assertThrows(AlreadyExistsException.class, () -> questionService.updateQuestion(questionId, questionDto1));
        
    }
    
    @Test
    void updateQuestionIfIdNotExists() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(questionId);
        when(questionRepo.findById(questionId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> questionService.updateQuestion(questionId,questionDto)); 
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
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
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
