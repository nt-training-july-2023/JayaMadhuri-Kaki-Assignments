import React, { useState, useEffect } from 'react';
import Timer from '../timer/Timer';
import '../../styles/Question.scss';
import Swal from 'sweetalert2'
import Url from '../../services/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import FormButton from '../../components/button/FormButton';
import Heading from '../../components/heading/Heading';

const QuestionForStudent = (props) => {
    const { selectedQuizId, setShowQuestion, time, details, selectedId, setEnable} = props;
    const quizId = localStorage.getItem("QuizId")
    const details_AfterRefresh = JSON.parse(localStorage.getItem("details"))
    const attemptedQuestions_AfterRefresh = localStorage.getItem("attemptedQuestions")
    const prevSelectedOption_AfterRefresh = localStorage.getItem("prevSelectedOption")
    const selectedOption_AfterRefresh = JSON.parse(localStorage.getItem("selectedOption"))
    const question_AfterRefresh = localStorage.getItem("question") ? JSON.parse(atob(localStorage.getItem("question"))) : []
    const [selectedOption, setSelectedOption] = useState(selectedOption_AfterRefresh);
    const [question, setQuestion] = useState(question_AfterRefresh);
    const [prevSelectedOption, setPrevSelectedOption] = useState(prevSelectedOption_AfterRefresh);
    const [attemptedQuestions, setAttemptedQuestions] = useState(Number(attemptedQuestions_AfterRefresh))
    const [isRunning, setIsRunning] = useState(true);
    let count = 1;
    const fetchData = async () => {
        Url.getQuestionsByQuizId(selectedQuizId ? selectedQuizId : quizId)
            .then(response => {
                setQuestion(response?.data?.responseData);
                localStorage.setItem('question', btoa(JSON.stringify(response?.data?.responseData)));
            }).catch(error => {
                Swal.fire({
                    title: sweetAlertMessages.ERROR,
                    text: sweetAlertMessages.ERROR_GETTING_LIST,
                    timer: 1500,
                    showConfirmButton: false,
                    showCancelButton: false,
                    icon: sweetAlertMessages.WARNING,
                    background: "#15172b",
                    color: "white",
                });
            })
    };
    const handleAnswerClick = (questionId, optionValue) => {
        if (!selectedOption.hasOwnProperty(questionId) || selectedOption[questionId] !== optionValue) {
            setSelectedOption({
                ...selectedOption,
                [questionId]: optionValue,
            });
        }
        setPrevSelectedOption(optionValue);
    };
    const handleSubmit = () =>{
        if (attemptedQuestions > 0) {
            checkAnswers();
        }else{
            Swal.fire({
                title: sweetAlertMessages.ATTEMPT_QUIZ,
                timer: 2000,
                showConfirmButton: false,
                showCancelButton: false,
                icon: sweetAlertMessages.WARNING,
                background: '#15172b',
                color: 'white',
            })
        }
    }
    const checkAnswers = () => {
        const score = question.reduce((acc, item) => {
            const questionId = item.questionId;
            const selectedOptionValue = selectedOption[questionId];
            if (selectedOptionValue === item.correctAnswer) {
                return acc + 1;
            }
            return acc;
        }, 0);
        const payload = {
            studentId: details?.userId ? details?.userId : details_AfterRefresh?.userId,
            subCategoryId: selectedQuizId ? selectedQuizId : quizId,
            categoryId: selectedId,
            marksObtained: score,
            totalMarks: question?.length,
            totalQuestions: question?.length,
            numOfAttemptedQuestions: attemptedQuestions
        }
        Swal.fire({
            title: sweetAlertMessages.SUBMITTED_SUCCESS,
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.INFO,
            background: '#15172b',
            color: 'white',
        })
        handleResults(payload);
        setTimeout(function () {
            setEnable(false)
            setShowQuestion(false)
            localStorage.setItem('reloadCount', '');
            localStorage.setItem("time","")
            localStorage.setItem("attemptedQuestions",0);
            localStorage.setItem("Current_Quiz_SubWindow","")
            localStorage.setItem("selectedOption","{}");
            localStorage.setItem("prevSelectedOption","");
        }, 2000);
    };
    const handleResults = (results) => {
    Url.addResults(results)
        .then(response => {
        }).catch(error => {
            Swal.fire({
                title: sweetAlertMessages.ERROR_SUBMISSION,
                text: sweetAlertMessages.PLEASE_ATTEMPT_QUIZ,
                timer: 2000,
                showConfirmButton: false,
                showCancelButton: false,
                icon: sweetAlertMessages.WARNING,
                background: '#15172b',
                color: 'white',
            })
            setTimeout(function () {
                setEnable(false)
                setShowQuestion(false)
                localStorage.setItem('reloadCount', '');
                localStorage.setItem("time","")
                localStorage.setItem("attemptedQuestions",0);
                localStorage.setItem("Current_Quiz_SubWindow","")
                localStorage.setItem("selectedOption","{}");
                localStorage.setItem("prevSelectedOption","");
            }, 1500);
        })
    }
    const incrementReloadCount = () => {
        const currentReloadCount = localStorage.getItem('reloadCount');
        const newReloadCount = currentReloadCount ? parseInt(currentReloadCount, 10) + 1 : 1;
        localStorage.setItem('reloadCount', newReloadCount.toString());
    };
    useEffect(() => {
        fetchData();
        const reloadCount = localStorage.getItem('reloadCount');
        if (!reloadCount) {
        localStorage.setItem('reloadCount', '1');
        }
        if (reloadCount) {
        incrementReloadCount();
        setIsRunning(false)
        Swal.fire({
            text: sweetAlertMessages.RELOAD,
            showConfirmButton: true,
            showCancelButton: true,
            icon: sweetAlertMessages.INFO,
            background: '#15172b',
            color: 'white',
        }).then(function (result) {
            if (result.value === true) {
                setShowQuestion(false)
                setEnable(false)
                localStorage.setItem("Current_Quiz_SubWindow","")
                localStorage.setItem('reloadCount', '');
                localStorage.setItem("attemptedQuestions",0);
                localStorage.setItem("time","")
                localStorage.setItem("prevSelectedOption","");
                checkAnswers();
            }else{
                setIsRunning(true)
            }
        })
        }
    }, []);
    useEffect(()=>{
        if(Object.keys(selectedOption).length > 0){
            localStorage.setItem("selectedOption",JSON.stringify(selectedOption));
            localStorage.setItem("attemptedQuestions",attemptedQuestions);
            localStorage.setItem("prevSelectedOption",prevSelectedOption);
            setAttemptedQuestions(Object.keys(selectedOption).length);
        }
    },[selectedOption])
    return (
        <div>
            <div>
                <Heading 
                    backButton={true} backButtonName="Back" backClassName="addquestion-btn"
                    backOnClick={() => { setShowQuestion(false); localStorage.setItem('reloadCount', '');}}
                    question={question}
                    headingText="Questions"
                />
            </div>
            {question.length > 0 ? (
                <>
                    {setEnable(true)}
                    <div className='timer'>
                        <Timer expiryTimestamp={time ? time : localStorage.getItem("time")} checkAnswers={checkAnswers} setEnable={setEnable} isRunning={isRunning}/>
                    </div>
                    <div className='question-body'>
                        <div className='card'>
                            <div className='student-question-container'>
                            <h5>Attempted Questions:-{attemptedQuestions+"/"+question.length}</h5>
                                {question.map((item) => (<>
                                    <div key={item.questionId}>
                                        <h3>{count++}{". "}{item.questionContent}</h3>
                                        <div className='answer-options'>
                                            <button
                                                className={`answer-button ${selectedOption[item.questionId] === 'optionA' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionA')}
                                            >
                                                {item.optionA}
                                            </button>
                                            <button
                                                className={`answer-button ${selectedOption[item.questionId] === 'optionB' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionB')}
                                            >
                                                {item.optionB}
                                            </button>
                                            <button
                                                className={`answer-button ${selectedOption[item.questionId] === 'optionC' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionC')}
                                            >
                                                {item.optionC}
                                            </button>
                                            <button
                                                className={`answer-button ${selectedOption[item.questionId] === 'optionD' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionD')}
                                            >
                                                {item.optionD}
                                            </button>
                                        </div>
                                    </div>
                                </>))}
                            </div>
                            <div className="submit-btn">
                                <button className="submit-quiz" onClick={handleSubmit}>
                                    Submit Quiz
                                </button>
                            </div>
                        </div>
                    </div>
                </>
            ) : (
                <h2 className='h2-no-list'>No Questions</h2>
            )}
        </div>
    )
}
export default QuestionForStudent