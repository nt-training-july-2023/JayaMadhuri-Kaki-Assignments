import React, { useState, useEffect } from 'react'
import '../../styles/Question.scss';
import Swal from 'sweetalert2'
import AddUpdateQuestion from './AddUpdateQuestion'
import QuestionUrl from '../../services/Url'
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CommonTable from '../../components/table/CommonTable';
import Heading from '../../components/heading/Heading';

const Question = (props) => {
    const { selectedQuizId, setShowQuestion, selectedQuizName, selectedName } = props
    const categoryName = localStorage.getItem("CategoryName")
    const QuizName = localStorage.getItem("QuizName")
    const QuizId = localStorage.getItem("QuizId")
    const [question, setQuestion] = useState([])
    const [titleQuestion, setTitleQuestion] = useState("Add Question")
    const [popUp, setPopUp] = useState(false)
    const [loading, setLoading] = useState(false);
    const userDetails = {
        UserType : "Admin"
    }
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
            subCategoryId: selectedQuizId ? selectedQuizId : QuizId
        })
        setPopUp(true)
    }
    const fetchData = async () => {
        QuestionUrl.getQuestionsByQuizId(selectedQuizId ? selectedQuizId : QuizId)
            .then(response => {
                setQuestion(response?.data?.responseData)
                setLoading(true)
            }).catch(error => {
                setLoading(true)
                Swal.fire({
                    title: sweetAlertMessages.ERROR,
                    text: sweetAlertMessages.ERROR_GETTING_LIST,
                    timer: 1500,
                    showConfirmButton: false,
                    showCancelButton: false,
                    icon: sweetAlertMessages.WARNING,
                    background: "#15172b",
                    color: "white",
                })
            })
    }
    useEffect(() => {
        fetchData()
    }, []);
    const rows = [
        'questionContent',
        'optionA',
        'optionB',
        'optionC',
        'optionD',
        'correctAnswer'
    ];
    const columns = [
        'Question Content',
        'Option A',
        'Option B',
        'Option C',
        'Option D',
        'Correct Answer'
    ];
    return (
        <div>
            <div>
                <Heading className="addquestion-btn" onClick={handleAdd} buttonName="Add Question" headingText="Questions" userDetails={userDetails}
                    backButton={true} backButtonName="Back" backClassName="backquestion-btn"
                    backOnClick={() => { 
                        localStorage.setItem("Current_Quiz_SubWindow","")
                        setShowQuestion(false) 
                    }}
                />
            </div>
            <div>
                <h2 className='sub-title'>{selectedName ? selectedName : categoryName}/{selectedQuizName ? selectedQuizName : QuizName}</h2>
            </div>
            {loading &&
                <div className={popUp && 'display-none'}>
                    {question.length > 0 ? (
                        <div className="question-container">
                            <CommonTable columns={columns} data={question.reverse()} rows={rows}/>
                        </div>
                    ) : (
                        <h2 className='h2-no-list'>No Questions</h2>
                    )}
                </div>
            }
            <>
                {popUp && (
                    <AddUpdateQuestion titleQuestion={titleQuestion} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} />
                )}
            </>
        </div>
    )
}
export default Question 