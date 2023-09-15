import axios from 'axios'

const URL = 'http://localhost:6060/subCategory';

class QuizUrl{

    addQuiz(Quiz){
        return axios.post(URL+"/add", Quiz);
    }

    getQuizByCategoryId(CategoryId){
        return axios.get(URL+"/subCategoryByCategory/"+CategoryId);
    }

    getQuizById(QuizId){
        return axios.get(URL+'/'+QuizId);
    }

    updateQuiz(QuizId, Quiz){
        return axios.put(URL+'/update/'+QuizId,Quiz);
    }

    deleteQuiz(QuizId){
        return axios.delete(URL+'/delete/'+QuizId);
    } 
}

export default new QuizUrl()