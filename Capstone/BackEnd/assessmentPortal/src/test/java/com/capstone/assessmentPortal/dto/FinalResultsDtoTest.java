package com.capstone.assessmentPortal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FinalResultsDtoTest {
    @Test
    void testAllArgsConstructor() {
        FinalResultsDto finalResultsDto = new FinalResultsDto(1L,1L,"madhu@nucleusteq.com","Madhuri Kaki","Java","String",10,10,10,10,"2001-01-23 15:42:32");
        assertEquals(finalResultsDto.getFinalResultId(),1L);
        assertEquals(finalResultsDto.getStudentId(),1L);
        assertEquals(finalResultsDto.getStudentEmailId(),"madhu@nucleusteq.com");
        assertEquals(finalResultsDto.getStudentName(),"Madhuri Kaki");
        assertEquals(finalResultsDto.getCategoryName(),"Java");
        assertEquals(finalResultsDto.getQuizName(),"String");
        assertEquals(finalResultsDto.getMarksObtained(),10);
        assertEquals(finalResultsDto.getTotalMarks(),10);
        assertEquals(finalResultsDto.getNumOfAttemptedQuestions(),10);
        assertEquals(finalResultsDto.getTotalQuestions(),10);
        assertEquals(finalResultsDto.getDateAndTime(),"2001-01-23 15:42:32");
    }
}
