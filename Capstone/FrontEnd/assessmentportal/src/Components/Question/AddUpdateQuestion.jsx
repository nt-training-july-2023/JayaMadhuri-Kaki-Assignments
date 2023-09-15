import React, { useState } from 'react'
import './Question.scss'
import Swal from 'sweetalert2'
import QuestionUrl from '../../Services/Url'

const AddUpdateQuestion = (props) => {
    const { titleQuestion, setPopUp, initialValues, fetchData } = props
    const [questionDetails, setQuestionDetails] = useState(initialValues)
    const handleAdd = () => {
        QuestionUrl.addQuestion(questionDetails)
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
                    })
                    fetchData()
                    setPopUp(false)
                }
            }).catch(error => {
                if (error?.response?.status === 409) {
                    Swal.fire({
                        title: 'Error',
                        text: 'All Fields are Mandatory',
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "warning",
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
                        title: 'Update',
                        text: 'Successfully Updated',
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "success",
                        background: "#15172b",
                        color: "white",
                    })
                    fetchData()
                    setPopUp(false)
                }
            }).catch(error => {
                if (error?.response?.status === 409) {
                    Swal.fire({
                        title: 'Error',
                        text: 'All Fields are Mandatory',
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "warning",
                        background: "#15172b",
                        color: "white",
                    })
                } else {
                    Swal.fire({
                        title: 'Error',
                        text: 'Internal Server Error',
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "warning",
                        background: "#15172b",
                        color: "white",
                    })
                }
            })
    }
    const handleClick = () => {
        if (titleQuestion == "Add Question") {
            handleAdd()
        } else {
            handleUpdate()
        }
    }
    const handleChange = (e) => {
        const { name, value } = e.target
        setQuestionDetails({ ...questionDetails, [name]: value })
    }
    return (
        <div className="addquestion-container">
            <h1 className="question-title1">{titleQuestion}</h1>
            <input className='name' type="text" name="questionContent" value={questionDetails?.questionContent} placeholder='Enter Question' onChange={handleChange} />
            <input className='name' type="text" name="optionA" value={questionDetails?.optionA} placeholder='Enter OptionA' onChange={handleChange} />
            <input className='name' type="text" name="optionB" value={questionDetails?.optionB} placeholder='Enter optionB' onChange={handleChange} />
            <input className='name' type="text" name="optionC" value={questionDetails?.optionC} placeholder='Enter optionC' onChange={handleChange} />
            <input className='name' type="text" name="optionD" value={questionDetails?.optionD} placeholder='Enter optionD' onChange={handleChange} />
            <select className='name' name='correctAnswer' value={questionDetails?.correctAnswer} onChange={handleChange}>
                <option > -- select an option -- </option>
                <option value="optionA">Option A</option>
                <option value="optionB">Option B</option>
                <option value="optionC">Option C</option>
                <option value="optionD">Option D</option>
            </select>
            <button className='btn' onClick={handleClick}>{titleQuestion == "Add Question" ? "Add" : "Update"}</button>
            <button className='btn' onClick={() => { setPopUp(false) }}>Close</button>
        </div>
    )
}
export default AddUpdateQuestion 