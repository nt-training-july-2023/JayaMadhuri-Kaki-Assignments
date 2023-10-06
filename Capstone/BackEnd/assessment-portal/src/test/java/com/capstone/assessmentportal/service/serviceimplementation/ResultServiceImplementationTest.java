package com.capstone.assessmentportal.service.serviceimplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.assessmentportal.dto.ResultsDto;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.model.FinalResultsOfQuiz;
import com.capstone.assessmentportal.model.Results;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.model.Users;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentportal.repository.ResultRepo;
import com.capstone.assessmentportal.repository.SubCategoryRepo;
import com.capstone.assessmentportal.repository.UsersRepo;

class ResultServiceImplementationTest {
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
    @Mock
    FinalResultOfQuizRepo finalResultRepo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddTemporaryResultIfUserIdNotExists() {
        ResultsDto resultsDto = new ResultsDto();
        resultsDto.setResultId(1L);
        resultsDto.setCategoryId(10L);
        resultsDto.setStudentId(11L);
        resultsDto.setSubCategoryId(12L);
        resultsDto.setMarksObtained(10);
        resultsDto.setTotalMarks(10);
        resultsDto.setNumOfAttemptedQuestions(9);
        resultsDto.setTotalQuestions(10);
        resultsDto.setDateAndTime("23-10-23");
        
        when(usersRepo.findById(10L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> resultsService.addTemporaryResult(resultsDto));
    }
    
    @Test
    void testAddTemporaryResultIfSubCategoryIdNotExists() {
        ResultsDto resultsDto = new ResultsDto(1L,10L,11L,12L,10,9,9,9,"23-10-23");
        Users users = new Users();
        Category category = new Category();
        when(usersRepo.findById(10L)).thenReturn(Optional.of(users));
        when(categoryRepo.findById(12L)).thenReturn(Optional.of(category));
        when(subCategoryRepo.findById(11L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> resultsService.addTemporaryResult(resultsDto));
    }
    
    @Test
    void testAddTemporaryResultIfCategoryIdNotExists() {
        ResultsDto resultsDto = new ResultsDto(1L,10L,11L,12L,10,9,9,9,"23-10-23");
        Users users = new Users();
        SubCategory subCategory = new SubCategory();
        when(usersRepo.findById(10L)).thenReturn(Optional.of(users));
        when(subCategoryRepo.findById(11L)).thenReturn(Optional.of(subCategory));
        when(categoryRepo.findById(12L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> resultsService.addTemporaryResult(resultsDto));
    }
    
    @Test
    void testAddTemporaryResult() {
        ResultsDto resultsDto = new ResultsDto(1L,10L,11L,12L,10,9,9,9,"23-10-23");
        
        Users users = new Users();
        SubCategory subCategory = new SubCategory();
        Category category = new Category();
        when(usersRepo.findById(10L)).thenReturn(Optional.of(users));
        when(categoryRepo.findById(12L)).thenReturn(Optional.of(category));
        when(subCategoryRepo.findById(11L)).thenReturn(Optional.of(subCategory));
        subCategory.setCategory(category);
        
        Results res = new Results();
        res.setResultId(resultsDto.getResultId());
        res.setStudents(users);
        res.setSubCategory(subCategory);
        res.setTotalMarks(resultsDto.getTotalMarks());
        res.setMarksObtained(resultsDto.getMarksObtained());
        res.setNumOfAttemptedQuestions(resultsDto.getNumOfAttemptedQuestions());
        res.setTotalQuestions(resultsDto.getTotalQuestions());
        res.setDateAndTime(resultsDto.getDateAndTime());
        
        FinalResultsOfQuiz finalResults = new FinalResultsOfQuiz();
        finalResults.setFinalResultId(res.getResultId());
        finalResults.setStudentId(res.getStudents().getUserId());
        finalResults.setStudentName(users.getFirstName()+" "
                +users.getLastName());
        finalResults.setCategoryName(category.getCategoryName());
        finalResults.setQuizName(res.getSubCategory().getSubCategoryName());
        finalResults.setMarksObtained(res.getMarksObtained());
        finalResults.setTotalMarks(res.getTotalMarks());
        finalResults.setNumOfAttemptedQuestions(res
                 .getNumOfAttemptedQuestions());
        finalResults.setTotalQuestions(res.getTotalQuestions());
        finalResults.setDateAndTime(res.getDateAndTime());
        
        when(finalResultRepo.save(finalResults)).thenReturn(finalResults);
        ResultsDto resultsdto = resultsService.addTemporaryResult(resultsDto);
        assertNotNull(resultsdto);
        assertEquals(resultsDto, resultsdto);
    }
}