package com.capstone.assessmentPortal.model;

import jakarta.persistence.Column;
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
  @Column(nullable = false)
  private Long studentId;
  /**
   *student name attribute.
  */
  @Column(nullable = false)
  private String studentName;
  /**
   *category name attribute.
  */
  @Column(nullable = false)
  private String categoryName;
  /**
   *quiz name attribute.
  */
  @Column(nullable = false)
  private String quizName;
  /**
   *marks obtained in quiz attribute.
  */
  @Column(nullable = false)
  private int marksObtained;
  /**
   *total marks of quiz attribute.
  */
  @Column(nullable = false)
  private int totalMarks;
  /**
   *number of attempted questions attribute.
  */
  @Column(nullable = false)
  private int numOfAttemptedQuestions;
  /**
   *total questions in quiz attribute.
  */
  @Column(nullable = false)
  private int totalQuestions;
  /**
   *date and time attribute.
  */
  @Column(nullable = false)
  private String dateAndTime;
}
