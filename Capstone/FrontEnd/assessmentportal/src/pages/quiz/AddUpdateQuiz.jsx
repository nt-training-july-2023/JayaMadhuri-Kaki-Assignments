import React, { useState } from 'react';
import '../../styles/Category.scss';
import QuizUrl from '../../service/Url';
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import TextInput from '../../components/input/TextInput';
import NumberInput from '../../components/input/NumberInput';
import FormButton from '../../components/button/FormButton';
import Success from '../../components/sweetAlert/Success';
import Warning from '../../components/sweetAlert/Warning';

const AddUpdateQuiz = (props) => {
    const { title, setPopUp, initialValues, fetchData } = props;
    const [quizDetails, setQuizDetails] = useState(initialValues);
    const [error, setError] = useState("");
    const [timeError, setTimeError] = useState("");
    const handleAdd = () => {
        if (quizDetails?.subCategoryName.length != 0) {
            if (quizDetails?.timeLimitInMinutes.length != 0) {
                setError('')
                setTimeError('')
                QuizUrl.addQuiz(quizDetails)
                    .then(response => {
                        if (response?.data?.statusCode === 200) {
                            Success.render(sweetAlertMessages.ADD_TITILE,sweetAlertMessages.SUCCESS_ADD_MSG)
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Warning.render(sweetAlertMessages.QUIZ_ALREADY_EXISTS)
                        }
                    })

            } else {
                setTimeError(errorMessages.TIME_LIMIT_REQUIRED)
            }
        } else {
            setError(errorMessages.QUIZ_NAME_REQUIRED)
        }
    }
    const handleUpdate = () => {
        if (quizDetails?.subCategoryName.length != 0) {
            if (quizDetails?.timeLimitInMinutes.length != 0) {
                setError('')
                setTimeError('')
                setError('')
                QuizUrl.updateQuiz(initialValues.subCategoryId, quizDetails)
                    .then(response => {
                        if (response?.data?.statusCode === 200) {
                            Success.render(sweetAlertMessages.UPDATE_TITLE,sweetAlertMessages.SUCCESS_UPDATE_MSG)
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Warning.render(sweetAlertMessages.QUIZ_ALREADY_EXISTS)
                        } else {
                            Warning.render(sweetAlertMessages.SOMETHING_WENT_WRONG)
                        }
                    })
            } else {
                setTimeError(errorMessages.TIME_LIMIT_REQUIRED)
            }
        } else {
            setError(errorMessages.QUIZ_NAME_REQUIRED)
        }
    }
    const handleClick = () => {
        if (title == "Add Quiz") {
            handleAdd();
        } else {
            handleUpdate();
        }
    }
    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name == "subCategoryName") {
            if (!value) {
                setError(errorMessages.QUIZ_NAME_REQUIRED)
            } else {
                setError('')
            }
        }
        if (name == "timeLimitInMinutes") {
            if (!value) {
                setTimeError(errorMessages.TIME_LIMIT_REQUIRED)
            } else {
                setTimeError('')
            }
        }
        setQuizDetails({ ...quizDetails, [name]: value })
    }
    return (
        <div className="category-form-container quiz-top-margin">
            <h1 className="category-form-title">{title}</h1>
            <TextInput className='form-input' name="subCategoryName" value={quizDetails?.subCategoryName} placeholder='Enter SubCategory Name' onChange={handleChange} />
            <p className='category-quiz-errors'>{error}</p>
            <NumberInput value={quizDetails?.timeLimitInMinutes} onChange={handleChange} />
            <p className='category-quiz-errors'>{timeError}</p>
            <TextInput className='form-input' name='subCategoryDescription' value={quizDetails?.subCategoryDescription} placeholder='Enter Description about Quiz' onChange={handleChange} />
            <FormButton className='form-button' onClick={handleClick}>{title == "Add Quiz" ? "Add" : "Update"}</FormButton>
            <FormButton className='form-button' onClick={() => { setPopUp(false) }}>Close</FormButton>
        </div>
    )
}
export default AddUpdateQuiz;