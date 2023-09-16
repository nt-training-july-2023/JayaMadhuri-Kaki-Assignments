import React, { useState, useEffect } from 'react'
import '../Styles/Category.scss';
import AddUpdateCategory from './AddUpdateCategory'
import Swal from 'sweetalert2'
import Quiz from '../Quiz/Quiz'
import CategoryUrl from '../../Services/Url'

const Category = ({ userDetails, setEnable }) => {
    const [category, setCategory] = useState([])
    const [title, setTitle] = useState("Add Category")
    const [popUp, setPopUp] = useState(false)
    const [showQuiz, setShowQuiz] = useState(false)
    const [selectedId, setSelectedId] = useState(null)
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
        CategoryUrl.getAllCategories()
            .then(response => {
                setCategory(response?.data?.listOfCategories)
            }).catch(error => {
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
                <Quiz userDetails={userDetails} setShowQuiz={setShowQuiz} selectedId={selectedId} setEnable={setEnable} />
            ) : (
                <div>
                    {category?.length > 0 ? (<>
                        <div className="category-container">
                            {category.map((item) => (
                                <div key={item.categoryId} className="category-card" onClick={() => { setShowQuiz(true); setSelectedId(item.categoryId) }}>
                                    <p>Name: {item.categoryName}</p>
                                    <p>Description: {item.categoryDescription}</p>
                                    {userDetails?.UserType === "Admin" && <div>
                                        <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            setPopUp(true)
                                            event.stopPropagation()
                                            let updateInitialValues = { categoryId: item?.categoryId, categoryName: item?.categoryName, categoryDescription: item?.categoryDescription }
                                            setInitialValues(updateInitialValues)
                                            setTitle("Update Category")
                                        }} className='category-btn'>Update</button>
                                        <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            event.stopPropagation()
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
                                                        fetchData()
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
                                        }} className='category-btn'>Delete</button>
                                    </div>}
                                </div>
                            ))}
                        </div>
                    </>) :
                        (
                            <h2 style={{ textAlign: "center", color: "#31334e", marginTop:"200px" }}>No Categories</h2>
                        )}
                    {popUp && (
                        <AddUpdateCategory title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} />
                    )}
                </div>
            )}
        </div>
    )
}
export default Category 