package com.capstone.assessmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultsDtoTest {
    @Test
    void testAllArgsConstructor() {
        ResultsDto resultsDto = new ResultsDto(1L,1L,"madhu@nucleusteq.com","Madhuri Kaki","Java","String",10,10,10,10,"2001-01-23 15:42:32");
        assertEquals(resultsDto.getResultId(),1L);
        assertEquals(resultsDto.getStudentId(),1L);
        assertEquals(resultsDto.getStudentEmailId(),"madhu@nucleusteq.com");
        assertEquals(resultsDto.getStudentName(),"Madhuri Kaki");
        assertEquals(resultsDto.getCategoryName(),"Java");
        assertEquals(resultsDto.getQuizName(),"String");
        assertEquals(resultsDto.getMarksObtained(),10);
        assertEquals(resultsDto.getTotalMarks(),10);
        assertEquals(resultsDto.getNumOfAttemptedQuestions(),10);
        assertEquals(resultsDto.getTotalQuestions(),10);
        assertEquals(resultsDto.getDateAndTime(),"2001-01-23 15:42:32");
    }
}
