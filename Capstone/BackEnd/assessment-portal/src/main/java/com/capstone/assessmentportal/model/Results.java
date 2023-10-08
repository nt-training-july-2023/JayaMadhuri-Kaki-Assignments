package com.capstone.assessmentportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Temporary Results entity class.
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Results {
    /**
     * result id attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;
    /**
     *student id attribute.
    */
    private Long studentId;
    /**
     *student email attribute.
    */
    private String studentEmailId;
    /**
     *student name attribute.
    */
    private String studentName;
    /**
     *category name attribute.
    */
    private String categoryName;
    /**
     *quiz name attribute.
    */
    private String quizName;
    /**
     *marks obtained in quiz attribute.
    */
    private int marksObtained;
    /**
     *total marks of quiz attribute.
    */
    private int totalMarks;
    /**
     *number of attempted questions attribute.
    */
    private int numOfAttemptedQuestions;
    /**
     *total questions in quiz attribute.
    */
    private int totalQuestions;
    /**
     *date and time attribute.
    */
    private String dateAndTime;
}
