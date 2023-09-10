import React,{useState,useEffect} from 'react';
import axios from 'axios'
import './Category.scss'
import AddCategory from './AddCategory';
import Swal from 'sweetalert2'

const Category = ({userDetails}) =>{
    const [category,setCategory] = useState([]);
    const [title,setTitle] = useState("Add Category");
    const message = "No Categories Found!";
    const [popUp,setPopUp] = useState(false);
    const [initialValues,setInitialValues] = useState({
        categoryName:"",
        categoryDescription:""
    })
    const handleAdd = () =>{
        setTitle("Add Category");
        setInitialValues({
            categoryName:"",
            categoryDescription:""
        })
        setPopUp(true);
    }
    const fetchData = async () => {
        try {
          const response = await axios.get("http://localhost:6060/allCategories");
            setCategory(response?.data?.listOfCategories);
        } catch (error) {
            message();
        }
    };
    useEffect(() => {
        fetchData();
    }, []);
    return(
        <div>
            <div>
                {userDetails?.UserType === "Admin" && <button className='addcategory-btn' onClick={handleAdd}>Add Category</button>}
                <h1 className='category-title'>Category</h1>
                <hr/>
            </div>
            <div className="category-container">
            {category.map((item) => (
            <div key={item.categoryId} className="category-card">
                {/* <p>Category ID: {item.categoryId}</p> */}
                <p>Name: {item.categoryName}</p>
                <p>Description: {item.categoryDescription}</p>
                {userDetails?.UserType === "Admin" && <div>
                    <button onClick={()=>{
                        setPopUp(true);
                        let updateInitialValues = {categoryId:item?.categoryId, categoryName:item?.categoryName, categoryDescription:item?.categoryDescription};
                        setInitialValues(updateInitialValues);
                        setTitle("Update Category");
                    }}  className='category-btn'>Update</button>
                    <button onClick={()=>{
                            axios.delete(`http://localhost:6060/deleteCategory/${item.categoryId}`)
                            .then(response=>{
                                if(response?.data?.statusCode == 200){
                                    Swal.fire({
                                        title: 'Delete',
                                        text: 'Successfully Deleted',
                                        timer: 1000,
                                        showConfirmButton:false,
                                        showCancelButton:false,
                                        icon: "success",
                                        background:"#15172b",
                                        color:"white",
                                    }); 
                                    fetchData()
                                }
                            }).catch(error=>{
                                if(error?.response?.status == "404"){
                                    Swal.fire({
                                        title: 'Delete',
                                        text: 'ID Not Found',
                                        timer: 1000,
                                        showConfirmButton:false,
                                        showCancelButton:false,
                                        icon: "warning",
                                        background:"#15172b",
                                        color:"white",
                                    }); 
                                }
                            })
                        }} className='category-btn'>Delete</button>
                </div>}
            </div>
            ))}
            </div>
            {popUp && (
                <AddCategory title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData}/>
            )}
        </div>
    )
}

export default Category;