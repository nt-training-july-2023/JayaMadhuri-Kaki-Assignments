package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capstone.assessmentPortal.dto.ResultsDto;
import com.capstone.assessmentPortal.exception.InputEmptyException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.FinalResultsOfQuiz;
import com.capstone.assessmentPortal.model.Results;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.model.Users;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.FinalResultOfQuizRepo;
import com.capstone.assessmentPortal.repository.ResultRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;
import com.capstone.assessmentPortal.repository.UsersRepo;

class ResultServiceImplementationTest {
    @Autowired
    ResultServiceImplementation resultsService;
    @Autowired
    ResultRepo resultRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    SubCategoryRepo subCategoryRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    FinalResultOfQuizRepo finalResultRepo;
    @BeforeEach
    void setUp() {
        subCategoryRepo = mock(SubCategoryRepo.class);
        usersRepo = mock(UsersRepo.class);
        resultRepo = mock(ResultRepo.class);
        categoryRepo = mock(CategoryRepo.class);
        finalResultRepo = mock(FinalResultOfQuizRepo.class);
        resultsService = new ResultServiceImplementation(subCategoryRepo,
                usersRepo,resultRepo,categoryRepo,finalResultRepo);
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

    @Test
    void testFindResultsByUserAndSubCategoryIfEmptyFieldUserID() {
        Long userId = null;
        Long subCategoryId = 1L;
        assertThrows(InputEmptyException.class, () -> resultsService.findResultsByUserAndSubCategory(userId,subCategoryId));
    }
    @Test
    void testFindResultsByUserAndSubCategoryIfEmptyField() {
        Long userId = 1L;
        Long subCategoryId = null;
        assertThrows(InputEmptyException.class, () -> resultsService.findResultsByUserAndSubCategory(userId,subCategoryId));
    }
    
    @Test
    void testFindResultsByUserAndSubCategoryIfFalse() {
        Long userId = 1L;
        Long subCategoryId = 2L;
        when(resultRepo.findResultsByStudentsAndSubCategory(userId, subCategoryId)).thenReturn(null);
        boolean result = resultsService.findResultsByUserAndSubCategory(userId, subCategoryId);
        assertFalse(result);
    }
    
    @Test
    void testFindResultsByUserAndSubCategoryIfTrue() {
        Long userId = 1L;
        Long subCategoryId = 2L;
        Results results = new Results();
        when(resultRepo.findResultsByStudentsAndSubCategory(userId, subCategoryId)).thenReturn(results);
        boolean result = resultsService.findResultsByUserAndSubCategory(userId, subCategoryId);
        assertTrue(result);
    }
}
