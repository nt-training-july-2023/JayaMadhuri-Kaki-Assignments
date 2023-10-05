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
import com.capstone.assessmentportal.dto.CategoryDetailsDto;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.service.serviceimplementation.CategoryServiceImplementation;

@SpringBootTest
class CategoryControllerTest {
    @Mock
    CategoryServiceImplementation categoryService;
    @InjectMocks
    CategoryController categoryController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddCategory() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        categoryDetailsDto.setCategoryId(1L);
        categoryDetailsDto.setCategoryName("Java");
        
        when(categoryService.addCategory(categoryDetailsDto)).thenReturn(categoryDetailsDto);
        CustomResponse<CategoryDetailsDto> response = categoryController.addCategory(categoryDetailsDto);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("Category Successfully Added",response.getMessage());
    }

    @Test
    void testGetAllCategories() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        List<CategoryDetailsDto> listofCategories = new ArrayList<>();
        listofCategories.add(categoryDetailsDto);
        when(categoryService.getCategories()).thenReturn(listofCategories);
        CustomResponse<List<CategoryDetailsDto>> response = categoryController.getCategories();
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(listofCategories,response.getResponseData());
        assertEquals("Successfully Retrieved Categories",response.getMessage());
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto(1L,"Java","");
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDetailsDto);
        CustomResponse<CategoryDetailsDto> response = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(categoryDetailsDto,response.getResponseData());
        assertEquals("Successfully Retrieved Category by Id",response.getMessage());
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        CustomResponse<CategoryDetailsDto> response = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("Category Successfully Deleted",response.getMessage());
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDetailsDto existingcategoryDetailsDto = new
                CategoryDetailsDto(categoryId,"Java","Programming language");
        when(categoryService.updateCategory(categoryId, existingcategoryDetailsDto))
        .thenReturn(existingcategoryDetailsDto);
        CustomResponse<CategoryDetailsDto> response = categoryController.updateCategory(categoryId,existingcategoryDetailsDto);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
        assertEquals(null,response.getResponseData());
        assertEquals("Category Successfully Updated",response.getMessage());
    }

}