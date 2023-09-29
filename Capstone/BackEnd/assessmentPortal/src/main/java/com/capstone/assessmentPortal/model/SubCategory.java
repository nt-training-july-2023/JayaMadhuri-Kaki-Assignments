package com.capstone.assessmentPortal.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Subcategory entity class.
 */
@Setter
@NoArgsConstructor
@Getter
@Entity
public class SubCategory {
    /**
     * subcategory id autogenerated attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subCategoryId;
    /**
     * subcategory name attribute.
     */
    private String subCategoryName;
    /**
     * subcategory description attribute.
     */
    private String subCategoryDescription;
    /**
     * subcategory time limit in minutes for quiz attribute.
     */
    private String timeLimitInMinutes;
    /**
     * category entity class reference variable attribute.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;
    /**
     * list of questions in a quiz attribute.
     */
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> question = new ArrayList<>();
    /**
     * list of results of a quiz.
     */
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Results> results = new ArrayList<>();
    /**
     * get category.
     * @return category
     */
    public final Category getCategory() {
        return new Category(category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription());
    }
    /**
     * set Category.
     * @param cate cate
     */
    public final void setCategory(final Category cate) {
        this.category = new Category(cate.getCategoryId(),
                cate.getCategoryName(),
                cate.getCategoryDescription());
    }
    /**
     * get question.
     * @return question
     */
    public final List<Question> getQuestion() {
        return new ArrayList<>(question);
    }
    /**
     * set question.
     * @param que que
     */
    public final void setQuestion(final List<Question> que) {
        this.question = new ArrayList<>(que);
    }
    /**
     * get results in the form of list.
     * @return results
     */
    public final List<Results> getResults() {
        return new ArrayList<>(results);
    }
    /**
     * set results.
     * @param res res
     */
    public final void setResults(final List<Results> res) {
        this.results = new ArrayList<>(res);
    }
    /**
     * parameter constructor for subcategory.
     * @param subCategoryid subCategoryid
     * @param subcategoryName subcategoryName
     * @param subcategoryDescription subcategoryDescription
     * @param timelimitInMinutes timelimitInMinutes
     */
    public SubCategory(final Long subCategoryid,
            final String subcategoryName,
            final String subcategoryDescription,
            final String timelimitInMinutes) {
        this.subCategoryId = subCategoryid;
        this.subCategoryName = subcategoryName;
        this.subCategoryDescription = subcategoryDescription;
        this.timeLimitInMinutes = timelimitInMinutes;
    }
}
