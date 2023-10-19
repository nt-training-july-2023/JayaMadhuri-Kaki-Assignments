package com.capstone.assessmentportal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
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
        CustomResponse<CategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Category Successfully Added");
        when(categoryService.addCategory(categoryDetailsDto)).thenReturn(categoryDetailsDto);
        CustomResponse<CategoryDetailsDto> response = categoryController.addCategory(categoryDetailsDto);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testGetAllCategories() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        List<CategoryDetailsDto> listofCategories = new ArrayList<>();
        listofCategories.add(categoryDetailsDto);
        CustomResponse<List<CategoryDetailsDto>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Retrieved Categories");
        expectedResponse.setResponseData(listofCategories);
        when(categoryService.getCategories()).thenReturn(listofCategories);
        CustomResponse<List<CategoryDetailsDto>> response = categoryController.getCategories();
        assertEquals(expectedResponse,response);
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto(1L,"Java","");
        CustomResponse<CategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Retrieved Category by Id");
        expectedResponse.setResponseData(categoryDetailsDto);
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDetailsDto);
        CustomResponse<CategoryDetailsDto> response = categoryController.getCategoryById(categoryId);
        assertEquals(expectedResponse,response);
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        CustomResponse<CategoryDetailsDto> response = categoryController.deleteCategory(categoryId);
        CustomResponse<CategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Category Successfully Deleted");
        assertEquals(expectedResponse,response);
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDetailsDto existingcategoryDetailsDto = new
                CategoryDetailsDto(categoryId,"Java","Programming language");
        CustomResponse<CategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Category Successfully Updated");
        when(categoryService.updateCategory(categoryId, existingcategoryDetailsDto))
        .thenReturn(existingcategoryDetailsDto);
        CustomResponse<CategoryDetailsDto> response = categoryController
                .updateCategory(categoryId,existingcategoryDetailsDto);
        assertEquals(expectedResponse,response);
    }

}