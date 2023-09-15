import React, { useState } from 'react';
import './Quiz.scss';
import Swal from 'sweetalert2'
import QuizUrl from '../../Services/Url';

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
                                title: 'Add',
                                text: 'Successfully Added',
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "success",
                                background: "#15172b",
                                color: "white",
                            });
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Swal.fire({
                                title: 'Error',
                                text: 'Quiz Name Already Exists',
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "warning",
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
                                title: 'Update',
                                text: 'Successfully Updated',
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "success",
                                background: "#15172b",
                                color: "white",
                            });
                            fetchData();
                            setPopUp(false);
                        }
                    }).catch(error => {
                        if (error?.response?.status === 409) {
                            Swal.fire({
                                title: 'Error',
                                text: 'Quiz Name Already Exists',
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "warning",
                                background: "#15172b",
                                color: "white",
                            });
                        } else {
                            Swal.fire({
                                title: 'Error',
                                text: 'Something went wrong',
                                timer: 1500,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "warning",
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
        <div className="addquiz-container">
            <h1 className="quiz-title1">{title}</h1>
            <input className='name' type="text" name="subCategoryName" value={quizDetails?.subCategoryName} placeholder='Enter SubCategory Name' onChange={handleChange} />
            <p className='err'>{error}</p>
            <input className='name' type="number" name="timeLimitInMinutes" value={quizDetails?.timeLimitInMinutes} placeholder='Enter Time Limit(In Minutes)' onChange={handleChange} />
            <p className='err'>{timeError}</p>
            <textarea className='description' type="text" name='subCategoryDescription' value={quizDetails?.subCategoryDescription} placeholder='Enter Description about Quiz' onChange={handleChange} />
            <button className='btn' onClick={handleClick}>{title == "Add Quiz" ? "Add" : "Update"}</button>
            <button className='btn' onClick={() => { setPopUp(false) }}>Close</button>
        </div>
    )
}
export default AddUpdateQuiz;