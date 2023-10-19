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
import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.service.serviceimplementation.ResultServiceImplementation;

@SpringBootTest
class ResultsControllerTest {
    @Mock
    ResultServiceImplementation resultService;
    @InjectMocks
    ResultsController resultsController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddResult() {
        ResultsDto resultsDto = new ResultsDto(1L,1L,"madhu@nucleusteq.com","Madhuri Kaki","Java","String",10,10,10,10,"2001-01-23 15:42:32");
        CustomResponse<ResultsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Results Successfully Added");
        when(resultService.addResult(resultsDto)).thenReturn(resultsDto);
        CustomResponse<ResultsDto> response = resultsController.addResult(resultsDto);
        assertEquals(expectedResponse,response);
    }
    @Test
    void testGetAllResults() {
        ResultsDto finalDto = new ResultsDto();
        finalDto.setStudentId(1L);
        finalDto.setStudentName("Madhuri");
        finalDto.setQuizName("Array");
        finalDto.setCategoryName("Java");
        finalDto.setMarksObtained(9);
        finalDto.setNumOfAttemptedQuestions(9);
        finalDto.setTotalMarks(10);
        finalDto.setTotalQuestions(10);
        finalDto.setDateAndTime("23-01-23");
        
        List<ResultsDto> list = new ArrayList<>();
        CustomResponse<List<ResultsDto>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Results Successfully Retrieved");
        expectedResponse.setResponseData(list);
        
        when(resultService.getResults()).thenReturn(list);
        CustomResponse<List<ResultsDto>> response = resultsController.getResults();
        assertEquals(expectedResponse,response);
    }
    @Test
    void testGetAllResultByStudentId() {
        ResultsDto finalDto = new ResultsDto();
        finalDto.setStudentId(1L);
        finalDto.setStudentName("Madhuri");
        finalDto.setQuizName("Array");
        finalDto.setCategoryName("Java");
        finalDto.setMarksObtained(9);
        finalDto.setNumOfAttemptedQuestions(9);
        finalDto.setTotalMarks(10);
        finalDto.setTotalQuestions(10);
        finalDto.setDateAndTime("23-01-23");
        
        List<ResultsDto> list = new ArrayList<>();
        CustomResponse<List<ResultsDto>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Results Successfully Retrieved");
        expectedResponse.setResponseData(list);
        
        when(resultService.getResultByStudentEmail(finalDto.getStudentEmailId())).thenReturn(list);
        CustomResponse<List<ResultsDto>> response = resultsController
                .getResultByStudentEmail(finalDto.getStudentEmailId());
        assertEquals(expectedResponse,response);
    }
}
