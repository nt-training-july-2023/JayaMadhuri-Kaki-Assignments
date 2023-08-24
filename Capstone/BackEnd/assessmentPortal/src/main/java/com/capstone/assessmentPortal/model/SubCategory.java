package com.capstone.assessmentPortal.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
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
public class SubCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long subCategoryId;
  @Column(nullable = false)
  private String subCategoryName;
  @Column(nullable = true)
  private String subCategoryDescription;
  @Column
  @NotEmpty(message = "Time limit should not be empty")
  private String timeLimitInMinutes;
  @Column
  private boolean isAttempted = false;
  @ManyToOne
  @JoinColumn(name = "categoryId")
  private Category category;
  @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Question> question = new ArrayList<>();
  @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Results> results = new ArrayList<>();
}
