import React, { useState } from 'react'
import '../../styles/Question.scss';
import QuestionUrl from '../../service/Url'
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import Input from '../../components/input/Input'
import Button from '../../components/button/Button'
import Alert from '../../components/sweetAlert/Alert';
import HeadingOne from '../../components/heading/HeadingOne';
import Paragraph from '../../components/paragraph/Paragraph';

const AddUpdateQuestion = (props) => {
    const { titleQuestion, setPopUp, initialValues, fetchData, setIsDisable, setRenderComponent } = props
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
                    Alert.Success(sweetAlertMessages.ADD_TITILE,sweetAlertMessages.SUCCESS_ADD_MSG)
                    fetchData();
                    setPopUp(false)
                }
            }).catch(error => {
                if (error?.response?.data?.message === "Options must be different from each other") {
                    Alert.Warning(sweetAlertMessages.OPTIONS_DIFFERENT)
                }else if (error?.response?.data?.statusCode === 400){
                    Alert.Warning(error?.response?.data?.message)
                }else if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
                }else{
                    Alert.Warning(sweetAlertMessages.ALL_FIELDS_REQUIRED)
                }
            })
    }
    const handleUpdate = () => {
        QuestionUrl.updateQuestion(initialValues.questionId, questionDetails)
            .then(response => {
                if (response?.data?.statusCode === 200) {
                    Alert.Success(sweetAlertMessages.UPDATE_TITLE,sweetAlertMessages.SUCCESS_UPDATE_MSG)
                    fetchData();
                    setPopUp(false)
                    setIsDisable(false)
                }
            }).catch(error => {
                if (error?.response?.status === 409) {
                    Alert.Warning(sweetAlertMessages.OPTIONS_DIFFERENT)
                }else if (error?.response?.data?.statusCode === 400){
                    Alert.Warning(error?.response?.data?.message)
                }else if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
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
                    setErrors({ ...errors, questionContent: errorMessages.QUESTION_REQUIRED });
                }else{
                    setErrors({ ...errors, questionContent: "" });
                }
                break;
            case "optionA":
                setQuestionDetails({ ...questionDetails, optionA: value })
                if(!value){
                  setErrors({ ...errors, optionA: errorMessages.OPTIONA_REQUIRED });
                }else{
                  setErrors({ ...errors, optionA: "" });
                }
                break;
            case "optionB":
                setQuestionDetails({ ...questionDetails, optionB: value })
                if(!value){
                    setErrors({ ...errors, optionB: errorMessages.OPTIONB_REQUIRED });
                }else{
                    setErrors({ ...errors, optionB: "" });
                }
                break;
            case "optionC":
                setQuestionDetails({ ...questionDetails, optionC: value })
                if(!value){
                  setErrors({ ...errors, optionC: errorMessages.OPTIONC_REQUIRED });
                }else{
                  setErrors({ ...errors, optionC: "" });
                }
                break;
            case "optionD":
                setQuestionDetails({ ...questionDetails, optionD: value })
                if(!value){
                    setErrors({ ...errors, optionD: errorMessages.OPTIOND_REQUIRED });
                }else{
                    setErrors({ ...errors, optionD: "" });
                }
                break;
            case "correctAnswer":
                setQuestionDetails({ ...questionDetails, correctAnswer: value })
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
        let questionContentError = "", optionAError = "", optionBError = "", optionCError = "", optionDError = "";
        switch (true) {
          case questionDetails?.questionContent.length < 1:
            questionContentError = errorMessages.QUESTION_REQUIRED;
          case questionDetails?.optionA.length < 1:
            optionAError = errorMessages.OPTIONA_REQUIRED
          case questionDetails?.optionB.length < 1:
            optionBError = errorMessages.OPTIONB_REQUIRED
          case questionDetails?.optionC.length < 1:
            optionCError = errorMessages.OPTIONC_REQUIRED
          case questionDetails?.optionD.length < 1:
            optionDError = errorMessages.OPTIOND_REQUIRED
          default:
            setErrors({ ...errors, questionContent: questionContentError, optionA: optionAError, optionB: optionBError, optionC: optionCError, optionD: optionDError});
            if(errors.questionContent === "" && errors.optionA === "" && errors.optionB === "" && errors.optionC === "" && errors.optionD === "" && questionDetails?.questionContent.length > 1 && questionDetails?.optionA.length > 1
            && questionDetails?.optionB.length > 1 && questionDetails?.optionC.length > 1 && questionDetails?.optionD.length > 1){
                handleAdd();
            }
        }
    }
    return (
        <div className='question-container-wrapper'>
        <div className="question-form-container">
            <HeadingOne className="question-form-title" children={titleQuestion}/>
            <Input type="text" className='question-input' name="questionContent" value={questionDetails?.questionContent} placeholder='Enter Question' onChange={handleChange} />
            <Paragraph className='question-errors' children={errors.questionContent}/>
            <Input type="text" className='question-input' name="optionA" value={questionDetails?.optionA} placeholder='Enter OptionA' onChange={handleChange} />
            <Paragraph className='question-errors' children={errors.optionA}/>
            <Input type="text" className='question-input' name="optionB" value={questionDetails?.optionB} placeholder='Enter optionB' onChange={handleChange} />
            <Paragraph className='question-errors' children={errors.optionB}/>
            <Input type="text" className='question-input' name="optionC" value={questionDetails?.optionC} placeholder='Enter optionC' onChange={handleChange} />
            <Paragraph className='question-errors' children={errors.optionC}/>
            <Input type="text" className='question-input' name="optionD" value={questionDetails?.optionD} placeholder='Enter optionD' onChange={handleChange} />
            <Paragraph className='question-errors' children={errors.optionD}/>
            <div className="custom-input question-input">
                <Input type="text"
                    className='options-input'
                    placeholder='Click arrow to enter correct answer'
                    value={questionDetails?.correctAnswer || ""}
                    readOnly={true}
                />
                <Button className="arrow-button" onClick={() => handleOptionChange("down")}>&#9660;</Button>
            </div>
            <Button className='question-form-button' onClick={handleClick} children={titleQuestion === "Add Question" ? "Add" : "Update"}/>
            <Button className='question-form-button' onClick={() => { setPopUp(false); setIsDisable(false) }} children={"Close"}/>
        </div>
        </div>
    )
}
export default AddUpdateQuestion 