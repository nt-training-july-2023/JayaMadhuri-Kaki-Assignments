import axios from 'axios'

const URL = 'http://localhost:6060/results';

class ResultUrl{

    addResults(Results){
        return axios.post(URL+"/add", Results);
    }

    checkIsAttempted(UserId, QuizId){
        return axios.get(URL+'/'+UserId+"/"+QuizId);
    }

}

export default new ResultUrl()