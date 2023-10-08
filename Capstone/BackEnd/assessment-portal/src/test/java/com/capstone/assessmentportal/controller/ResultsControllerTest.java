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
        when(resultService.addResult(resultsDto)).thenReturn(resultsDto);
        CustomResponse<ResultsDto> response = resultsController.addResult(resultsDto);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals("Results Successfully Added",response.getMessage());
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
        when(resultService.getResults()).thenReturn(list);
        CustomResponse<List<ResultsDto>> response = resultsController.getResults();
        CustomResponse<List<ResultsDto>> res = new CustomResponse<List<ResultsDto>>(); 
        res.setMessage("Successfully Retrieved");
        res.setStatusCode(200);
        res.setResponseData(list);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(list,response.getResponseData());
        assertEquals("Results Successfully Retrieved",response.getMessage());
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
        when(resultService.getResultByStudentEmail(finalDto.getStudentEmailId())).thenReturn(list);
        CustomResponse<List<ResultsDto>> response = resultsController
                .getResultByStudentEmail(finalDto.getStudentEmailId());
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(list,response.getResponseData());
        assertEquals("Results Successfully Retrieved",response.getMessage());
    }
}
