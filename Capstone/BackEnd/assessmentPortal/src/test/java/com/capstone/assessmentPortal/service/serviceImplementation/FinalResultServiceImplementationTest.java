package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.assessmentPortal.dto.FinalResultsDto;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;

class FinalResultServiceImplementationTest {
    @InjectMocks
    FinalResultServiceImplementation finalResultsService;
    @Mock
    FinalResultOfQuizRepo finalResultsRepo;
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
        
        FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz(finalDto.getFinalResultId(),
                finalDto.getStudentId(), finalDto.getStudentEmailId(),
                finalDto.getStudentName(),finalDto.getQuizName()
                ,finalDto.getCategoryName(),
                finalDto.getMarksObtained(),finalDto.getNumOfAttemptedQuestions(),
                finalDto.getTotalMarks(),finalDto.getTotalQuestions(),finalDto.getDateAndTime());
        
        List<FinalResultsOfQuiz> listoffinalresults = new ArrayList<>();
        listoffinalresults.add(finalResults);
        finalResultsRepo.save(finalResults);
        when(finalResultsRepo.findAll()).thenReturn(listoffinalresults);
        List<FinalResultsDto> finalResultsDto = finalResultsService.getFinalResults();
        assertEquals(listoffinalresults.get(0).getCategoryName(), finalResultsDto.get(0).getCategoryName());
        assertEquals(listoffinalresults.get(0).getQuizName(), finalResultsDto.get(0).getQuizName());
    }

    @Test
    void testGetFinalResultByStudentEmail() {
        Long studentId = 1L;
        FinalResultsDto finalDto = new FinalResultsDto(10L,studentId,"jaya@nucleusteq.com","Madhuri","Array",
                "Java",9,9,10,10,"23-10-23");
        
        FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz(finalDto.getFinalResultId(),
                finalDto.getStudentId(), finalDto.getStudentEmailId(),
                finalDto.getStudentName(),finalDto.getQuizName()
                ,finalDto.getCategoryName(),
                finalDto.getMarksObtained(),finalDto.getNumOfAttemptedQuestions(),
                finalDto.getTotalMarks(),finalDto.getTotalQuestions(),finalDto.getDateAndTime());
        
        List<FinalResultsOfQuiz> listoffinalresults = new ArrayList<>();
        listoffinalresults.add(finalResults);
        finalResultsRepo.save(finalResults);
        when(finalResultsRepo.getFinalResultsByStudentEmail(finalDto.getStudentEmailId())).thenReturn(listoffinalresults);
        List<FinalResultsDto> finalResultsDto = finalResultsService.getFinalResultByStudentEmail(finalDto.getStudentEmailId());
        assertEquals(listoffinalresults.get(0).getCategoryName(), finalResultsDto.get(0).getCategoryName());
        assertEquals(listoffinalresults.get(0).getQuizName(), finalResultsDto.get(0).getQuizName());
    }
    
    @Test
    void testGetFinalResultByStudentEmailNotExists() {
        Long studentId = 1L;
        FinalResultsDto finalDto = new FinalResultsDto(10L,studentId,"jaya@nucleusteq.com","Madhuri","Array",
                "Java",9,9,10,10,"23-10-23");
        
        FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz(finalDto.getFinalResultId(),
                finalDto.getStudentId(), finalDto.getStudentEmailId(),
                finalDto.getStudentName(),finalDto.getQuizName()
                ,finalDto.getCategoryName(),
                finalDto.getMarksObtained(),finalDto.getNumOfAttemptedQuestions(),
                finalDto.getTotalMarks(),finalDto.getTotalQuestions(),finalDto.getDateAndTime());
        
        List<FinalResultsOfQuiz> listoffinalresults = new ArrayList<>();
        finalResultsRepo.save(finalResults);
        when(finalResultsRepo.getFinalResultsByStudentEmail(finalDto.getStudentEmailId())).thenReturn(listoffinalresults);
        assertTrue(listoffinalresults.isEmpty());
        assertThrows(NoSuchElementException.class, () -> finalResultsService.getFinalResultByStudentEmail(finalResults.getStudentEmailId()));
    }
}
