import axios from 'axios'

const USER_URL = 'http://localhost:6060/users';
const CATEGORY_URL = 'http://localhost:6060/category';
const QUIZ_URL = 'http://localhost:6060/subCategory';
const QUESTION_URL = 'http://localhost:6060/questions';
const RESULT_URL = 'http://localhost:6060/results';
const FINALRESULT_URL = 'http://localhost:6060/finalResults';

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

    updateUser(userId, User) {
        return axios.put(USER_URL + '/update/' + userId, User);
    }

    deleteUser(userId) {
        return axios.delete(USER_URL + '/delete/' + userId);
    }

    addCategory(Category) {
        return axios.post(CATEGORY_URL + "/add", Category);
    }

    getAllCategories() {
        return axios.get(CATEGORY_URL + "/all");
    }

    getCategoryByCategoryId(CategoryId) {
        return axios.get(CATEGORY_URL + '/' + CategoryId);
    }

    updateCategory(CategoryId, Category) {
        return axios.put(CATEGORY_URL + '/update/' + CategoryId, Category);
    }

    deleteCategory(CategoryId) {
        return axios.delete(CATEGORY_URL + '/delete/' + CategoryId);
    }

    addQuiz(Quiz) {
        return axios.post(QUIZ_URL + "/add", Quiz);
    }

    getQuizByCategoryId(CategoryId) {
        return axios.get(QUIZ_URL + "/subCategoryByCategory/" + CategoryId);
    }

    getQuizById(QuizId) {
        return axios.get(QUIZ_URL + '/' + QuizId);
    }

    updateQuiz(QuizId, Quiz) {
        return axios.put(QUIZ_URL + '/update/' + QuizId, Quiz);
    }

    deleteQuiz(QuizId) {
        return axios.delete(QUIZ_URL + '/delete/' + QuizId);
    }

    addQuestion(Question) {
        return axios.post(QUESTION_URL + "/add", Question);
    }

    getAllQuestionsByQuizId(QuizId) {
        return axios.get(QUESTION_URL + "/" + QuizId);
    }

    updateQuestion(QuestionId, Question) {
        return axios.put(QUESTION_URL + '/update/' + QuestionId, Question);
    }

    deleteQuestion(QuestionId) {
        return axios.delete(QUESTION_URL + '/delete/' + QuestionId);
    }

    addResults(Results) {
        return axios.post(RESULT_URL + "/add", Results);
    }
    
    getAllResults() {
        return axios.get(FINALRESULT_URL);
    }
    
    getAllResultsByStudentEmail(Email) {
        return axios.get(FINALRESULT_URL + "/" + Email);
    }
}
export default new Url()