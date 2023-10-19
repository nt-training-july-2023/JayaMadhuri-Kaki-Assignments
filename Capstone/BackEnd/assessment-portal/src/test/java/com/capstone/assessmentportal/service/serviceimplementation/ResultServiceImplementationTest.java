package com.capstone.assessmentportal.service.serviceimplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.model.Results;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.model.Users;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.repository.ResultRepo;
import com.capstone.assessmentportal.repository.SubCategoryRepo;
import com.capstone.assessmentportal.repository.UsersRepo;

class resultsServiceImplementationTest {
    @InjectMocks
    ResultServiceImplementation resultsService;
    @Mock
    ResultRepo resultRepo;
    @Mock
    UsersRepo usersRepo;
    @Mock
    SubCategoryRepo subCategoryRepo;
    @Mock
    CategoryRepo categoryRepo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddTemporaryResultIfUserIdNotExists() {
        ResultsDto resultsDto = new ResultsDto();
        resultsDto.setResultId(1L);
        resultsDto.setCategoryName("Java");
        resultsDto.setStudentId(11L);
        resultsDto.setStudentName("Jaya Madhuri");
        resultsDto.setStudentEmailId("madhu@nucleusteq.com");
        resultsDto.setQuizName("Array");
        resultsDto.setMarksObtained(10);
        resultsDto.setTotalMarks(10);
        resultsDto.setNumOfAttemptedQuestions(9);
        resultsDto.setTotalQuestions(10);
        resultsDto.setDateAndTime("23-10-23");
        
        when(usersRepo.findById(10L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> resultsService.addResult(resultsDto));
    }
    
    @Test
    void testAddTemporaryResultIfSubCategoryNameNotExists() {
        ResultsDto resultsDto = new ResultsDto(1L,1L,"madhu@nucleusteq.com","Madhuri Kaki","Java","String",10,10,10,10,"2001-01-23 15:42:32");
        Users users = new Users();
        when(usersRepo.findById(10L)).thenReturn(Optional.of(users));
        when(subCategoryRepo.getSubCategoryByName(resultsDto.getQuizName())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> resultsService.addResult(resultsDto));
    }
    
    @Test
    void testAddTemporaryResultIfCategoryNameNotExists() {
        ResultsDto resultsDto = new ResultsDto(1L,1L,"madhu@nucleusteq.com","Madhuri Kaki","Java","String",10,10,10,10,"2001-01-23 15:42:32");
        Users users = new Users();
        SubCategory subCategory = new SubCategory();
        when(usersRepo.findById(10L)).thenReturn(Optional.of(users));
        when(subCategoryRepo.getSubCategoryByName(resultsDto.getQuizName())).thenReturn(Optional.of(subCategory));
        when(categoryRepo.getCategoryByName(resultsDto.getCategoryName())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> resultsService.addResult(resultsDto));
    }
    
    @Test
    void testAddTemporaryResult() {
        ResultsDto resultsDto = new ResultsDto(1L,1L,"madhu@nucleusteq.com","Madhuri Kaki","Java","String",10,10,10,10,"2001-01-23 15:42:32");
        Results res = new Results();
        res.setResultId(resultsDto.getResultId());
        res.setCategoryName(resultsDto.getCategoryName());
        res.setStudentId(resultsDto.getStudentId());
        res.setStudentName(resultsDto.getStudentName());
        res.setStudentEmailId(resultsDto.getStudentEmailId());
        res.setQuizName(resultsDto.getQuizName());
        res.setTotalMarks(resultsDto.getTotalMarks());
        res.setMarksObtained(resultsDto.getMarksObtained());
        res.setNumOfAttemptedQuestions(resultsDto.getNumOfAttemptedQuestions());
        res.setTotalQuestions(resultsDto.getTotalQuestions());
        res.setDateAndTime(resultsDto.getDateAndTime());
        Users users = new Users();
        SubCategory subCategory = new SubCategory();
        Category category = new Category();
        when(usersRepo.findById(res.getStudentId())).thenReturn(Optional.of(users));
        when(categoryRepo.getCategoryByName(res.getCategoryName())).thenReturn(Optional.of(category));
        when(subCategoryRepo.getSubCategoryByName(res.getQuizName())).thenReturn(Optional.of(subCategory));
        ResultsDto resultsdto = resultsService.addResult(resultsDto);
        assertNotNull(resultsdto);
        assertEquals(resultsDto,resultsdto);
    }
    
    @Test
    void testGetAllFinalResults() {
        ResultsDto resultsDto = new ResultsDto();
        resultsDto.setStudentId(1L);
        resultsDto.setStudentName("Madhuri");
        resultsDto.setQuizName("Array");
        resultsDto.setCategoryName("Java");
        resultsDto.setMarksObtained(9);
        resultsDto.setNumOfAttemptedQuestions(9);
        resultsDto.setTotalMarks(10);
        resultsDto.setTotalQuestions(10);
        resultsDto.setDateAndTime("23-01-23");
        
        Results finalResults = new Results(resultsDto.getResultId(),
                resultsDto.getStudentId(), resultsDto.getStudentEmailId(),
                resultsDto.getStudentName(),
                resultsDto.getCategoryName(),resultsDto.getQuizName(),
                resultsDto.getMarksObtained(),
                resultsDto.getTotalMarks(),resultsDto.getNumOfAttemptedQuestions()
                ,resultsDto.getTotalQuestions(),resultsDto.getDateAndTime());
        
        List<Results> listoffinalresults = new ArrayList<>();
        listoffinalresults.add(finalResults);
        resultRepo.save(finalResults);
        when(resultRepo.findAll()).thenReturn(listoffinalresults);
        List<ResultsDto> resultDto = resultsService.getResults();
        assertEquals(Collections.singletonList(resultsDto),resultDto);
    }

    @Test
    void testGetFinalResultByStudentEmail() {
        Long studentId = 1L;
        ResultsDto finalDto = new ResultsDto(10L,studentId,"jaya@nucleusteq.com","Madhuri","Array",
                "Java",9,9,10,10,"23-10-23");
        
        Results finalResults = new Results(finalDto.getResultId(),
                finalDto.getStudentId(), finalDto.getStudentEmailId(),
                finalDto.getStudentName(),
                finalDto.getCategoryName(),finalDto.getQuizName(),
                finalDto.getMarksObtained(),
                finalDto.getTotalMarks(),finalDto.getNumOfAttemptedQuestions(),
                finalDto.getTotalQuestions(),finalDto.getDateAndTime());
        
        List<Results> listoffinalresults = new ArrayList<>();
        listoffinalresults.add(finalResults);
        resultRepo.save(finalResults);
        when(resultRepo.getResultsByStudentEmail(finalDto.getStudentEmailId())).thenReturn(listoffinalresults);
        List<ResultsDto> ResultsDto = resultsService.getResultByStudentEmail(finalDto.getStudentEmailId());
        assertEquals(Collections.singletonList(finalDto),ResultsDto);
    }
    
    @Test
    void testGetFinalResultByStudentEmailNotExists() {
        Long studentId = 1L;
        ResultsDto finalDto = new ResultsDto(10L,studentId,"jaya@nucleusteq.com","Madhuri","Array",
                "Java",9,9,10,10,"23-10-23");
        
        Results finalResults = new Results(finalDto.getResultId(),
                finalDto.getStudentId(), finalDto.getStudentEmailId(),
                finalDto.getStudentName(),finalDto.getQuizName()
                ,finalDto.getCategoryName(),
                finalDto.getMarksObtained(),finalDto.getNumOfAttemptedQuestions(),
                finalDto.getTotalMarks(),finalDto.getTotalQuestions(),finalDto.getDateAndTime());
        
        List<Results> listoffinalresults = new ArrayList<>();
        resultRepo.save(finalResults);
        when(resultRepo.getResultsByStudentEmail(finalDto.getStudentEmailId())).thenReturn(listoffinalresults);
        assertTrue(listoffinalresults.isEmpty());
        assertThrows(NoSuchElementException.class, () -> resultsService.getResultByStudentEmail(finalResults.getStudentEmailId()));
    }
}
