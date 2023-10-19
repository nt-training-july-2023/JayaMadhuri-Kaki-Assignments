import React, { useState } from 'react';
import '../../styles/Category.scss';
import QuizUrl from '../../service/Url';
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import Input from '../../components/input/Input';
import Button from '../../components/button/Button';
import Alert from '../../components/sweetAlert/Alert';
import HeadingOne from '../../components/heading/HeadingOne';
import Paragraph from '../../components/paragraph/Paragraph';

const AddUpdateQuiz = (props) => {
    const { title, setPopUp, initialValues, fetchData, setIsDisable, setRenderComponent } = props;
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
                            Alert.Success(sweetAlertMessages.ADD_TITILE,sweetAlertMessages.SUCCESS_ADD_MSG)
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Alert.Warning(sweetAlertMessages.QUIZ_ALREADY_EXISTS)
                        }else if(error?.response?.status === 400){
                            Alert.Warning(errorMessages.QUIZ_NAME_REQUIRED)
                        }else if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                            Alert.NetworkError(setRenderComponent)
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
                QuizUrl.updateQuiz(initialValues.subCategoryId, quizDetails)
                    .then(response => {
                        if (response?.data?.statusCode === 200) {
                            Alert.Success(sweetAlertMessages.UPDATE_TITLE,sweetAlertMessages.SUCCESS_UPDATE_MSG)
                            fetchData();
                            setPopUp(false);
                            setIsDisable(false)
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Alert.Warning(sweetAlertMessages.QUIZ_ALREADY_EXISTS)
                        }else if(error?.response?.status === 400){
                            Alert.Warning(errorMessages.QUIZ_NAME_REQUIRED)
                        }else if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                            Alert.NetworkError(setRenderComponent)
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
            <HeadingOne className="category-form-title" children={title}/>
            <Input type="text" className='form-input' name="subCategoryName" value={quizDetails?.subCategoryName} placeholder='Enter SubCategory Name' onChange={handleChange} />
            <Paragraph className='category-quiz-errors' children={error}/>
            <Input type="number"  name="timeLimitInMinutes" placeholder="Enter Time Limit(In Minutes)" className="form-input" value={quizDetails?.timeLimitInMinutes} onChange={handleChange} />
            <Paragraph className='category-quiz-errors' children={timeError}/>
            <Input  type="text" className='form-input' name='subCategoryDescription' value={quizDetails?.subCategoryDescription} placeholder='Enter Description about Quiz' onChange={handleChange} />
            <Button className='form-button' onClick={handleClick} children={title == "Add Quiz" ? "Add" : "Update"}/>
            <Button className='form-button' onClick={() => { setPopUp(false); setIsDisable(false) }} children="Close"/>
        </div>
    )
}
export default AddUpdateQuiz;