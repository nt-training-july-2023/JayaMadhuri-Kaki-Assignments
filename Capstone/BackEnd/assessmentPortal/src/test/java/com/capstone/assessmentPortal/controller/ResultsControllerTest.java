package com.capstone.assessmentPortal.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.assessmentPortal.dto.ResultsDto;
import com.capstone.assessmentPortal.service.serviceImplementation.ResultServiceImplementation;

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
        ResponseEntity<Object> response = resultsController.addResult(resultsDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
