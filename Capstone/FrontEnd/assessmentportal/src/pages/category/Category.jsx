import React, { useState, useEffect } from 'react'
import '../../styles/Category.scss';
import AddUpdateCategory from './AddUpdateCategory'
import Swal from 'sweetalert2'
import Quiz from '../quiz/Quiz'
import CategoryUrl from '../../services/Url'
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CardButton from '../../components/button/CardButton';
import FormButton from '../../components/button/FormButton';
import Heading from '../../components/heading/Heading';

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
                setCategory(response?.data?.responseData)
                setLoading(true);
            }).catch(error => {
                setLoading(true);
                Swal.fire({
                    title: sweetAlertMessages.ERROR,
                    text: sweetAlertMessages.ERROR_GETTING_LIST,
                    timer: 1500,
                    showConfirmButton: false,
                    showCancelButton: false,
                    icon: sweetAlertMessages.WARNING,
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
                <Heading className="addcategory-btn" onClick={handleAdd} buttonName="Add Category" headingText="Category" userDetails={userDetails}/>
            </div>}
            {showQuiz ? (
                <Quiz userDetails={userDetails} setShowQuiz={setShowQuiz} selectedId={selectedId} setEnable={setEnable} selectedName={selectedName}/>
            ) : (
                <div>
                    {loading && <>
                    {category?.length > 0 ? (
                    <div className={popUp && 'display-none'}>
                        <div className="category-container card-margin-category"> 
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
                                    {userDetails?.UserType === "Admin" && <div className='button-categorycard'>
                                        <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            setPopUp(true)
                                            event.stopPropagation()
                                            let updateInitialValues = { categoryId: item?.categoryId, categoryName: item?.categoryName, categoryDescription: item?.categoryDescription }
                                            setInitialValues(updateInitialValues)
                                            setTitle("Update Category")
                                        }} className='category-btn category-btn1'>Update</CardButton>
                                        <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
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
                                        }} className='category-btn category-btn2'>Delete</CardButton>
                                    </div>}
                                </div>
                            ))}
                        </div>
                    </div>) :
                        (
                            <h2 className='h2-no-list'>No Categories</h2>
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