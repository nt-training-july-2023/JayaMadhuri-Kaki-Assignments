import React, { useState, useEffect } from 'react';
import Timer from '../timer/Timer';
import '../../styles/Question.scss';
import Url from '../../service/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import Heading from '../../components/heading/Heading';
import Alert from '../../components/sweetAlert/Alert';
import HeadingFive from '../../components/heading/HeadingFive';
import HeadingThree from '../../components/heading/HeadingThree';
import Button from '../../components/button/Button';
import HeadingTwo from '../../components/heading/HeadingTwo';

const QuestionForStudent = (props) => {
    const { selectedQuizId, setShowQuestion, time, details, setEnable, setRenderComponent} = props;
    const quizId = localStorage.getItem("QuizId")
    const details_AfterRefresh = JSON.parse(localStorage.getItem("details"))
    const categoryName = localStorage.getItem("CategoryName");
    const quizName = localStorage.getItem("QuizName");
    const attemptedQuestions_AfterRefresh = localStorage.getItem("attemptedQuestions")
    const selectedOption_AfterRefresh = JSON.parse(localStorage.getItem("selectedOption"))
    const question_AfterRefresh = localStorage.getItem("question") ? JSON.parse(atob(localStorage.getItem("question"))) : []
    const [selectedOption, setSelectedOption] = useState(selectedOption_AfterRefresh);
    const [question, setQuestion] = useState(question_AfterRefresh);
    const [attemptedQuestions, setAttemptedQuestions] = useState(Number(attemptedQuestions_AfterRefresh))
    const [isRunning, setIsRunning] = useState(true);
    let count = 1;
    const fetchData = async () => {
        Url.getQuestionsByQuizId(selectedQuizId ? selectedQuizId : quizId)
            .then(response => {
                setQuestion(response?.data?.responseData);
                localStorage.setItem('question', btoa(JSON.stringify(response?.data?.responseData)));
            }).catch(error => {
                if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
                }else{
                    Alert.Warning(sweetAlertMessages.ERROR_GETTING_LIST)
                }
            })
    };
    const handleAnswerClick = (questionId, optionValue) => {
        if (!selectedOption.hasOwnProperty(questionId) || selectedOption[questionId] !== optionValue) {
            setSelectedOption({
                ...selectedOption,
                [questionId]: optionValue,
            });
        }
    };
    const handleSubmit = () =>{
        if (attemptedQuestions > 0) {
            checkAnswers();
        }else{
            Alert.Warning(sweetAlertMessages.ATTEMPT_QUIZ)
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
            studentEmailId: details?.emailId ? details?.emailId : details_AfterRefresh?.emailId,
            studentName: details?.firstName+ " " +details?.lastName ? details?.firstName+ " " +details?.lastName : details_AfterRefresh?.firstName+ " " +details?.lastName,
            quizName: quizName,
            categoryName: categoryName,
            marksObtained: score,
            totalMarks: question?.length,
            totalQuestions: question?.length,
            numOfAttemptedQuestions: attemptedQuestions
        }
        Alert.Success(sweetAlertMessages.SUBMITTED_SUCCESS)
        handleResults(payload);
        setTimeout(function () {
            setEnable(false)
            setShowQuestion(false)
            localStorage.setItem('reloadCount', '');
            localStorage.setItem("time","")
            localStorage.setItem("attemptedQuestions","");
            localStorage.setItem("Current_Quiz_SubWindow","")
            localStorage.setItem("selectedOption","{}");
        }, 2000);
    };
    const handleResults = (results) => {
    Url.addResults(results)
        .then(response => {
        }).catch(error => {
            if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                Alert.NetworkError(setRenderComponent)
            }else{
                Alert.Warning(sweetAlertMessages.PLEASE_ATTEMPT_QUIZ)
                setTimeout(function () {
                    setEnable(false)
                    setShowQuestion(false)
                    localStorage.setItem('reloadCount', '');
                    localStorage.setItem("time","")
                    localStorage.setItem("attemptedQuestions","");
                    localStorage.setItem("Current_Quiz_SubWindow","")
                    localStorage.setItem("selectedOption","{}");
                }, 1500);
            }
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
        {question.length > 0 && Alert.Info(setShowQuestion,setEnable,checkAnswers,setIsRunning)}
        }
    }, []);
    useEffect(()=>{
        if(Object.keys(selectedOption).length > 0){
            localStorage.setItem("selectedOption",JSON.stringify(selectedOption));
            localStorage.setItem("attemptedQuestions",Object.keys(selectedOption).length);
            setAttemptedQuestions(Object.keys(selectedOption).length);
        }
    },[selectedOption])
    return (
        <div>
            <div>
            {question.length === 0 &&<Heading 
                    backButton={true} backButtonName="Back" backClassName="addquestion-button"
                    backOnClick={() => { setShowQuestion(false); localStorage.setItem('reloadCount', ''); localStorage.setItem('Current_Quiz_SubWindow','quiz')}}
                    question={question}
                    headingText="Questions"
                />}
            </div>
            {question.length > 0 ? (
                <>
                    {setEnable(true)}
                    <div className='timer'>
                        <Timer expiryTimestamp={time ? time : localStorage.getItem("time")} checkAnswers={checkAnswers} setEnable={setEnable} isRunning={isRunning}/>
                    </div>
                    <div className='question-body'>
                        <div className='test-card'>
                            <div className='student-question-container'>
                            <HeadingFive>Attempted Questions:-{attemptedQuestions+"/"+question.length}</HeadingFive>
                                {question.map((item) => (<>
                                    <div key={item.questionId}>
                                        <HeadingThree>{count++}{". "}{item.questionContent}</HeadingThree>
                                        <div className='test-options'>
                                            <Button
                                                className={`options-button ${selectedOption[item.questionId] === 'optionA' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionA')}
                                                children={item.optionA}
                                            />
                                            <Button
                                                className={`options-button ${selectedOption[item.questionId] === 'optionB' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionB')}
                                                children={item.optionB}
                                            />
                                            <Button
                                                className={`options-button ${selectedOption[item.questionId] === 'optionC' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionC')}
                                                children={item.optionC}
                                            />
                                            <Button
                                                className={`options-button ${selectedOption[item.questionId] === 'optionD' ? 'selected' : ''
                                                    }`}
                                                onClick={() => handleAnswerClick(item.questionId, 'optionD')}
                                                children= {item.optionD}
                                            />
                                        </div>
                                    </div>
                                </>))}
                            </div>
                            <div className="submit-button-div">
                                <Button className="submit-quiz-button" onClick={handleSubmit} children="Submit"/>
                            </div>
                        </div>
                    </div>
                </>
            ) : (<>
                    {setEnable(false)}
                    <HeadingTwo className='h2-no-list' children="No Questions"/>
                </>
            )}
        </div>
    )
}
export default QuestionForStudent