package com.capstone.assessmentPortal.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.assessmentPortal.dto.QuestionDto;
import com.capstone.assessmentPortal.service.serviceImplementation.QuestionServiceImplementation;

@SpringBootTest
class QuestionControllerTest {
    @Mock
    QuestionServiceImplementation questionService;
    @InjectMocks
    QuestionController questionController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllQuestionsBySubCategoryId() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        List<QuestionDto> questionlist = new ArrayList<>();
        when(questionService.getQuestionsBySubCategoryId(questionDto.getSubCategoryId())).thenReturn(questionlist);
        ResponseEntity<Object> response = questionController.getAllQuestionsBySubCategoryId(questionDto.getSubCategoryId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testAddQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        ResponseEntity<Object> response = questionController.addQuestion(questionDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testUpdateQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d","c",10L);
        when(questionService.updateQuestion(questionId,questionDto)).thenReturn(questionDto);
        ResponseEntity<Object> response = questionController.updateQuestion(questionId,questionDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        ResponseEntity<Object> response = questionController.deleteQuestion(questionId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
