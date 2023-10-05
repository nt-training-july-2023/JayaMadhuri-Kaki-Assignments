package com.capstone.assessmentPortal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Temporary Results entity class.
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Results {
    /**
     * result id attribute autogenerated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;
    /**
     * users entity reference variable attribute.
     */
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Users students;
    /**
     * subcategory entity reference variable attribute.
     */
    @ManyToOne
    @JoinColumn(name = "subCategoryId")
    private SubCategory subCategory;
    /**
     * marks obtained in quiz attribute.
     */
    private int marksObtained;
    /**
     * total marks of quiz attribute.
     */
    private int totalMarks;
    /**
     * number of questions attempted attribute.
     */
    private int numOfAttemptedQuestions;
    /**
     * total questions in quiz attribute.
     */
    private int totalQuestions;
    /**
     * date and time of quiz attempted attribute.
     */
    private String dateAndTime;
    /**
     * get students.
     * @return students
     */
    public Users getStudents() {
        return new Users(students.getFirstName(), students.getUserId());
    }
    /**
     * set students.
     * @param stu stu
     */
    public void setStudents(final Users stu) {
        this.students = new Users(stu.getFirstName(), stu.getUserId());
    }
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
}
