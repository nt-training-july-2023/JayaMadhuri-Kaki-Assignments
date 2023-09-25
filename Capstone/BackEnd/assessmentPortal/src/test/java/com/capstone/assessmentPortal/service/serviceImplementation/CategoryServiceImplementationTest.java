package com.capstone.assessmentPortal.service.serviceImplementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.assessmentPortal.dto.CategoryDetailsDto;
import com.capstone.assessmentPortal.exception.AlreadyExistsException;
import com.capstone.assessmentPortal.model.Category;
import com.capstone.assessmentPortal.repository.CategoryRepo;

import java.util.List;
import java.util.NoSuchElementException;
@SpringBootTest
class CategoryServiceImplementationTest {
    @Mock
    CategoryRepo categoryRepo;
    @InjectMocks
    CategoryServiceImplementation categoryServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddCategoryWithSameName() {
       CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
       categoryDetailsDto.setCategoryName("Java");
       Category category = new Category();
       category.setCategoryName(categoryDetailsDto.getCategoryName());
       when(categoryRepo.getCategoryByName(categoryDetailsDto.getCategoryName())).thenReturn(Optional.of(category));
       assertThrows(AlreadyExistsException.class, () -> categoryServiceImpl.addCategory(categoryDetailsDto));
    }
    
    @Test
    void testAddCategory() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        categoryDetailsDto.setCategoryId(1L);
        categoryDetailsDto.setCategoryName("Java");
        Category category = new Category();
        category.setCategoryId(categoryDetailsDto.getCategoryId());
        category.setCategoryName(categoryDetailsDto.getCategoryName());
        when(categoryRepo.getCategoryByName(category.getCategoryName())).thenReturn(Optional.empty());
        categoryRepo.save(category);
        when(categoryRepo.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        CategoryDetailsDto categoryDto = categoryServiceImpl.addCategory(categoryDetailsDto);
        assertEquals(categoryDto.getCategoryId(), category.getCategoryId());
    }

    @Test
    void testGetAllCategories() {
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        categoryDetailsDto.setCategoryId(1L);
        categoryDetailsDto.setCategoryName("Java");
        Category category = new Category();
        category.setCategoryId(categoryDetailsDto.getCategoryId());
        category.setCategoryName(categoryDetailsDto.getCategoryName());
        List<Category> listofcategories = new ArrayList<>();
        listofcategories.add(category);
        when(categoryRepo.getCategoryByName(category.getCategoryName())).thenReturn(Optional.empty());
        categoryRepo.save(category);
        when(categoryRepo.findAll()).thenReturn(listofcategories);
        List<CategoryDetailsDto> categoryList = categoryServiceImpl.getCategories();
        assertEquals("Java", categoryList.get(0).getCategoryName());
    }

    @Test
    void testGetCategoryByIdIfIdNotExists() {
        Long categoryId = 1L;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> categoryServiceImpl.getCategoryById(categoryId));
    }
    
    @Test
    void testGetCategoryByIdIfIdExists() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto(1L,"Java","");
        Category category = new Category(categoryDetailsDto.getCategoryId(),categoryDetailsDto.getCategoryName(),
                categoryDetailsDto.getCategoryDescription());
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        CategoryDetailsDto categoryDetails = categoryServiceImpl.getCategoryById(categoryId);
        assertEquals(category.getCategoryName(), categoryDetails.getCategoryName());
    }
    
    @Test
    void testDeleteIfIdNotExists() {
        Long categoryId = 1L;
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> categoryServiceImpl.deleteCategory(categoryId));
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        categoryDetailsDto.setCategoryId(categoryId);
        categoryDetailsDto.setCategoryName("Java");
        categoryDetailsDto.setCategoryDescription("programming language");
        Category category = new Category();
        category.setCategoryId(categoryDetailsDto.getCategoryId());
        category.setCategoryName(categoryDetailsDto.getCategoryName());
        category.setCategoryDescription(categoryDetailsDto.getCategoryDescription());
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        categoryServiceImpl.deleteCategory(categoryId);
        assertFalse(categoryRepo.existsById(category.getCategoryId()));
    }

    @Test
    void testUpdateCategoryIdNotExists() {
        Long categoryId = 1L;
        CategoryDetailsDto categoryDetailsDto = new CategoryDetailsDto();
        categoryDetailsDto.setCategoryId(categoryId);
        categoryDetailsDto.setCategoryName("Java");
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> categoryServiceImpl.updateCategory(categoryId,categoryDetailsDto));
    }
    
    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryDetailsDto existingcategoryDetailsDto = new CategoryDetailsDto(categoryId,"Java","Programming language");
        Category category = new Category(existingcategoryDetailsDto.getCategoryId(),existingcategoryDetailsDto.getCategoryName(),
                existingcategoryDetailsDto.getCategoryDescription());
        CategoryDetailsDto updatedcategoryDetailsDto = new CategoryDetailsDto(categoryId,"Spring","Programming language");
        Category updatedCategory = new Category(updatedcategoryDetailsDto.getCategoryId(),updatedcategoryDetailsDto.getCategoryName(),
                updatedcategoryDetailsDto.getCategoryDescription());
        when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepo.save(category)).thenReturn(updatedCategory);
        CategoryDetailsDto categoryDto = categoryServiceImpl.updateCategory(categoryId, updatedcategoryDetailsDto);
        assertNotNull(categoryDto);
        assertEquals(updatedCategory.getCategoryName(),categoryDto.getCategoryName());
        assertEquals(updatedCategory.getCategoryDescription(),categoryDto.getCategoryDescription());
    }
}
