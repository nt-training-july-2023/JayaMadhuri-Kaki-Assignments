package com.capstone.assessmentportal.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.capstone.assessmentportal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.response.CustomResponse;
import com.capstone.assessmentportal.service.serviceimplementation.SubCategoryServiceImplementation;

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
        CustomResponse<List<SubCategoryDetailsDto>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Retrieved Quizes");
        expectedResponse.setResponseData(subCategorylist);
        when(subCategoryService.getSubCategories()).thenReturn(subCategorylist);
        CustomResponse<List<SubCategoryDetailsDto>> response = subcategoryController.getSubCategories();
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(response);
    }

    @Test
    void testGetSubCategoryById() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(1L);
        
        CustomResponse<SubCategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Retrieved Quizes by Id");
        expectedResponse.setResponseData(subCategoryDto);
        
        when(subCategoryService.getSubCategoryById(subCategoryId)).thenReturn(subCategoryDto);
        CustomResponse<SubCategoryDetailsDto> response = subcategoryController.getSubCategoryById(subCategoryId);
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(response);
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
        CustomResponse<List<SubCategoryDetailsDto>> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Successfully Retrieved Quizes by Category Id");
        expectedResponse.setResponseData(list);
        
        when(subCategoryService.getSubCategoryByCategoryId(subCategoryDto.getCategoryId()))
        .thenReturn(list);
        CustomResponse<List<SubCategoryDetailsDto>> response = subcategoryController
                .getSubCategoryByCategoryId(subCategoryDto.getCategoryId());
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(response);
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
        
        CustomResponse<SubCategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Quiz Successfully Added");
        
        when(subCategoryService.addSubCategory(subCategoryDto)).thenReturn(subCategoryDto);
        CustomResponse<SubCategoryDetailsDto> response = subcategoryController.addSubCategory(subCategoryDto);
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(response);
    }

    @Test
    void testUpdateSubCategory() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto1 = new SubCategoryDetailsDto();
        subCategoryDto1.setSubCategoryId(subCategoryId);
        subCategoryDto1.setSubCategoryName("Java");
        subCategoryDto1.setSubCategoryDescription("programming language");
        subCategoryDto1.setTimeLimitInMinutes("100");
        
        CustomResponse<SubCategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Quiz Successfully Updated");
        
        when(subCategoryService.updateSubCategory(subCategoryDto1, subCategoryId)).thenReturn(subCategoryDto1);
        CustomResponse<SubCategoryDetailsDto> response = subcategoryController.updateSubCategory(subCategoryId,subCategoryDto1);
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(response);
    }

    @Test
    void testDeleteSubCategory() {
        Long subCategoryId = 1L;
        CustomResponse<SubCategoryDetailsDto> expectedResponse = new CustomResponse<>();
        expectedResponse.setStatusCode(200);
        expectedResponse.setMessage("Quiz Successfully Deleted");
        
        CustomResponse<SubCategoryDetailsDto> response = subcategoryController.deleteSubCategory(subCategoryId);
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(response);
    }

}