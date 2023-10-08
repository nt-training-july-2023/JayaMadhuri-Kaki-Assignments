import axios from 'axios'

const USER_URL = 'http://localhost:6060';
const CATEGORY_URL = 'http://localhost:6060/category';
const QUIZ_URL = 'http://localhost:6060/subCategory';
const QUESTION_URL = 'http://localhost:6060/questions';
const RESULT_URL = 'http://localhost:6060/results';

class Url {

    userRegistration(User) {
        return axios.post(USER_URL + "/register", User);
    }

    userLogin(UserEmailPassword) {
        return axios.post(USER_URL + "/login", UserEmailPassword);
    }

    getUserById(userId) {
        return axios.get(USER_URL + '/' + userId);
    }

    getUserByEmail(Email) {
        return axios.get(USER_URL + '/email/' + Email);
    }

    CheckUserByEmail(Email) {
        return axios.get(USER_URL + '/register/' + Email);
    }

    updateUser(userId, User) {
        return axios.put(USER_URL + '/' + userId, User);
    }

    deleteUser(userId) {
        return axios.delete(USER_URL + '/' + userId);
    }

    addCategory(Category) {
        return axios.post(CATEGORY_URL, Category);
    }

    getCategories() {
        return axios.get(CATEGORY_URL);
    }

    getCategoryByCategoryId(CategoryId) {
        return axios.get(CATEGORY_URL + '/' + CategoryId);
    }

    updateCategory(CategoryId, Category) {
        return axios.put(CATEGORY_URL + '/' + CategoryId, Category);
    }

    deleteCategory(CategoryId) {
        return axios.delete(CATEGORY_URL + '/' + CategoryId);
    }

    addQuiz(Quiz) {
        return axios.post(QUIZ_URL, Quiz);
    }

    getQuizByCategoryId(CategoryId) {
        return axios.get(QUIZ_URL + "/subCategoryByCategory/" + CategoryId);
    }

    getQuizById(QuizId) {
        return axios.get(QUIZ_URL + '/' + QuizId);
    }

    updateQuiz(QuizId, Quiz) {
        return axios.put(QUIZ_URL + '/' + QuizId, Quiz);
    }

    deleteQuiz(QuizId) {
        return axios.delete(QUIZ_URL + '/' + QuizId);
    }

    addQuestion(Question) {
        return axios.post(QUESTION_URL, Question);
    }

    getQuestionsByQuizId(QuizId) {
        return axios.get(QUESTION_URL + "/" + QuizId);
    }

    updateQuestion(QuestionId, Question) {
        return axios.put(QUESTION_URL + '/' + QuestionId, Question);
    }

    deleteQuestion(QuestionId) {
        return axios.delete(QUESTION_URL + '/' + QuestionId);
    }

    addResults(Results) {
        return axios.post(RESULT_URL, Results);
    }
    
    getResults() {
        return axios.get(RESULT_URL);
    }
    
    getResultsByStudentEmail(Email) {
        return axios.get(RESULT_URL + "/" + Email);
    }
}
export default new Url()