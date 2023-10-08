package com.capstone.assessmentportal.response;

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
    public static final String CATEGORYNAME_NOTBLANK =
            "Category name is required";
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
    public static final String AGE_VALIDATION = "Age should be atleast"
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
    /**
     *controller message when categories retrieved successfully.
    */
    public static final String CATEGORIES_RETRIEVED = "Successfully"
            + " Retrieved Categories";
    /**
     *controller message when category retrieved successfully.
    */
    public static final String CATEGORIES_RETRIEVED_BY_ID = "Successfully"
            + " Retrieved Category by Id";
    /**
     *controller message when category added successfully.
    */
    public static final String CATEGORIES_ADDED = "Category Successfully"
            + " Added";
    /**
     *controller message when category updated successfully.
    */
    public static final String CATEGORIES_UPDATED = "Category Successfully"
            + " Updated";
    /**
     *controller message when category deleted successfully.
    */
    public static final String CATEGORIES_DELETED = "Category Successfully "
            + "Deleted";
    /**
     *controller message when Results retrieved successfully.
    */
    public static final String RESULTS_RETRIEVED = "Results Successfully"
            + " Retrieved";
    /**
     *controller message when Results added successfully.
    */
    public static final String RESULTS_ADDED = "Results Successfully"
            + " Added";
    /**
     *controller message when questions retrieved successfully.
    */
    public static final String QUESTIONS_RETRIEVED = "Successfully Retrieved"
            + " Questions By Quiz Id";
    /**
     *controller message when Question added successfully.
    */
    public static final String QUESTIONS_ADDED = "Question Successfully"
            + " Added";
    /**
     *controller message when Question updated successfully.
    */
    public static final String QUESTIONS_UPDATED = "Question Successfully"
            + " Updated";
    /**
     *controller message when   deleted successfully.
    */
    public static final String QUESTIONS_DELETED = "Question Successfully "
            + "Deleted";
    /**
     *controller message when quiz retrieved successfully.
    */
    public static final String QUIZ_RETRIEVED = "Successfully"
            + " Retrieved Quizes";
    /**
     *controller message when quiz retrieved successfully by id.
    */
    public static final String QUIZ_RETRIEVED_BY_ID = "Successfully"
            + " Retrieved Quizes by Id";
    /**
     *controller message when quiz retrieved successfully by category id.
    */
    public static final String QUIZ_RETRIEVED_BY_CATEGORY_ID = "Successfully"
            + " Retrieved Quizes by Category Id";
    /**
     *controller message when quiz added successfully.
    */
    public static final String QUIZ_ADDED = "Quiz Successfully"
            + " Added";
    /**
     *controller message when quiz updated successfully.
    */
    public static final String QUIZ_UPDATED = "Quiz Successfully"
            + " Updated";
    /**
     *controller message when quiz deleted successfully.
    */
    public static final String QUIZ_DELETED = "Quiz Successfully "
            + "Deleted";
    /**
     *controller message when user successfully registered.
    */
    public static final String USER_REGISTERED = "User successfully registered";
    /**
     *controller message when user successfully login.
    */
    public static final String USER_LOGIN = "User successfully logged in";
    /**
     *controller message when user details retrieved by id.
    */
    public static final String USER_RETRIEVED_BY_ID = "Retrieved User details"
            + " by Id";
    /**
     *controller message when user details retrieved by Email.
    */
    public static final String USER_RETRIEVED_BY_EMAIL = "Retrieved student "
            + "details by EmailId";
    /**
     *controller message When user email validation.
    */
    public static final String USER_EMAIL_VALIDATE = "Successfully Validated";
    /**
     *controller message when user updated successfully.
    */
    public static final String USER_UPDATED = "User Successfully"
            + " Updated";
    /**
     *controller message when user deleted successfully.
    */
    public static final String USER_DELETED = "User Successfully "
            + "Deleted";
    /**
     *Logger message for category.
    */
    public static final String CATEGORY_LOGGER_MSG = "Entity to Dto conversion"
            + " in Category";
    /**
     *Logger message for Quiz.
    */
    public static final String QUIZ_LOGGER_MSG = "Entity to Dto conversion"
            + " in Quiz";
    /**
     *Logger message for Questions.
    */
    public static final String QUESTIONS_LOGGER_MSG = "Entity to Dto conversion"
            + " in Questions";
    /**
     *Logger message for Results.
    */
    public static final String RESULTS_LOGGER_MSG = "Entity to Dto conversion"
            + " in Results";
    /**
     *service message for FinalResults.
    */
    public static final String RESULTS_EMAIL = "Cannot find student email "
            + "in db";
    /**
     *Student name is blank.
    */
    public static final String USERNAME_NOTBLANK = "Student Name is Required";
    /**
     *when quiz name not exists.
    */
    public static final String QUIZNAME_NOTFOUND = "Cannot find quiz with name";
    /**
     *when category name not exists.
    */
    public static final String CATEGORYNAME_NOTFOUND =
            "Cannot find category with name";
}
