package com.capstone.assessmentPortal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Temporary Results entity class.
*/

@Data
@Entity
public class Results {
  /**
   *result id attribute autogenerated.
  */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long resultId;
  /**
   *users entity reference variable attribute.
  */
  @ManyToOne
  @JoinColumn(name = "studentId")
  private Users students;
  /**
   *subcategory entity reference variable attribute.
  */
  @ManyToOne
  @JoinColumn(name = "subCategoryId")
  private SubCategory subCategory;
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
   *number of questions attempted attribute.
  */
  @Column(nullable = false)
  private int numOfAttemptedQuestions;
  /**
   *total questions in quiz attribute.
  */
  @Column(nullable = false)
  private int totalQuestions;
  /**
   *date and time of quiz attempted attribute.
  */
  @Column(nullable = false)
  private String dateAndTime;
}
