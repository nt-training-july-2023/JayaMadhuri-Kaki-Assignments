import React, { useState, useEffect } from 'react'
import '../Styles/Category.scss';
import AddUpdateCategory from './AddUpdateCategory'
import Swal from 'sweetalert2'
import Quiz from '../Quiz/Quiz'
import CategoryUrl from '../../Services/Url'

const Category = (props) => {
    const {userDetails, setEnable} = props
    const showQuiz_AfterRefresh =  localStorage.getItem("Current_Category_SubWindow")
    const [category, setCategory] = useState([])
    const [title, setTitle] = useState("Add Category")
    const [popUp, setPopUp] = useState(false)
    const [showQuiz, setShowQuiz] = useState(showQuiz_AfterRefresh === "quiz")
    const [selectedId, setSelectedId] = useState(null)
    const [selectedName, setSelectedName] = useState(null)
    const [loading, setLoading] = useState(false);
    const [initialValues, setInitialValues] = useState({
        categoryName: "",
        categoryDescription: ""
    })
    const handleAdd = () => {
        setTitle("Add Category")
        setInitialValues({
            categoryName: "",
            categoryDescription: ""
        })
        setPopUp(true)
    }
    const fetchData = async () => {
        CategoryUrl.getCategories()
            .then(response => {
                setCategory(response?.data?.listOfCategories)
                setLoading(true);
            }).catch(error => {
                setLoading(true);
                Swal.fire({
                    title: 'Error',
                    text: 'Error In Getting Category List',
                    timer: 1500,
                    showConfirmButton: false,
                    showCancelButton: false,
                    icon: "warning",
                    background: "#15172b",
                    color: "white",
                })
            })
    }
    useEffect(() => {
        fetchData()
    }, [])
    return (
        <div>
            {!showQuiz && <div>
                {userDetails?.UserType === "Admin" && <button className='addcategory-btn' onClick={handleAdd}>Add Category</button>}
                <h1 className='category-title'>Category</h1>
                <hr />
            </div>}
            {showQuiz ? (
                <Quiz userDetails={userDetails} setShowQuiz={setShowQuiz} selectedId={selectedId} setEnable={setEnable} selectedName={selectedName}/>
            ) : (
                <div>
                    {loading && <>
                    {category?.length > 0 ? (
                    <div className={popUp && 'display-none'}>
                        <div className="category-container"> 
                            {category.map((item) => (
                                <div key={item.categoryId} className="category-card" onClick={() => {
                                    setShowQuiz(true);
                                    localStorage.setItem("Current_Category_SubWindow","quiz")
                                    localStorage.setItem("CategoryId",item.categoryId)
                                    localStorage.setItem("CategoryName",item.categoryName)
                                    setSelectedId(item.categoryId); 
                                    setSelectedName(item.categoryName)
                                 }}>
                                    <p>Name: {item.categoryName}</p>
                                    <p>Description: {item.categoryDescription}</p>
                                    {userDetails?.UserType === "Admin" && <div>
                                        <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            setPopUp(true)
                                            event.stopPropagation()
                                            let updateInitialValues = { categoryId: item?.categoryId, categoryName: item?.categoryName, categoryDescription: item?.categoryDescription }
                                            setInitialValues(updateInitialValues)
                                            setTitle("Update Category")
                                        }} className='category-btn category-btn1'>Update</button>
                                        <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            event.stopPropagation()
                                            Swal.fire({
                                                text: 'do you really want to delete?',
                                                icon: "warning",
                                                background: "#15172b",
                                                color: "white",
                                                showCancelButton:true
                                            }).then(function (result) {
                                                if (result.value === true) {
                                                    CategoryUrl.deleteCategory(item.categoryId)
                                                    .then(response => {
                                                        if (response?.data?.statusCode == 200) {
                                                            Swal.fire({
                                                                title: 'Delete',
                                                                text: 'Successfully Deleted',
                                                                timer: 1000,
                                                                showConfirmButton: false,
                                                                showCancelButton: false,
                                                                icon: "success",
                                                                background: "#15172b",
                                                                color: "white",
                                                            })
                                                            fetchData();
                                                        }
                                                    }).catch(error => {
                                                        if (error?.response?.status == "404") {
                                                            Swal.fire({
                                                                title: 'Delete',
                                                                text: 'ID Not Found',
                                                                timer: 1000,
                                                                showConfirmButton: false,
                                                                showCancelButton: false,
                                                                icon: "warning",
                                                                background: "#15172b",
                                                                color: "white",
                                                            })
                                                        }
                                                    })
                                                } 
                                            })
                                        }} className='category-btn category-btn2'>Delete</button>
                                    </div>}
                                </div>
                            ))}
                        </div>
                    </div>) :
                        (
                            <h2 style={{ textAlign: "center", color: "#31334e", marginTop:"200px" }}>No Categories</h2>
                        )}
                    {popUp && (
                        <AddUpdateCategory title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} />
                    )}
                    </>}
                </div>
            )}
        </div>
    )
}
export default Category 