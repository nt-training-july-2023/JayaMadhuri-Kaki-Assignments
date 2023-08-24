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
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class FinalResultsOfQuiz {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long finalResultId;
  @Column(nullable = false)
  private Long studentId;
  @Column(nullable = false)
  private String studentName;
  @Column(nullable = false)
  private String categoryName;
  @Column(nullable = false)
  private String quizName;
  @Column(nullable = false)
  private int marksObtained;
  @Column(nullable = false)
  private int totalMarks;
  @Column(nullable = false)
  private int numOfAttemptedQuestions;
  @Column(nullable = false)
  private int totalQuestions;
  @Column(nullable = false)
  private String dateAndTime;
}
