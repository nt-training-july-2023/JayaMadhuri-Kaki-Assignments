package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capstone.assessmentPortal.dto.FinalResultsDto;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;

class FinalResultServiceImplementationTest {
    @Autowired
    FinalResultServiceImplementation finalResultsService;
    @Autowired
    FinalResultOfQuizRepo finalResultsRepo;
    @BeforeEach
    void setUp() {
        finalResultsRepo = mock(FinalResultOfQuizRepo.class);
        finalResultsService = new FinalResultServiceImplementation(finalResultsRepo);
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
        List<FinalResultsDto> finalResultsDto = finalResultsService.getAllFinalResults();
        assertEquals(listoffinalresults.get(0).getCategoryName(), finalResultsDto.get(0).getCategoryName());
    }

    @Test
    void testGetFinalResultByStudentId() {
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
    }

    @Test
    void testGetFinalResultsByStudentIdQuizName() {
       Long studentId = 1L;
       String quizName = "Java";
       FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz();
       when(finalResultsRepo.getFinalResultsByStudentIdQuizName(studentId, quizName))
       .thenReturn(Optional.of(finalResults));
       Optional<FinalResultsDto> finalResultsDto = finalResultsService
               .getFinalResultsByStudentIdQuizName(studentId, quizName);
       assertEquals(finalResults.getCategoryName(), finalResultsDto.get().getCategoryName());
    }

}
