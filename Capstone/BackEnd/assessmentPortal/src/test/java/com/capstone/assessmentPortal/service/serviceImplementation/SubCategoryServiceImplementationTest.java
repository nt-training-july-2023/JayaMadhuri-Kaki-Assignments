package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.model.SubCategory;
import com.capstone.assessmentPortal.repository.CategoryRepo;
import com.capstone.assessmentPortal.repository.SubCategoryRepo;

class SubCategoryServiceImplementationTest {
    @Autowired
    SubCategoryRepo subCategoryRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    SubCategoryServiceImplementation subCategoryServiceImpl;
    @BeforeEach
    void setUp() {
        subCategoryRepo = mock(SubCategoryRepo.class);
        categoryRepo = mock(CategoryRepo.class);
        subCategoryServiceImpl = new SubCategoryServiceImplementation(subCategoryRepo,categoryRepo);
    }
//    @Test
//    void testAddSubCategory() {
//        Long subCategoryId = 1L;
//        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
//        subCategoryDto.setSubCategoryName("Array");
//        subCategoryDto.setTimeLimitInMinutes("100");
//        subCategoryDto.setCategoryId(1L);
//        subCategoryDto.setSubCategoryId(subCategoryId);
//        
//        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
//        categoryDto.setCategoryId(1L);
//        categoryDto.setCategoryName("Java");
//        
//        Category category = new Category();
//        category.setCategoryId(categoryDto.getCategoryId());
//        category.setCategoryName(categoryDto.getCategoryName());
//        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.of(category));
//        SubCategory subCategory = new SubCategory();
//        subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
//        subCategory.setSubCategoryName(subCategory.getSubCategoryName());
//        subCategory.setTimeLimitInMinutes(subCategoryDto.getTimeLimitInMinutes());
//        subCategory.setCategory(category);
//        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
//        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));
//        SubCategoryDetailsDto subCategoryDetailsDto = subCategoryServiceImpl.addSubCategory(subCategoryDto);
//        assertEquals(subCategory.getSubCategoryName(),subCategoryDetailsDto.getSubCategoryName());
//    }

//    @Test
//    void testGetAllSubCategories() {
//        fail("Not yet implemented");
//    }

//    @Test
//    void testGetSubCategoryById() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testUpdateSubCategory() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testDeleteSubCategory() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetSubCategoryByCategoryId() {
//        fail("Not yet implemented");
//    }

}
