package com.capstone.assessmentportal.model;

import com.capstone.assessmentportal.dto.Option;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Questions of quiz entity class.
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Question {
    /**
     * question id attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    /**
     * question title attribute.
     */
    private String questionContent;
    /**
     * question option 1 attribute.
     */
    private String optionA;
    /**
     * question option 2 attribute.
     */
    private String optionB;
    /**
     * question option 3 attribute.
     */
    private String optionC;
    /**
     * question option 4 attribute.
     */
    private String optionD;
    /**
     * question answer attribute.
     */
    @Enumerated(EnumType.STRING)
    private Option correctAnswer;
    /**
     * foreign key attribute of subcategory.
     */
    @ManyToOne
    @JoinColumn(name = "subCategoryId")
    private SubCategory subCategory;
    /**
     * get subcategory.
     * @return subCategory
     */
    public SubCategory getSubCategory() {
        return new SubCategory(subCategory.getSubCategoryId(),
                subCategory.getSubCategoryName(),
                subCategory.getSubCategoryDescription(),
                subCategory.getTimeLimitInMinutes());
    }
    /**
     * set subCategory.
     * @param subcategory subcategory
     */
    public void setSubCategory(final SubCategory subcategory) {
        this.subCategory = new SubCategory(subcategory.getSubCategoryId(),
                subcategory.getSubCategoryName(),
                subcategory.getSubCategoryDescription(),
                subcategory.getTimeLimitInMinutes());
    }
    /**
     * parameter constructor for question.
     * @param questionid questionid
     * @param questioncontent questioncontent
     * @param optiona optiona
     * @param optionb optionb
     * @param optionc optionc
     * @param optiond optiond
     * @param correctAns correctAns
     */
    public Question(final Long questionid, final String questioncontent,
            final String optiona, final String optionb,
            final String optionc, final String optiond,
            final Option correctAns) {
        this.questionId = questionid;
        this.questionContent = questioncontent;
        this.optionA = optiona;
        this.optionB = optionb;
        this.optionC = optionc;
        this.optionD = optiond;
        this.correctAnswer = correctAns;
    }
}
