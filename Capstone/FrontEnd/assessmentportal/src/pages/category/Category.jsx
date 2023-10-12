import React, { useState, useEffect } from 'react'
import '../../styles/Category.scss';
import AddUpdateCategory from './AddUpdateCategory'
import Quiz from '../quiz/Quiz'
import CategoryUrl from '../../service/Url'
import { sweetAlertMessages } from "../../constants/ValidationMessages"
import Heading from '../../components/heading/Heading';
import Alert from '../../components/sweetAlert/Alert';
import CommonCard from '../../components/card/CommonCard';
import HeadingTwo from '../../components/heading/HeadingTwo';

const Category = (props) => {
    const { userDetails, setEnable, setRenderComponent } = props
    const showQuiz_AfterRefresh = localStorage.getItem("Current_Category_SubWindow")
    const [category, setCategory] = useState([])
    const [title, setTitle] = useState("Add Category")
    const [popUp, setPopUp] = useState(false)
    const [showQuiz, setShowQuiz] = useState(showQuiz_AfterRefresh === "quiz")
    const [selectedId, setSelectedId] = useState(null)
    const [selectedName, setSelectedName] = useState(null)
    const [loading, setLoading] = useState(false);
    const [isDisable,setIsDisable] = useState(false)
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
                if(error?.message === sweetAlertMessages.NETWORK_ERROR){
                    Alert.NetworkError(setRenderComponent)
                }else{
                    setLoading(true);
                    Alert.Warning(sweetAlertMessages.ERROR_GETTING_LIST)
                }
            })
    }
    const onClickCategory = (item) => {
        setShowQuiz(true);
        localStorage.setItem("Current_Category_SubWindow", "quiz")
        localStorage.setItem("CategoryId", item.categoryId)
        localStorage.setItem("CategoryName", item.categoryName)
        setSelectedId(item.categoryId);
        setSelectedName(item.categoryName)
    }
    const onClickCategoryUpdate = (item) => {
        setPopUp(true)
        let updateInitialValues = { categoryId: item?.categoryId, categoryName: item?.categoryName, categoryDescription: item?.categoryDescription }
        setInitialValues(updateInitialValues)
        setTitle("Update Category")
        setIsDisable(true)
    }
    const onClickCategoryDelete = (item) => {
        Alert.Delete(fetchData, item.categoryId, false, false, true,setRenderComponent)
    }
    useEffect(() => {
        fetchData()
    }, [])
    return (
        <div>
            {!showQuiz && <div>
                <Heading className="addcategory-button" onClick={handleAdd} buttonName="Add Category" headingText="Category" userDetails={userDetails} isDisable={isDisable} hrClassName="hr-sticky"/>
            </div>}
            {showQuiz ? (
                <Quiz userDetails={userDetails} setShowQuiz={setShowQuiz} selectedId={selectedId} setEnable={setEnable} selectedName={selectedName} />
            ) : (
                <div className='category-container-wrapper'>
                    {loading && <>
                        {category?.length > 0 ? (
                            <div className={popUp && 'display-none'}>
                                <div className="category-container category-card-margin">
                                    {category.map((item) => (
                                        <CommonCard
                                            data={item}
                                            onClickCard={() => { onClickCategory(item) }}
                                            userType={userDetails.UserType}
                                            cardType={"Category"}
                                            onClickUpdate={() => { onClickCategoryUpdate(item) }}
                                            onClickDelete={() => { onClickCategoryDelete(item) }}
                                        />
                                    ))}
                                </div>
                            </div>) :
                            (
                                <HeadingTwo className='h2-no-list' children={"No Categories"}/>
                            )}
                        {popUp && (
                            <AddUpdateCategory title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} setIsDisable={setIsDisable} setRenderComponent={setRenderComponent}/>
                        )}
                    </>}
                </div>
            )}
        </div>
    )
}
export default Category 