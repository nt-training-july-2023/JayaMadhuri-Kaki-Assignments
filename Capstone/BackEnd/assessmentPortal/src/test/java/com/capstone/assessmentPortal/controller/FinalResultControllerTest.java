/**
 * 
 */
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

import com.capstone.assessmentPortal.dto.FinalResultsDto;
import com.capstone.assessmentPortal.service.serviceImplementation.FinalResultServiceImplementation;

/**
 * 
 */
@SpringBootTest
class FinalResultControllerTest {
    @Mock
    FinalResultServiceImplementation finalResults;
    @InjectMocks
    FinalResultController finalResultController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllFinalResults() {
        FinalResultsDto finalDto = new FinalResultsDto();
        finalDto.setStudentId(1L);
        finalDto.setStudentName("Madhuri");
        finalDto.setQuizName("Array");
        finalDto.setCategoryName("Java");
        finalDto.setMarksObtained(9);
        finalDto.setNumOfAttemptedQuestions(9);
        finalDto.setTotalMarks(10);
        finalDto.setTotalQuestions(10);
        finalDto.setDateAndTime("23-01-23");
        List<FinalResultsDto> list = new ArrayList<>();
        when(finalResults.getAllFinalResults()).thenReturn(list);
        ResponseEntity<Object> response = finalResultController.getAllFinalResults();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    void testGetAllFinalResultByStudentId() {
        FinalResultsDto finalDto = new FinalResultsDto();
        finalDto.setStudentId(1L);
        finalDto.setStudentName("Madhuri");
        finalDto.setQuizName("Array");
        finalDto.setCategoryName("Java");
        finalDto.setMarksObtained(9);
        finalDto.setNumOfAttemptedQuestions(9);
        finalDto.setTotalMarks(10);
        finalDto.setTotalQuestions(10);
        finalDto.setDateAndTime("23-01-23");
        List<FinalResultsDto> list = new ArrayList<>();
        when(finalResults.getFinalResultByStudentEmail(finalDto.getStudentEmailId())).thenReturn(list);
        ResponseEntity<Object> response = finalResultController
                .getAllFinalResultByStudentEmail(finalDto.getStudentEmailId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
