import axios from 'axios'

const URL = 'http://localhost:6060/questions';

class QuestionUrl{

    addQuestion(Question){
        return axios.post(URL+"/add", Question);
    }

    getAllQuestionsByQuizId(QuizId){
        return axios.get(URL+"/"+QuizId);
    }

    updateQuestion(QuestionId, Question){
        return axios.put(URL+'/update/'+QuestionId,Question);
    }

    deleteQuestion(QuestionId){
        return axios.delete(URL+'/delete/'+QuestionId);
    } 
}

export default new QuestionUrl()