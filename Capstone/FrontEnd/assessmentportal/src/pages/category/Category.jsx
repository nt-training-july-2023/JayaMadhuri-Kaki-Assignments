import React, { useState, useEffect } from 'react'
import '../../styles/Category.scss';
import AddUpdateCategory from './AddUpdateCategory'
import Quiz from '../quiz/Quiz'
import CategoryUrl from '../../service/Url'
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CardButton from '../../components/button/CardButton';
import Heading from '../../components/heading/Heading';
import Warning from '../../components/sweetAlert/Warning';
import Delete from '../../components/sweetAlert/Delete';

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
                Warning.render(sweetAlertMessages.ERROR_GETTING_LIST)
            })
    }
    useEffect(() => {
        fetchData()
    }, [])
    return (
        <div>
            {!showQuiz && <div>
                <Heading className="addcategory-button" onClick={handleAdd} buttonName="Add Category" headingText="Category" userDetails={userDetails}/>
            </div>}
            {showQuiz ? (
                <Quiz userDetails={userDetails} setShowQuiz={setShowQuiz} selectedId={selectedId} setEnable={setEnable} selectedName={selectedName}/>
            ) : (
                <div>
                    {loading && <>
                    {category?.length > 0 ? (
                    <div className={popUp && 'display-none'}>
                        <div className="category-container category-card-margin"> 
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
                                    {userDetails?.UserType === "Admin" && <div className='categorycard-buttons-div'>
                                        <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            setPopUp(true)
                                            event.stopPropagation()
                                            let updateInitialValues = { categoryId: item?.categoryId, categoryName: item?.categoryName, categoryDescription: item?.categoryDescription }
                                            setInitialValues(updateInitialValues)
                                            setTitle("Update Category")
                                        }} className='categorycard-button categorycard-button-update'>Update</CardButton>
                                        <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            event.stopPropagation()
                                            Delete.render(fetchData,item.categoryId,false,false,true)
                                        }} className='categorycard-button categorycard-button-delete'>Delete</CardButton>
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