package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SubCategoryDetailsDtoTest {
    @Test
    void testAllArgsConstructor() {
        SubCategoryDetailsDto subCategoryDetailsDto = new SubCategoryDetailsDto(1L,"Array","description","10",10L);
        assertEquals(subCategoryDetailsDto.getSubCategoryId(),1L);
        assertEquals(subCategoryDetailsDto.getSubCategoryName(),"Array");
        assertEquals(subCategoryDetailsDto.getSubCategoryDescription(),"description");
        assertEquals(subCategoryDetailsDto.getTimeLimitInMinutes(),"10");
        assertEquals(subCategoryDetailsDto.getCategoryId(),10L);
    }
}