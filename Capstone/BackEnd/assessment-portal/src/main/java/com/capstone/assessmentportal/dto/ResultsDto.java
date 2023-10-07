package com.capstone.assessmentportal.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.capstone.assessmentportal.response.ValidationMessage;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * result id attribute autogenerated.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultsDto {
    /**
     * result id attribute autogenerated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;
    /**
     *student id attribute.
    */
    @Column(nullable = false)
    @NotNull(message = ValidationMessage.STUDENTID_NOTNULL)
    private Long studentId;
    /**
     *student email attribute.
    */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.EMAIL_NOTBLANK)
    private String studentEmailId;
    /**
     *student name attribute.
    */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.USERNAME_NOTBLANK)
    private String studentName;
    /**
     *category name attribute.
    */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.CATEGORYNAME_NOTBLANK)
    private String categoryName;
    /**
     *quiz name attribute.
    */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.QUIZNAME_NOTBLANK)
    private String quizName;
    /**
     *marks obtained in quiz attribute.
    */
    @Column(nullable = false)
    @NotNull(message = ValidationMessage.MARKSOBTAINED_NOTNULL)
    private int marksObtained;
    /**
     *total marks of quiz attribute.
    */
    @Column(nullable = false)
    @NotNull(message = ValidationMessage.TOTALMARKS_NOTNULL)
    private int totalMarks;
    /**
     *number of attempted questions attribute.
    */
    @Column(nullable = false)
    @NotNull(message = ValidationMessage.ATTEMPTEDQUESTIONS_NOTNULL)
    private int numOfAttemptedQuestions;
    /**
     *total questions in quiz attribute.
    */
    @Column(nullable = false)
    @NotNull(message = ValidationMessage.TOTALMARKS_NOTNULL)
    private int totalQuestions;
    /**
     *date and time attribute.
    */
    @Column(nullable = false)
    private String dateAndTime;
    /**
     * set date and time.
     * @return dateandtime
     */
    public final String setDateAndTimeMethod() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
}
