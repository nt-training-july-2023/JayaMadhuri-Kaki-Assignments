import React, { useState } from 'react'
import '../../styles/Question.scss';
import Swal from 'sweetalert2'
import QuestionUrl from '../../services/Url'
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import TextInput from '../../components/input/TextInput'
import FormButton from '../../components/button/FormButton'

const AddUpdateQuestion = (props) => {
    const { titleQuestion, setPopUp, initialValues, fetchData } = props
    const [questionDetails, setQuestionDetails] = useState(initialValues)
    const [selectedOptionIndex, setSelectedOptionIndex] = useState(-1);
    const initialErrors = {
        questionContent: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        correctAnswer: ""
      }
    const [errors, setErrors] = useState(initialErrors);
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
                if (error?.response?.status === 409) {
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
                }else{
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.SOMETHING_WENT_WRONG,
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
            handleValidateInputs();
        } else {
            handleUpdate()
        }
    }
    const handleChange = (e) => {
        const { name, value } = e.target
        switch(name) {
            case "questionContent":
                setQuestionDetails({ ...questionDetails, questionContent: value })
                if(!value){
                    setErrors({ ...errors, questionContent: "Question is Required" });
                }else{
                    setErrors({ ...errors, questionContent: "" });
                }
                break;
            case "optionA":
                setQuestionDetails({ ...questionDetails, optionA: value })
                if(!value){
                  setErrors({ ...errors, optionA: "OptionA is Required" });
                }else{
                  setErrors({ ...errors, optionA: "" });
                }
                break;
            case "optionB":
                setQuestionDetails({ ...questionDetails, optionB: value })
                if(!value){
                    setErrors({ ...errors, optionB: "optionB is Required" });
                }else{
                    setErrors({ ...errors, optionB: "" });
                }
                break;
            case "optionC":
                setQuestionDetails({ ...questionDetails, optionC: value })
                if(!value){
                  setErrors({ ...errors, optionC: "optionC is Required" });
                }else{
                  setErrors({ ...errors, optionC: "" });
                }
                break;
            case "optionD":
                setQuestionDetails({ ...questionDetails, optionD: value })
                if(!value){
                    setErrors({ ...errors, optionD: "optionD is Required" });
                }else{
                    setErrors({ ...errors, optionD: "" });
                }
                break;
            case "correctAnswer":
                setQuestionDetails({ ...questionDetails, correctAnswer: value })
                if(!value){
                  setErrors({ ...errors, correctAnswer: "correctAnswer is Required" });
                }else{
                  setErrors({ ...errors, correctAnswer: "" });
                }
                break;
            default:
                setQuestionDetails({ ...questionDetails, [name]: value })
                break;
        }
    };
    const handleOptionChange = (direction) => {
        let newIndex = selectedOptionIndex;
        if (direction === "down") {
            newIndex = (newIndex + 1) % options.length;
        } 
        setSelectedOptionIndex(newIndex);
        setQuestionDetails({ ...questionDetails, correctAnswer: options[newIndex] });
    }
    const handleValidateInputs = () => {
        let questionContentError = "", optionAError = "", optionBError = "", optionCError = "", optionDError = "", correctAnswerError = "";
        switch (true) {
          case questionDetails?.questionContent.length < 1:
            questionContentError = "Question is required";
          case questionDetails?.optionA.length < 1:
            optionAError = "Option A is required"
          case questionDetails?.optionB.length < 1:
            optionBError = "Option B is required"
          case questionDetails?.optionC.length < 1:
            optionCError = "Option C is required"
          case questionDetails?.optionD.length < 1:
            optionDError = "Option D is required"
          case questionDetails?.correctAnswer.length < 1:
            correctAnswerError = "Correct Answer is required"
          default:
            setErrors({ ...errors, questionContent: questionContentError, optionA: optionAError, optionB: optionBError, optionC: optionCError, optionD: optionDError, correctAnswer: correctAnswerError });
            if(errors.questionContent === "" && errors.optionA === "" && errors.optionB === "" && errors.optionC === "" && errors.optionD === "" && errors.correctAnswer === "" && questionDetails?.questionContent.length > 1){
                handleAdd();
            }
        }
    }
    return (
        <div className='que-container-wrapper'>
        <div className="que-container">
            <h1 className="question-title1">{titleQuestion}</h1>
            <TextInput className='question-input' name="questionContent" value={questionDetails?.questionContent} placeholder='Enter Question' onChange={handleChange} />
            <p className='err'>{errors.questionContent}</p>
            <TextInput className='question-input' name="optionA" value={questionDetails?.optionA} placeholder='Enter OptionA' onChange={handleChange} />
            <p className='err'>{errors.optionA}</p>
            <TextInput className='question-input' name="optionB" value={questionDetails?.optionB} placeholder='Enter optionB' onChange={handleChange} />
            <p className='err'>{errors.optionB}</p>
            <TextInput className='question-input' name="optionC" value={questionDetails?.optionC} placeholder='Enter optionC' onChange={handleChange} />
            <p className='err'>{errors.optionC}</p>
            <TextInput className='question-input' name="optionD" value={questionDetails?.optionD} placeholder='Enter optionD' onChange={handleChange} />
            <p className='err'>{errors.optionD}</p>
            <div className="custom-input question-input">
                <TextInput
                    className='options-input'
                    placeholder='Click arrow to enter correct answer'
                    value={questionDetails?.correctAnswer || ""}
                    readOnly={true}
                />
                <button className="arrow-button" onClick={() => handleOptionChange("down")}>&#9660;</button>
            </div>
            <p className='err'>{errors.correctAnswer}</p>
            <FormButton className='btn' onClick={handleClick}>{titleQuestion === "Add Question" ? "Add" : "Update"}</FormButton>
            <FormButton className='btn' onClick={() => { setPopUp(false) }}>Close</FormButton>
        </div>
        </div>
    )
}
export default AddUpdateQuestion 