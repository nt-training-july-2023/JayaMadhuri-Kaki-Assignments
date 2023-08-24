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
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long questionId;
  @Column(nullable = false)
  private String questionContent;
  @Column(nullable = false)
  private String optionA;
  @Column(nullable = false)
  private String optionB;
  @Column(nullable = false)
  private String optionC;
  @Column(nullable = false)
  private String optionD;
  @Column(nullable = false)
  private String correctAnswer;
  @ManyToOne
  @JoinColumn(name = "subCategoryId")
  private SubCategory subCategory;
}
