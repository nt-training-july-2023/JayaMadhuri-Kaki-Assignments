package com.capstone.assessmentportal.controller;

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
import com.capstone.assessmentportal.dto.Option;
import com.capstone.assessmentportal.dto.QuestionDto;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.service.serviceimplementation.QuestionServiceImplementation;

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
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d",Option.optionA,10L);
        List<QuestionDto> questionlist = new ArrayList<>();
        when(questionService.getQuestionsBySubCategoryId(questionDto.getSubCategoryId())).thenReturn(questionlist);
        CustomResponse<List<QuestionDto>> response = questionController.getQuestionsBySubCategoryId(questionDto.getSubCategoryId());
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(questionlist,response.getResponseData());
        assertEquals("Successfully Retrieved Questions By Quiz Id",response.getMessage());
    }

    @Test
    void testAddQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d",Option.optionA,10L);
        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        CustomResponse<QuestionDto> response = questionController.addQuestion(questionDto);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals("Question Successfully Added",response.getMessage());
    }

    @Test
    void testUpdateQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d",Option.optionA,10L);
        when(questionService.updateQuestion(questionId,questionDto)).thenReturn(questionDto);
        CustomResponse<QuestionDto> response = questionController.updateQuestion(questionId,questionDto);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals("Question Successfully Updated",response.getMessage());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        CustomResponse<QuestionDto> response = questionController.deleteQuestion(questionId);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals("Question Successfully Deleted",response.getMessage());
    }

}
