import axios from 'axios'

const URL = 'http://localhost:6060/category';

class CategoryUrl{

    addCategory(Category){
        return axios.post(URL+"/add", Category);
    }

    getAllCategories(){
        return axios.get(URL+"/all");
    }

    getCategoryByCategoryId(CategoryId){
        return axios.get(URL+'/'+CategoryId);
    }

    updateCategory(CategoryId, Category){
        return axios.put(URL+'/update/'+CategoryId,Category);
    }

    deleteCategory(CategoryId){
        return axios.delete(URL+'/delete/'+CategoryId);
    } 
}

export default new CategoryUrl()