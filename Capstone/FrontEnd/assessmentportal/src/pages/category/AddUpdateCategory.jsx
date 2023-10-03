import React, { useState } from 'react';
import '../../styles/Category.scss';
import CategoryUrl from '../../service/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import TextInput from '../../components/input/TextInput';
import FormButton from '../../components/button/FormButton';
import Success from '../../components/sweetAlert/Success';
import Warning from '../../components/sweetAlert/Warning';

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
                        Success.render(sweetAlertMessages.ADD_TITILE,sweetAlertMessages.SUCCESS_ADD_MSG)
                        fetchData();
                        setPopUp(false);
                    }
                }).catch(error => {
                    if (error?.response?.status === 409) {
                        Warning.render(sweetAlertMessages.CATEGORY_ALREADY_EXISTS)
                    }
                })
        } else {
            setError(sweetAlertMessages.CATEGORY_NAME_REQUIRED)
        }
    }
    const handleUpdate = () => {
        if (categoryDetails?.categoryName.length != 0) {
            setError('')
            CategoryUrl.updateCategory(initialValues.categoryId, categoryDetails)
                .then(response => {
                    if (response?.data?.statusCode === 200) {
                        Success.render(sweetAlertMessages.UPDATE_TITLE,sweetAlertMessages.SUCCESS_UPDATE_MSG)
                        fetchData();
                        setPopUp(false);
                    }
                }).catch(error => {
                    if (error?.response?.status === 409) {
                        Warning.render(sweetAlertMessages.CATEGORY_ALREADY_EXISTS)
                    }
                })
        } else {
            setError(sweetAlertMessages.CATEGORY_NAME_REQUIRED)
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
                setError(sweetAlertMessages.CATEGORY_NAME_REQUIRED)
            } else {
                setError('')
            }
        }
        setCategoryDetails({ ...categoryDetails, [name]: value })
    }
    return (
        <div className="cat-container category-top">
            <h1 className="category-title1">{title}</h1>
            <TextInput className='name' name="categoryName" value={categoryDetails?.categoryName} placeholder='Enter Category Name' onChange={handleChange} />
            <p className='err'>{error}</p>
            <TextInput className='name' name='categoryDescription' value={categoryDetails?.categoryDescription} placeholder='Enter Description about Category' onChange={handleChange} />
            <FormButton className='btn' onClick={handleClick}>{title == "Add Category" ? "Add" : "Update"}</FormButton>
            <FormButton className='btn' onClick={() => { setPopUp(false) }}>Close</FormButton>
        </div>
    )
}
export default AddUpdateCategory;