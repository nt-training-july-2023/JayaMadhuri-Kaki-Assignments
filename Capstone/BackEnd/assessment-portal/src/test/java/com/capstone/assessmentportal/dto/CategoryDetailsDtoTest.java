package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryDetailsDtoTest {
    @Test
    void testAllArgsConstructor() {
        CategoryDetailsDto categoryDto=new CategoryDetailsDto(1L,"java","java basics");
        assertEquals("java",categoryDto.getCategoryName());
        assertEquals("java basics",categoryDto.getCategoryDescription());
        assertEquals(1,categoryDto.getCategoryId());
    }
}
