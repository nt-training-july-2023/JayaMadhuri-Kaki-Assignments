import React, { useState } from 'react';
import '../../styles/Category.scss';
import CategoryUrl from '../../service/Url';
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import Input from '../../components/input/Input';
import Button from '../../components/button/Button';
import Alert from '../../components/sweetAlert/Alert';
import HeadingOne from '../../components/heading/HeadingOne';
import Paragraph from '../../components/paragraph/Paragraph';

const AddUpdateCategory = (props) => {
    const { title, setPopUp, initialValues, fetchData, setIsDisable, setRenderComponent } = props;
    const [categoryDetails, setCategoryDetails] = useState(initialValues);
    const [error, setError] = useState("");
    const handleAdd = () => {
        if (categoryDetails?.categoryName.length != 0) {
            setError('')
            CategoryUrl.addCategory(categoryDetails)
                .then(response => {
                    if (response?.data?.statusCode === 200) {
                        Alert.Success(sweetAlertMessages.ADD_TITILE,sweetAlertMessages.SUCCESS_ADD_MSG)
                        fetchData();
                        setPopUp(false);
                    }
                }).catch(error => {
                    if (error?.response?.status === 409) {
                        Alert.Warning(sweetAlertMessages.CATEGORY_ALREADY_EXISTS)
                    }else if(error?.response?.status === 400){
                        Alert.Warning(errorMessages.CATEGORY_NAME_REQUIRED)
                    }else if(error?.message === sweetAlertMessages.NETWORK_ERROR){
                        Alert.NetworkError(setRenderComponent)
                    }
                })
        } else {
            setError(errorMessages.CATEGORY_NAME_REQUIRED)
        }
    }
    const handleUpdate = () => {
        if (categoryDetails?.categoryName.length != 0) {
            setError('')
            CategoryUrl.updateCategory(initialValues.categoryId, categoryDetails)
                .then(response => {
                    if (response?.data?.statusCode === 200) {
                        Alert.Success(sweetAlertMessages.UPDATE_TITLE,sweetAlertMessages.SUCCESS_UPDATE_MSG)
                        fetchData();
                        setPopUp(false);
                        setIsDisable(false)
                    }
                }).catch(error => {
                    if (error?.response?.status === 409) {
                        Alert.Warning(sweetAlertMessages.CATEGORY_ALREADY_EXISTS)
                    }else if(error?.response?.status === 400){
                        Alert.Warning(errorMessages.CATEGORY_NAME_REQUIRED)
                    }else if(error?.message === sweetAlertMessages.NETWORK_ERROR){
                        Alert.NetworkError(setRenderComponent)
                    }
                })
        } else {
            setError(errorMessages.CATEGORY_NAME_REQUIRED)
        }
    }
    const handleClick = () => {
        if (title == "Add Category") {
            handleAdd();
        } else {
            handleUpdate();
        }
    }
    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name == "categoryName") {
            if (!value) {
                setError(errorMessages.CATEGORY_NAME_REQUIRED)
            } else {
                setError('')
            }
        }
        setCategoryDetails({ ...categoryDetails, [name]: value })
    }
    return (
        <div className="category-form-container category-top-margin">
            <HeadingOne className="category-form-title" children={title}/>
            <Input type="text" className='form-input' name="categoryName" value={categoryDetails?.categoryName} placeholder='Enter Category Name' onChange={handleChange} />
            <Paragraph className='category-quiz-errors' children={error}/>
            <Input type="text" className='form-input' name='categoryDescription' value={categoryDetails?.categoryDescription} placeholder='Enter Description about Category' onChange={handleChange} />
            <Button className='form-button' onClick={handleClick} children={title == "Add Category" ? "Add" : "Update"}/>
            <Button className='form-button' onClick={() => { setPopUp(false); setIsDisable(false)}} children="Close"/>
        </div>
    )
}
export default AddUpdateCategory;