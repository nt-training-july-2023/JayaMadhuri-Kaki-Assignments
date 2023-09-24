package com.capstone.assessmentPortal.dto;

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
    @NotBlank(message = "Question is required")
    private String questionContent;
    /**
     * question option 1 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "OptionA is required")
    private String optionA;
    /**
     * question option 2 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "OptionB is required")
    private String optionB;
    /**
     * question option 3 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "OptionC is required")
    private String optionC;
    /**
     * question option 4 attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "OptionD is required")
    private String optionD;
    /**
     * question answer attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "CorrectAnswer is required")
    private String correctAnswer;
    /**
     * subCategory id attribute.
     */
    @Column(nullable = false)
    @NotNull(message = "Quiz Id is required")
    private Long subCategoryId;
}
