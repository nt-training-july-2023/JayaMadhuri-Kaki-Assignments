package com.capstone.assessmentPortal.dto;

import com.capstone.assessmentPortal.response.ValidationMessage;

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
 * Questions dto class.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    /**
     * question id attribute autogenerated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    /**
     * question title attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.QUESTION_NOTBLANK)
    private String questionContent;
    /**
     * question option 1 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.OPTIONA_NOTBLANK)
    private String optionA;
    /**
     * question option 2 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.OPTIONB_NOTBLANK)
    private String optionB;
    /**
     * question option 3 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.OPTIONC_NOTBLANK)
    private String optionC;
    /**
     * question option 4 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.OPTIOND_NOTBLANK)
    private String optionD;
    /**
     * question answer attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = ValidationMessage.CORRECTANSWER_NOTBLANK)
    private String correctAnswer;
    /**
     * subCategory id attribute.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessage.QUIZID_NOTNULL)
    private Long subCategoryId;
}
