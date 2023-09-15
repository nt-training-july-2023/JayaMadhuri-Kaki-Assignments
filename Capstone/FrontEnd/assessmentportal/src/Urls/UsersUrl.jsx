import axios from 'axios'

const URL = 'http://localhost:6060/users';

class UsersUrl{

    userRegistration(User){
        return axios.post(URL+"/register", User);
    }

    userLogin(UserEmailPassword){
        return axios.post(URL+"/login",UserEmailPassword);
    }

    getUserById(userId){
        return axios.get(URL+'/'+userId);
    }

    getUserByEmail(Email){
        return axios.get(URL+'/'+Email);
    }

    updateUser(userId, User){
        return axios.put(URL+'/update/'+userId,User);
    }

    deleteUser(userId){
        return axios.delete(URL+'/delete/'+userId);
    } 
}

export default new UsersUrl()