import React, { useState } from 'react';
import '../../styles/Category.scss';
import Swal from 'sweetalert2'
import QuizUrl from '../../services/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import TextInput from '../../components/input/TextInput';
import NumberInput from '../../components/input/NumberInput';
import FormButton from '../../components/button/FormButton';

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
                            Swal.fire({
                                title: sweetAlertMessages.ADD_TITILE,
                                text: sweetAlertMessages.SUCCESS_ADD_MSG,
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: sweetAlertMessages.SUCCESS,
                                background: "#15172b",
                                color: "white",
                            });
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Swal.fire({
                                title: sweetAlertMessages.ERROR,
                                text: sweetAlertMessages.QUIZ_ALREADY_EXISTS,
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: sweetAlertMessages.WARNING,
                                background: "#15172b",
                                color: "white",
                            });
                        }
                    })

            } else {
                setTimeError('Time Limit Required')
            }
        } else {
            setError('Quiz Name Required')
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
                            Swal.fire({
                                title: sweetAlertMessages.UPDATE_TITLE,
                                text: sweetAlertMessages.SUCCESS_UPDATE_MSG,
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: sweetAlertMessages.SUCCESS,
                                background: "#15172b",
                                color: "white",
                            });
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Swal.fire({
                                title: sweetAlertMessages.ERROR,
                                text: sweetAlertMessages.QUIZ_ALREADY_EXISTS,
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: sweetAlertMessages.WARNING,
                                background: "#15172b",
                                color: "white",
                            });
                        } else {
                            Swal.fire({
                                title: sweetAlertMessages.ERROR,
                                text:  sweetAlertMessages.SOMETHING_WENT_WRONG,
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: sweetAlertMessages.WARNING,
                                background: "#15172b",
                                color: "white",
                            });
                        }
                    })
            } else {
                setTimeError('Time Limit Required')
            }
        } else {
            setError('Quiz Name Required')
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
                setError('SubCategory name required')
            } else {
                setError('')
            }
        }
        if (name == "timeLimitInMinutes") {
            if (!value) {
                setTimeError('Time Limit Required')
            } else {
                setTimeError('')
            }
        }
        setQuizDetails({ ...quizDetails, [name]: value })
    }
    return (
        <div className="cat-container quiz-top">
            <h1 className="category-title1">{title}</h1>
            <TextInput className='name' name="subCategoryName" value={quizDetails?.subCategoryName} placeholder='Enter SubCategory Name' onChange={handleChange} />
            <p className='err'>{error}</p>
            <NumberInput value={quizDetails?.timeLimitInMinutes} onChange={handleChange} />
            <p className='err'>{timeError}</p>
            <TextInput className='name' name='subCategoryDescription' value={quizDetails?.subCategoryDescription} placeholder='Enter Description about Quiz' onChange={handleChange} />
            <FormButton className='btn' onClick={handleClick}>{title == "Add Quiz" ? "Add" : "Update"}</FormButton>
            <FormButton className='btn' onClick={() => { setPopUp(false) }}>Close</FormButton>
        </div>
    )
}
export default AddUpdateQuiz;