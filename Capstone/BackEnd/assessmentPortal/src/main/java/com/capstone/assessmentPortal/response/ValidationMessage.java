package com.capstone.assessmentPortal.response;

public class ValidationMessage {
    public static final String emailNotblank = "Email Id is required";
    public static final String emailPattern = "Not a valid email";
    public static final String passwordNotblank = "Password is required";
    public static final String passwordPattern="Password must be at least 8 characters long and "
                 + "contain at least one digit, one lowercase letter,"
                 + " one uppercase letter, "
                 + "one special character, and no whitespace";
    public static final String categoryIdNotnull = "Category Id is required";
    public static final String categoryNameNotblank = "Category name is required";
    public static final String quizIdNotnull = "Quiz Id is required";
    public static final String quizNameNotblank = "Quiz Name is required";
    public static final String timeLimitNotblank = "Time Limit is required";
    public static final String questionNotblank = "Question is required";
    public static final String optionANotblank = "OptionA is required";
    public static final String optionBNotblank = "OptionB is required";
    public static final String optionCNotblank = "OptionC is required";
    public static final String optionDNotblank = "OptionD is required";
    public static final String correctAnswerNotblank = "Correct Answer is required";
    public static final String studentIdNotnull = "Student Id is required";
    public static final String marksObtainedNotnull = "Marks Obtained is required";
    public static final String totalQuestionsNotnull = "Total Questions is required";
    public static final String totalMarksNotnull = "Total Marks is required";
    public static final String attemptedQuestionsNotnull = "Attempted Questions is required";
    public static final String firstNameNotblank = "First Name is required";
    public static final String lastNameNotblank = "Last Name is required";
    public static final String dobNotblank = "Date of Birth is required";
    public static final String dobPattern = "Date of birth should be in yyyy-mm-dd format";
    public static final String ageValidation = "Age should be above 18 years old";
    public static final String genderNotblank = "Gender is required";
}
