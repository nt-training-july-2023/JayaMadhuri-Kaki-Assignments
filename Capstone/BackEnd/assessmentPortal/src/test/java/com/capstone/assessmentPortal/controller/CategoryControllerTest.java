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
        ResponseEntity<Object> response = categoryController.addCategory(categoryDetailsDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testGetAllCategories() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        List<CategoryDetailsDto> listofCategories = new ArrayList<>();
        listofCategories.add(categoryDetailsDto);
        when(categoryService.getAllCategories()).thenReturn(listofCategories);
        ResponseEntity<Object> response = categoryController.getAllCategories();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto(1L,"Java","");
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDetailsDto);
        ResponseEntity<Object> response = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        ResponseEntity<Object> response = categoryController.deleteCategory(categoryId);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDetailsDto existingcategoryDetailsDto = new
                CategoryDetailsDto(categoryId,"Java","Programming language");
        when(categoryService.updateCategory(categoryId, existingcategoryDetailsDto))
        .thenReturn(existingcategoryDetailsDto);
        ResponseEntity<Object> response = categoryController.updateCategory(categoryId,existingcategoryDetailsDto);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

}