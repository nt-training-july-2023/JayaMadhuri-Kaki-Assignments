package com.capstone.assessmentPortal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Results {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long resultId;
  @ManyToOne
  @JoinColumn(name = "studentId")
  private Users students;
  @ManyToOne
  @JoinColumn(name = "subCategoryId")
  private SubCategory subCategory;
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
