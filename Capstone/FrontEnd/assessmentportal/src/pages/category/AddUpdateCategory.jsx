import React, { useState } from 'react';
import '../../styles/Category.scss';
import CategoryUrl from '../../service/Url';
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import Input from '../../components/input/Input';
import Button from '../../components/button/Button';
import Alert from '../../components/sweetAlert/Alert';

const AddUpdateCategory = (props) => {
    const { title, setPopUp, initialValues, fetchData } = props;
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
                    }
                }).catch(error => {
                    if (error?.response?.status === 409) {
                        Alert.Warning(sweetAlertMessages.CATEGORY_ALREADY_EXISTS)
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
            <h1 className="category-form-title">{title}</h1>
            <Input type="text" className='form-input' name="categoryName" value={categoryDetails?.categoryName} placeholder='Enter Category Name' onChange={handleChange} />
            <p className='category-quiz-errors'>{error}</p>
            <Input type="text" className='form-input' name='categoryDescription' value={categoryDetails?.categoryDescription} placeholder='Enter Description about Category' onChange={handleChange} />
            <Button className='form-button' onClick={handleClick}>{title == "Add Category" ? "Add" : "Update"}</Button>
            <Button className='form-button' onClick={() => { setPopUp(false) }}>Close</Button>
        </div>
    )
}
export default AddUpdateCategory;