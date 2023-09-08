import React,{useState} from 'react';
import axios from 'axios'
import './AdminCategory.scss';
import Swal from 'sweetalert2'

const AddCategory = (props) =>{
    const {title,setPopUp,initialValues,fetchData} = props;
    const [categoryDetails,setCategoryDetails] = useState(initialValues);
    const [error,setError] = useState("");
    const handleAdd = () =>{
        if(categoryDetails?.categoryName.length!=0){
            setError('')
            axios.post("http://localhost:6060/addCategory",categoryDetails)
            .then(response=>{
                if(response?.data?.statusCode === 200){
                    Swal.fire({
                        title: 'Add',
                        text: 'Successfully Added',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "success",
                        background:"#15172b",
                        color:"white",
                    }); 
                    fetchData();
                    setPopUp(false);
                }
            }).catch(error=>{
                if(error?.response?.status === 409){
                    Swal.fire({
                        title: 'Error',
                        text: 'Category Name Already Exists',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "warning",
                        background:"#15172b",
                        color:"white",
                    }); 
                }
            })
        }else{
            setError('Category Name Required')
        }
    }
    const handleUpdate = () =>{
        if(categoryDetails?.categoryName.length!=0){
            setError('')
            axios.put(`http://localhost:6060/updateCategory/${initialValues.categoryId}`,categoryDetails)
            .then(response=>{
                console.log(response)
                if(response?.data?.statusCode === 200){
                    Swal.fire({
                        title: 'Update',
                        text: 'Successfully Updated',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "success",
                        background:"#15172b",
                        color:"white",
                    }); 
                    fetchData();
                    setPopUp(false);
                }
            }).catch(error=>{
                console.log(error)
                if(error?.response?.status === 409){
                    Swal.fire({
                        title: 'Error',
                        text: 'Something Wrong',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "warning",
                        background:"#15172b",
                        color:"white",
                    }); 
                }else{
                    Swal.fire({
                        title: 'Error',
                        text: 'A Category is Already Exists With Same Name',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "warning",
                        background:"#15172b",
                        color:"white",
                    }); 
                }
            })
        }else{
            setError('Category Name Required')
        }
    }
    const handleClick = () =>{
        if(title == "Add Category"){
            handleAdd();
        }else{
            handleUpdate();
        }
    }
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name == "categoryName"){
            if(!value){
                setError('category name required')
            }else{
                setError('')
            }
        }
        setCategoryDetails({...categoryDetails,[name]:value})
    }
    return(
        <div className="cat-container">
            <h1 className="category-title1">{title}</h1>
            <input className='name' type="text" name="categoryName" value={categoryDetails?.categoryName} placeholder='Enter Category Name' onChange={handleChange}/>
            <p className='err'>{error}</p>
            <textarea className='description' type="text" name='categoryDescription' value={categoryDetails?.categoryDescription} placeholder='Enter Description about Category' onChange={handleChange}/>
            <button className='btn' onClick={handleClick}>{title == "Add Category"? "Add" : "Update"}</button>
            <button className='btn' onClick={()=>{setPopUp(false)}}>Close</button>
        </div>
    )
}
export default AddCategory;