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
import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.response.CustomResponse;
import com.capstone.assessmentPortal.service.serviceImplementation.SubCategoryServiceImplementation;

@SpringBootTest
class SubCategoryControllerTest {
    @Mock
    CategoryRepo categoryRepo;
    @Mock
    SubCategoryServiceImplementation subCategoryService;
    @InjectMocks
    SubCategoryController subcategoryController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllSubCategories() {
        List<SubCategoryDetailsDto> subCategorylist = new ArrayList<>();
        when(subCategoryService.getSubCategories()).thenReturn(subCategorylist);
        ResponseEntity<CustomResponse<List<SubCategoryDetailsDto>>> response = subcategoryController.getSubCategories();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testGetSubCategoryById() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(1L);
        
        when(subCategoryService.getSubCategoryById(subCategoryId)).thenReturn(subCategoryDto);
        ResponseEntity<CustomResponse<SubCategoryDetailsDto>> response = subcategoryController.getSubCategoryById(subCategoryId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testGetSubCategoryByCategoryId() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        List<SubCategoryDetailsDto> list = new ArrayList<>();
        when(subCategoryService.getSubCategoryByCategoryId(subCategoryDto.getCategoryId()))
        .thenReturn(list);
        ResponseEntity<CustomResponse<List<SubCategoryDetailsDto>>> response = subcategoryController
                .getSubCategoryByCategoryId(subCategoryDto.getCategoryId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testAddSubCategory() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryDescription("Topic in Java");
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        when(subCategoryService.addSubCategory(subCategoryDto)).thenReturn(subCategoryDto);
        ResponseEntity<CustomResponse<SubCategoryDetailsDto>> response = subcategoryController.addSubCategory(subCategoryDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testUpdateSubCategory() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto1 = new SubCategoryDetailsDto();
        subCategoryDto1.setSubCategoryId(subCategoryId);
        subCategoryDto1.setSubCategoryName("Java");
        subCategoryDto1.setSubCategoryDescription("programming language");
        subCategoryDto1.setTimeLimitInMinutes("100");
        
        when(subCategoryService.updateSubCategory(subCategoryDto1, subCategoryId)).thenReturn(subCategoryDto1);
        ResponseEntity<CustomResponse<SubCategoryDetailsDto>> response = subcategoryController.updateSubCategory(subCategoryId,subCategoryDto1);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testDeleteSubCategory() {
        Long subCategoryId = 1L;
        ResponseEntity<CustomResponse<SubCategoryDetailsDto>> response = subcategoryController.deleteSubCategory(subCategoryId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}
