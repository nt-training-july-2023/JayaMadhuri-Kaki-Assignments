import React, { useState } from 'react'
import '../Styles/Question.scss'
import Swal from 'sweetalert2'
import QuestionUrl from '../../Services/Url'
import {sweetAlertMessages}  from "../../../src/constants/ValidationMessages"

const AddUpdateQuestion = (props) => {
    const { titleQuestion, setPopUp, initialValues, fetchData } = props
    const [questionDetails, setQuestionDetails] = useState(initialValues)
    const [selectedOptionIndex, setSelectedOptionIndex] = useState(-1);
    const options = ["optionA", "optionB", "optionC", "optionD"];
    const handleAdd = () => {
        QuestionUrl.addQuestion(questionDetails)
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
                    })
                    fetchData();
                    setPopUp(false)
                }
            }).catch(error => {
                if (error?.response?.status === 400) {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.ALL_FIELDS_REQUIRED,
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    })
                }else if (error?.response?.status === 409) {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.OPTIONS_DIFFERENT,
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    })
                }
            })
    }
    const handleUpdate = () => {
        QuestionUrl.updateQuestion(initialValues.questionId, questionDetails)
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
                    })
                    fetchData();
                    setPopUp(false)
                }
            }).catch(error => {
                if (error?.response?.status === 400) {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.ALL_FIELDS_REQUIRED,
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    })
                } else  if (error?.response?.status === 409) {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.OPTIONS_DIFFERENT,
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    })
                }
            })
    }
    const handleClick = () => {
        if (titleQuestion === "Add Question") {
            handleAdd()
        } else {
            handleUpdate()
        }
    }
    const handleChange = (e) => {
        const { name, value } = e.target
        setQuestionDetails({ ...questionDetails, [name]: value })
    }
    const handleOptionChange = (direction) => {
        let newIndex = selectedOptionIndex;
        if (direction === "down") {
            newIndex = (newIndex + 1) % options.length;
        } 
        setSelectedOptionIndex(newIndex);
        setQuestionDetails({ ...questionDetails, correctAnswer: options[newIndex] });
    }
    return (
        <div className='que-container-wrapper'>
        <div className="que-container">
            <h1 className="question-title1">{titleQuestion}</h1>
            <input className='question-input' type="text" name="questionContent" value={questionDetails?.questionContent} placeholder='Enter Question' onChange={handleChange} />
            <input className='question-input' type="text" name="optionA" value={questionDetails?.optionA} placeholder='Enter OptionA' onChange={handleChange} />
            <input className='question-input' type="text" name="optionB" value={questionDetails?.optionB} placeholder='Enter optionB' onChange={handleChange} />
            <input className='question-input' type="text" name="optionC" value={questionDetails?.optionC} placeholder='Enter optionC' onChange={handleChange} />
            <input className='question-input' type="text" name="optionD" value={questionDetails?.optionD} placeholder='Enter optionD' onChange={handleChange} />
            <div className="custom-input question-input">
                <input
                    type="text"
                    className='options-input'
                    placeholder='Click arrow to enter correct answer'
                    value={questionDetails?.correctAnswer || ""}
                    readOnly
                />
                <button className="arrow-button" onClick={() => handleOptionChange("down")}>&#9660;</button>
            </div>
            <button className='btn' onClick={handleClick}>{titleQuestion === "Add Question" ? "Add" : "Update"}</button>
            <button className='btn' onClick={() => { setPopUp(false) }}>Close</button>
        </div>
        </div>
    )
}
export default AddUpdateQuestion 