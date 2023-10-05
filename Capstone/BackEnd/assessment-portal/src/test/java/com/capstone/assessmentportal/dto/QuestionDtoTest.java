package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionDtoTest {
    @Test
    void testAllArgsConstructor() {
        QuestionDto questionDto = new QuestionDto(1L,"select correct options","A","B","C","D",Option.optionA,10L);
        assertEquals(questionDto.getQuestionId(),1L);
        assertEquals(questionDto.getQuestionContent(),"select correct options");
        assertEquals(questionDto.getOptionA(),"A");
        assertEquals(questionDto.getOptionB(),"B");
        assertEquals(questionDto.getOptionC(),"C");
        assertEquals(questionDto.getOptionD(),"D");
        assertEquals(questionDto.getCorrectAnswer(),Option.optionA);
        assertEquals(questionDto.getSubCategoryId(),10L);
    }
}