package com.capstone.assessmentportal.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        ResultsDto resultsDto = new ResultsDto(1L,10L,11L,12L,10,9,9,9,"23-10-23");
        when(resultService.addTemporaryResult(resultsDto)).thenReturn(resultsDto);
        CustomResponse<ResultsDto> response = resultsController.addResult(resultsDto);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("Results Successfully Added",response.getMessage());
    }
}