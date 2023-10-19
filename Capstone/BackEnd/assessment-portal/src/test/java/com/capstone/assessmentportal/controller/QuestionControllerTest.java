package com.capstone.assessmentportal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
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
        CustomResponse<List<QuestionDto>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Retrieved Questions By Quiz Id");
        expectedResponse.setResponseData(questionlist);
        when(questionService.getQuestionsBySubCategoryId(questionDto.getSubCategoryId())).thenReturn(questionlist);
        CustomResponse<List<QuestionDto>> response = questionController
                .getQuestionsBySubCategoryId(questionDto.getSubCategoryId());
        assertEquals(expectedResponse,response);
    }

    @Test
    void testAddQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d",Option.optionA,10L);
        CustomResponse<QuestionDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Question Successfully Added");
        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        CustomResponse<QuestionDto> response = questionController.addQuestion(questionDto);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testUpdateQuestion() {
        Long questionId = 1L;
        QuestionDto questionDto = new QuestionDto(questionId,"what is array","a","b","c","d",Option.optionA,10L);
        CustomResponse<QuestionDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Question Successfully Updated");
        when(questionService.updateQuestion(questionId,questionDto)).thenReturn(questionDto);
        CustomResponse<QuestionDto> response = questionController.updateQuestion(questionId,questionDto);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        CustomResponse<QuestionDto> response = questionController.deleteQuestion(questionId);
        CustomResponse<QuestionDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Question Successfully Deleted");
        assertEquals(expectedResponse,response);
    }

}
