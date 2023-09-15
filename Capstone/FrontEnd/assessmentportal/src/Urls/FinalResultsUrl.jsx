import axios from 'axios'

const URL = 'http://localhost:6060/finalResults';

class FinalResultsUrl{
    getAllResults(){
        return axios.get(URL);
    }
    getAllResultsByStudentEmail(Email){
        return axios.get(URL+"/"+Email);
    }
}

export default new FinalResultsUrl()