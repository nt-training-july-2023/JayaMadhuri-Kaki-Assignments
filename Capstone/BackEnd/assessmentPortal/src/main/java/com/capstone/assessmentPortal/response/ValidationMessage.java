package com.capstone.assessmentPortal.response;

/**
 *validation message class.
*/
public final class ValidationMessage {
    /**
     *default constructor.
    */
    private ValidationMessage() {
    }
    /**
     * message when email blank.
    */
    public static final String EMAIL_NOTBLANK = "Email Id is required";
    /**
     *message when email pattern wrong.
    */
    public static final String EMAIL_PATTERN = "Not a valid email";
    /**
     *message when password blank.
    */
    public static final String PASSWORD_NOTBLANK = "Password is required";
    /**
     *message when password pattern wrong.
    */
    public static final String PASSWORD_PATTERN = "Password must be at least 8 "
                 + "characters long and "
                 + "contain at least one digit, one lowercase letter,"
                 + " one uppercase letter, "
                 + "one special character, and no whitespace";
    /**
     *when category id null.
    */
    public static final String CATEGORY_NOTNULL = "Category Id is required";
    /**
     *category name is blank.
    */
    public static final String CATEGORY_NOTBLANK = "Category name is required";
    /**
     *quiz id is null.
    */
    public static final String QUIZID_NOTNULL = "Quiz Id is required";
    /**
     *quiz name is blank.
    */
    public static final String QUIZNAME_NOTBLANK = "Quiz Name is required";
    /**
     *time limit is blank.
    */
    public static final String TIMELIMIT_NOTBLANK = "Time Limit is required";
    /**
     *question is blank.
    */
    public static final String QUESTION_NOTBLANK = "Question is required";
    /**
     *optionA is blank.
    */
    public static final String OPTIONA_NOTBLANK = "OptionA is required";
    /**
     *optionB is blank.
    */
    public static final String OPTIONB_NOTBLANK = "OptionB is required";
    /**
     *optionC is blank.
    */
    public static final String OPTIONC_NOTBLANK = "OptionC is required";
    /**
     *optionD is blank.
    */
    public static final String OPTIOND_NOTBLANK = "OptionD is required";
    /**
     *correct answer is blank.
    */
    public static final String CORRECTANSWER_NOTBLANK = "Correct "
            + "Answer is required";
    /**
     *student id is null.
    */
    public static final String STUDENTID_NOTNULL = "Student Id is required";
    /**
     *marks obtained is null.
    */
    public static final String MARKSOBTAINED_NOTNULL = "Marks Obtained "
            + "is required";
    /**
     *total questions is null.
    */
    public static final String TOTALQUESTIONS_NOTNULL = "Total Questions "
            + "is required";
    /**
     *total marks is null.
    */
    public static final String TOTALMARKS_NOTNULL = "Total Marks is required";
    /**
     *attempted questions is null.
    */
    public static final String ATTEMPTEDQUESTIONS_NOTNULL = "Attempted "
            + "Questions is required";
    /**
     *first name is blank.
    */
    public static final String FIRSTNAME_NOTBLANK = "First Name is required";
    /**
     *last name is blank.
    */
    public static final String LASTNAME_NOTBLANK = "Last Name is required";
    /**
     *date of birth is blank.
    */
    public static final String DOB_NOTBLANK = "Date of Birth is required";
    /**
     *date of birth pattern wrong.
    */
    public static final String DOB_PATTERN = "Date of birth should be in "
            + "yyyy-mm-dd format";
    /**
     *age below 18 years.
    */
    public static final String AGE_VALIDATION = "Age should be above"
            + " 18 years old";
    /**
     *gender is blank.
    */
    public static final String GENDER_NOTBLANK = "Gender is required";
    /**
     *exception when category name already exists.
    */
    public static final String CATEGORY_ALREADYEXISTS = "Category with"
            + " same name already exists";
    /**
     *exception when category id not exists.
    */
    public static final String CATEGORY_NOSUCHELEMENT = "Cannot find "
            + "category with id";
    /**
     *exception when quiz id not exists.
    */
    public static final String QUIZ_NOSUCHELEMENT = "Cannot find quiz with id";
    /**
     *exception when quiz name already exists.
    */
    public static final String QUIZ_ALREADYEXISTS = "Quiz with same name "
            + "already exists";
    /**
     *exception when options are same.
    */
    public static final String QUESTION_ALREADYEXISTS = "Options must be"
            + " different from each other";
    /**
     *exception when question id not exists.
    */
    public static final String QUESTION_NOSUCHELEMENT = "Cannot find "
            + "question with id";
    /**
     *exception when user id not exists.
    */
    public static final String USER_NOSUCHELEMENT = "Cannot find user with id";
    /**
     *exception when user email not exists.
    */
    public static final String USER_NOSUCHELEMENTEMAIL = "Cannot "
            + "find user with email id";
    /**
     *exception when user email already contains account.
    */
    public static final String USER_EMAILALREADYEXISTS = "An Account with same"
                + " email already exists";
    /**
     *exception when incorrect credentials.
    */
    public static final String USER_NOTFOUND = "Incorrect Credentials";
}
