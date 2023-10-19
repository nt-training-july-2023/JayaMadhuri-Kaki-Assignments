package com.capstone.assessmentportal.service.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capstone.assessmentportal.dto.CategoryDetailsDto;
import com.capstone.assessmentportal.dto.SubCategoryDetailsDto;
import com.capstone.assessmentportal.exception.AlreadyExistsException;
import com.capstone.assessmentportal.model.Category;
import com.capstone.assessmentportal.model.Question;
import com.capstone.assessmentportal.model.SubCategory;
import com.capstone.assessmentportal.repository.CategoryRepo;
import com.capstone.assessmentportal.repository.SubCategoryRepo;

import java.util.List;
import java.util.NoSuchElementException;

class SubCategoryServiceImplementationTest {
    @Mock
    SubCategoryRepo subCategoryRepo;
    @Mock
    CategoryRepo categoryRepo;
    @InjectMocks
    SubCategoryServiceImplementation subCategoryServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        
        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
        categoryDto.setCategoryId(10L);  
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("programming");
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getTimeLimitInMinutes(),subCategoryDto.getSubCategoryDescription());
        
        List<SubCategory> listOfSubCategories = new ArrayList<>();
        category.setSubCategory(listOfSubCategories);
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.of(category));
        subCategory.setCategory(category);
        subCategoryRepo.save(subCategory);
        when(subCategoryRepo.findById(subCategoryId)).thenReturn(Optional.of(subCategory));
        SubCategoryDetailsDto subCategoryDetailsDto = subCategoryServiceImpl.addSubCategory(subCategoryDto);
        assertEquals(subCategoryDto,subCategoryDetailsDto);
    }
    
    @Test
    void testAddIfSameNameExists() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Java");
        subCategoryDto.setSubCategoryId(subCategoryId);
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryDescription("Topic in Java");
        subCategoryDto.setTimeLimitInMinutes("100");
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getTimeLimitInMinutes(),subCategoryDto.getSubCategoryDescription());
        
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.of(subCategory));
        assertThrows(AlreadyExistsException.class, () -> subCategoryServiceImpl.addSubCategory(subCategoryDto));
    }
    
    @Test
    void testAddIfCategoryIdInvalid() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
        categoryDto.setCategoryId(10L);  
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("programming");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getTimeLimitInMinutes(),subCategoryDto.getSubCategoryDescription());;
        
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> subCategoryServiceImpl.addSubCategory(subCategoryDto));
    }

    @Test
    void testGetAllSubCategories() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setSubCategoryDescription("Basics");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
        categoryDto.setCategoryId(10L);  
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("programming");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getSubCategoryDescription(), subCategoryDto.getTimeLimitInMinutes());
 
        List<Question> listOfQuestions = new ArrayList<>();
        subCategory.setQuestion(listOfQuestions);
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.of(category));
        subCategory.setCategory(category);
        subCategoryRepo.save(subCategory);
        List<SubCategory> listOfSubCategories = new ArrayList<>();
        listOfSubCategories.add(subCategory);
        when(subCategoryRepo.findAll()).thenReturn(listOfSubCategories);
        List<SubCategoryDetailsDto> subCategoryList = subCategoryServiceImpl.getSubCategories();
        assertEquals(Collections.singletonList(subCategoryDto),subCategoryList);
    }

    @Test
    void testGetSubCategoryByIdIfExists() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
        categoryDto.setCategoryId(10L);  
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("programming");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getSubCategoryDescription(), subCategoryDto.getTimeLimitInMinutes());
        
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.of(category));
        subCategory.setCategory(category);
        subCategoryRepo.save(subCategory);
        when(subCategoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        SubCategoryDetailsDto subCategoryDetailsDto = subCategoryServiceImpl.getSubCategoryById(subCategoryId);
        assertEquals(subCategoryDto,subCategoryDetailsDto);
    }
    
    @Test
    void testGetSubCategoryByIdIfNotExists(){
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(subCategoryId);
        when(categoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> subCategoryServiceImpl.getSubCategoryById(subCategoryDto.getSubCategoryId()));
    }
    
    @Test
    void testUpdateSubCategoryIfIdNotExits() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(subCategoryId);
        when(subCategoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> subCategoryServiceImpl.updateSubCategory(subCategoryDto,subCategoryDto.getSubCategoryId()));
    }
    
    @Test
    void testUpdateCategoryIfIdNotExits() {
        Long CategoryId = 1L;
        Long subCategoryId = 1L;
        SubCategory subCategory = new SubCategory();
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        
        subCategoryDto.setCategoryId(CategoryId);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        when(subCategoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> subCategoryServiceImpl.updateSubCategory(subCategoryDto,subCategoryDto.getCategoryId()));
    }
    
    @Test
    void testUpdateSubCategory() {
        Long subCategoryId = 1L;
        Long categoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(subCategoryId);
        subCategoryDto.setSubCategoryName("Java");
        subCategoryDto.setSubCategoryDescription("programming language");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(categoryId);
        
        SubCategory subCategory = new SubCategory(subCategoryId,subCategoryDto.getSubCategoryName(),
                subCategoryDto.getSubCategoryDescription(),subCategoryDto.getTimeLimitInMinutes());
        subCategory.getQuestion();
        
        SubCategoryDetailsDto subCategoryDto1 = new SubCategoryDetailsDto();
        subCategoryDto1.setSubCategoryId(subCategoryId);
        subCategoryDto1.setSubCategoryName("Java");
        subCategoryDto1.setSubCategoryDescription("programming language");
        subCategoryDto1.setTimeLimitInMinutes("100");
        
        Category category = new Category();
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        subCategoryDto1.setCategoryId(categoryId);
        SubCategory subCategory1 = new SubCategory(subCategoryId,subCategoryDto1.getSubCategoryName(),
                subCategoryDto1.getSubCategoryDescription(),subCategoryDto1.getTimeLimitInMinutes());
        when(subCategoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        when(subCategoryRepo.save(subCategory)).thenReturn(subCategory1);
        SubCategoryDetailsDto subCategoryDetailsDto = subCategoryServiceImpl.updateSubCategory(subCategoryDto1, subCategoryId);
        assertEquals(subCategoryDetailsDto,subCategoryDto);
    }
    
    @Test
    void testUpdateSubCategoryIfQuizNameAlreadyExists() {
        Long subCategoryId = 1L;
        Long categoryId = 1L;
        String subCategoryName = "Java";
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(subCategoryId);
        subCategoryDto.setSubCategoryName(subCategoryName);
        subCategoryDto.setSubCategoryDescription("programming language");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(categoryId);
        
        SubCategory existingSubCategory = new SubCategory(2L, "Javaa", "Description", "200");
        
        SubCategoryDetailsDto subCategoryDto1 = new SubCategoryDetailsDto();
        subCategoryDto1.setSubCategoryId(subCategoryId);
        
        Category category = new Category();
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        subCategoryDto1.setCategoryId(categoryId);
        when(subCategoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.of(existingSubCategory));
        when(subCategoryRepo.getSubCategoryByName(subCategoryName)).thenReturn(Optional.of(existingSubCategory));
        assertTrue(!subCategoryDto.getSubCategoryName().equals(subCategoryDto1.getSubCategoryName()));
        assertThrows(AlreadyExistsException.class, () -> subCategoryServiceImpl.updateSubCategory(subCategoryDto, subCategoryId));
    }
    
    @Test
    void testDeleteSubCategoryIfIdNotExists() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(subCategoryId);
        when(categoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> subCategoryServiceImpl.deleteSubCategory(subCategoryDto.getSubCategoryId()));
    }
   
    @Test
    void testDeleteSubCategoryIfIdExists() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryId(subCategoryId);
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryId);
        
        when(subCategoryRepo.findById(subCategoryDto.getSubCategoryId())).thenReturn(Optional.of(subCategory));
        subCategoryServiceImpl.deleteSubCategory(subCategoryId);
        assertFalse(subCategoryRepo.existsById(subCategoryId));
    }
    
    @Test
    void testGetSubCategoryByCategoryIdIfNotExists() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
        categoryDto.setCategoryId(10L);  
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("programming");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getTimeLimitInMinutes(),subCategoryDto.getSubCategoryDescription());
        
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> subCategoryServiceImpl.getSubCategoryByCategoryId(subCategoryDto.getCategoryId()));
    }
    
    @Test
    void testGetSubCategoryByCategoryId() {
        Long subCategoryId = 1L;
        SubCategoryDetailsDto subCategoryDto = new SubCategoryDetailsDto();
        subCategoryDto.setSubCategoryName("Array");
        subCategoryDto.setTimeLimitInMinutes("100");
        subCategoryDto.setCategoryId(10L);
        subCategoryDto.setSubCategoryId(subCategoryId);
        
        CategoryDetailsDto categoryDto = new CategoryDetailsDto();
        categoryDto.setCategoryId(10L);  
        categoryDto.setCategoryName("java");
        categoryDto.setCategoryDescription("programming");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());
        
        SubCategory subCategory = new SubCategory(subCategoryDto.getSubCategoryId(),subCategoryDto.getSubCategoryName(),
                subCategoryDto.getSubCategoryDescription(), subCategoryDto.getTimeLimitInMinutes());
        
        when(subCategoryRepo.getSubCategoryByName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepo.findById(subCategoryDto.getCategoryId())).thenReturn(Optional.of(category));
        subCategory.setCategory(category);
        List<SubCategory> listOfSubcategories = new ArrayList<>();
        listOfSubcategories.add(subCategory);
        subCategoryRepo.save(subCategory);
        when(subCategoryRepo.getSubCategoryByCategoryId(subCategoryDto.getCategoryId())).thenReturn(listOfSubcategories);
        List<SubCategoryDetailsDto> listOfsubCategoriesDto = subCategoryServiceImpl.getSubCategoryByCategoryId(10L);
        assertEquals(Collections.singletonList(subCategoryDto),listOfsubCategoriesDto);
    }
}