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
 * Final results of quiz entity class.
*/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FinalResultsOfQuiz {
  /**
   *final result id attribute autogenerated.
  */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long finalResultId;
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