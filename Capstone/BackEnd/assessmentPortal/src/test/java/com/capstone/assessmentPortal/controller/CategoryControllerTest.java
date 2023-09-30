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

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.response.CustomResponse;
import com.capstone.assessmentPortal.service.serviceImplementation.CategoryServiceImplementation;

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
        ResponseEntity<CustomResponse<CategoryDetailsDto>> response = categoryController.addCategory(categoryDetailsDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(null,response.getBody().getResponseData());
        assertEquals("Category Successfully Added",response.getBody().getMessage());
    }

    @Test
    void testGetAllCategories() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        List<CategoryDetailsDto> listofCategories = new ArrayList<>();
        listofCategories.add(categoryDetailsDto);
        when(categoryService.getCategories()).thenReturn(listofCategories);
        ResponseEntity<CustomResponse<List<CategoryDetailsDto>>> response = categoryController.getCategories();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(listofCategories,response.getBody().getResponseData());
        assertEquals("Successfully Retrieved Categories",response.getBody().getMessage());
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto(1L,"Java","");
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDetailsDto);
        ResponseEntity<CustomResponse<CategoryDetailsDto>> response = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(categoryDetailsDto,response.getBody().getResponseData());
        assertEquals("Successfully Retrieved Category by Id",response.getBody().getMessage());
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        ResponseEntity<CustomResponse<CategoryDetailsDto>> response = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(null,response.getBody().getResponseData());
        assertEquals("Category Successfully Deleted",response.getBody().getMessage());
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDetailsDto existingcategoryDetailsDto = new
                CategoryDetailsDto(categoryId,"Java","Programming language");
        when(categoryService.updateCategory(categoryId, existingcategoryDetailsDto))
        .thenReturn(existingcategoryDetailsDto);
        ResponseEntity<CustomResponse<CategoryDetailsDto>> response = categoryController.updateCategory(categoryId,existingcategoryDetailsDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(null,response.getBody().getResponseData());
        assertEquals("Category Successfully Updated",response.getBody().getMessage());
    }

}
