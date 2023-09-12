import React,{useState,useEffect} from 'react';
import axios from 'axios'
import './Category.scss'
import AddUpdateCategory from './AddUpdateCategory';
import Swal from 'sweetalert2'
import Quiz from '../Quiz/Quiz';

const Category = ({userDetails}) =>{
    const [category,setCategory] = useState([]);
    const [title,setTitle] = useState("Add Category");
    const [popUp,setPopUp] = useState(false);
    const [showQuiz,setShowQuiz] = useState(false);
    const [selectedId,setSelectedId] = useState(null);
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
            Swal.fire({
                title: 'Error',
                text: 'Error In Getting Category List',
                timer: 1500,
                showConfirmButton:false,
                showCancelButton:false,
                icon: "warning",
                background:"#15172b",
                color:"white",
            }); 
        }
    };   
    useEffect(() => {
        fetchData();
    }, []);
    return(
        <div>
            {!showQuiz && <div>
                {userDetails?.UserType === "Admin" && <button className='addcategory-btn' onClick={handleAdd}>Add Category</button>}
                <h1 className='category-title'>Category</h1>
                <hr/>
            </div>}
            {showQuiz ? (
                <Quiz userDetails={userDetails} setShowQuiz={setShowQuiz} selectedId={selectedId}/>
            ) : (
            <div>
            {category.length>0 ?(<>
            <div className="category-container">
            {category.map((item) => (
            <div key={item.categoryId} className="category-card" onClick={()=>{setShowQuiz(true);setSelectedId(item.categoryId);}}>
                {/* <p>Category ID: {item.categoryId}</p> */}
                <p>Name: {item.categoryName}</p>
                <p>Description: {item.categoryDescription}</p>
                {userDetails?.UserType === "Admin" && <div>
                    <button onMouseDown={event => event.stopPropagation()} onClick={(event)=>{
                        setPopUp(true);
                        event.stopPropagation();
                        let updateInitialValues = {categoryId:item?.categoryId, categoryName:item?.categoryName, categoryDescription:item?.categoryDescription};
                        setInitialValues(updateInitialValues);
                        setTitle("Update Category");
                    }}  className='category-btn'>Update</button>
                    <button onMouseDown={event => event.stopPropagation()} onClick={(event)=>{
                            event.stopPropagation();
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
            </>):
            (
                <h2 style={{textAlign:"center",color:"#31334e"}}>No Categories</h2>
            )}
            {popUp && (
                <AddUpdateCategory title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData}/>
            )}
            </div>
            )};
        </div>
    )
}

export default Category;