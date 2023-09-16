import React, { useState, useEffect } from 'react'
import '../Styles/Question.scss'
import Swal from 'sweetalert2'
import AddUpdateQuestion from './AddUpdateQuestion'
import QuestionUrl from '../../Services/Url'

const Question = (props) => {
    const { selectedQuizId, setShowQuestion } = props
    const [question, setQuestion] = useState([])
    const [titleQuestion, setTitleQuestion] = useState("Add Question")
    const [popUp, setPopUp] = useState(false)
    const [initialValues, setInitialValues] = useState({
        questionContent: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        correctAnswer: "",
        subCategoryId: selectedQuizId
    })
    const handleAdd = () => {
        setTitleQuestion("Add Question")
        setInitialValues({
            questionContent: "",
            optionA: "",
            optionB: "",
            optionC: "",
            optionD: "",
            correctAnswer: "",
            subCategoryId: selectedQuizId
        })
        setPopUp(true)
    }
    const fetchData = async () => {
        QuestionUrl.getAllQuestionsByQuizId(selectedQuizId)
            .then(response => {
                setQuestion(response?.data?.QuestionBySubCategoryId)
            }).catch(error => {
                Swal.fire({
                    title: 'Error',
                    text: 'Error in getting Questions List',
                    timer: 1500,
                    showConfirmButton: false,
                    showCancelButton: false,
                    icon: "warning",
                    background: "#15172b",
                    color: "white",
                })
            })
    }
    useEffect(() => {
        fetchData()
    }, [])
    return (
        <div>
            <div>
                <button className='addquestion-btn' onClick={handleAdd}>Add Question</button>
                <button className='backquestion-btn' onClick={() => { setShowQuestion(false) }}>Back</button>
                <h1 className='category-title'>Questions</h1>
                <hr />
            </div>
            {question.length > 0 ? (
                <div className="question-container">
                    <table class="table table-responsive">
                        <tr>
                            <th>Question</th>
                            <th>OptionA</th>
                            <th>OptionB</th>
                            <th>OptionC</th>
                            <th>OptionD</th>
                            <th>Correct Answer</th>
                            <th>Actions</th>
                        </tr>
                        <tbody>
                            {question.map((item) => (
                                <tr key={item.questionId}>
                                    <td>{item.questionContent}</td>
                                    <td>{item.optionA}</td>
                                    <td>{item.optionB}</td>
                                    <td>{item.optionC}</td>
                                    <td>{item.optionD}</td>
                                    <td>{item.correctAnswer}</td>
                                    <td>
                                        <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                            setPopUp(true)
                                            let updateInitialValues = { questionId: item?.questionId,
                                                questionContent: item?.questionContent,
                                                optionA: item?.optionA,
                                                optionB: item?.optionB,
                                                optionC: item?.optionC,
                                                optionD: item?.optionD,
                                                correctAnswer: item?.correctAnswer,
                                                subCategoryId: item?.subCategoryId }
                                            setInitialValues(updateInitialValues)
                                            setTitleQuestion("Update Question")
                                            event.stopPropagation()
                                        }} className='category-btn'>Update</button>
                                        <button onMouseDown={event => event.stopPropagation()}
                                            onClick={(event) => {
                                                event.stopPropagation()
                                                QuestionUrl.deleteQuestion(item.questionId)
                                                    .then(response => {
                                                        if (response?.data?.statusCode == 200) {
                                                            Swal.fire({
                                                                title: 'Delete',
                                                                text: 'Successfully Deleted',
                                                                timer: 1000,
                                                                showConfirmButton: false,
                                                                showCancelButton: false,
                                                                icon: "success",
                                                                background: "#15172b",
                                                                color: "white",
                                                            })
                                                            fetchData()
                                                        }
                                                    }).catch(error => {
                                                        if (error?.response?.status == "404") {
                                                            Swal.fire({
                                                                title: 'Delete',
                                                                text: 'ID Not Found',
                                                                timer: 1000,
                                                                showConfirmButton: false,
                                                                showCancelButton: false,
                                                                icon: "warning",
                                                                background: "#15172b",
                                                                color: "white",
                                                            })
                                                        }
                                                    })
                                            }} className='category-btn'>Delete</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            ) : (
                <h2 style={{ textAlign: "center", color: "#31334e", marginTop:"200px" }}>No Questions</h2>
            )}
            <>
                {popUp && (
                    <AddUpdateQuestion titleQuestion={titleQuestion} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} />
                )}
            </>
        </div>
    )
}
export default Question 