import React, { useState, useEffect } from 'react';
import './Quiz.scss'
import Swal from 'sweetalert2'
import AddUpdateQuiz from './AddUpdateQuiz';
import Question from '../Question/Question';
import QuestionForStudent from '../Question/QuestionForStudent';
import QuizUrl from '../../Urls/Url';
import ResultUrl from '../../Urls/Url';
import UsersUrl from '../../Urls/Url';

const Quiz = (props) => {
    const { userDetails, setShowQuiz, selectedId, setEnable } = props;
    const [quiz, setQuiz] = useState([]);
    const [details, setDetails] = useState({})
    const [title, setTitle] = useState("Add Quiz");
    const [selectedQuizId, setSelectedQuizId] = useState(null)
    const [popUp, setPopUp] = useState(false);
    const [time, setTime] = useState(null)
    const [showQuestion, setShowQuestion] = useState(false);
    const [initialValues, setInitialValues] = useState({
        subCategoryName: "",
        subCategoryDescription: "",
        timeLimitInMinutes: "",
        categoryId: "",
    })
    const handleAdd = () => {
        setTitle("Add Quiz");
        setInitialValues({
            subCategoryName: "",
            subCategoryDescription: "",
            timeLimitInMinutes: "",
            categoryId: selectedId
        })
        setPopUp(true);
    }
    const fetchData = async () => {
        QuizUrl.getQuizByCategoryId(selectedId)
            .then(response => {
                setQuiz(response?.data?.SubCategoryByCategoryId);
            }).catch(error => {
                if (error?.response?.statusCode == 400) {
                    Swal.fire({
                        title: 'Error',
                        text: 'Error in getting Quiz List',
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "warning",
                        background: "#15172b",
                        color: "white",
                    });
                }
            })
    };
    const isAttempted = async (QuizId) => {
        ResultUrl.checkIsAttempted(details.userId, QuizId)
            .then(response => {
                if (response?.data?.status === true) {
                    Swal.fire({
                        title: 'Already Attempted',
                        text: 'User can attempt quiz only once',
                        timer: 1500,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "warning",
                        background: "#15172b",
                        color: "white",
                    })
                }
            }).catch(error => {
                console.log(error)
            })
    }
    const getUserDetails = async () => {
        UsersUrl.getUserByEmail(userDetails.EmailId)
            .then(response => {
                if (response?.data?.statusCode === 200) {
                    const user = response?.data?.StudentDetails;
                    setDetails(user);
                }
            }).catch(error => {
                if (error?.response?.message === "Network Error") {
                    Swal.fire({
                        title: 'Erro',
                        text: 'NetWork Error',
                        timer: 2000,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: "warning",
                        background: "#15172b",
                        color: "white",
                    });
                }
            })
    }
    useEffect(() => {
        fetchData();
        getUserDetails();
    }, []);
    return (
        <div>
            {!showQuestion &&
                <div>
                    {userDetails?.UserType === "Admin" && <button className='addquiz-btn' onClick={handleAdd}>Add Quiz</button>}
                    <button className={userDetails?.UserType === "Admin" ? ('backquiz-btn') : ('addquiz-btn')} onClick={() => { setShowQuiz(false) }}>Back</button>
                    <h1 className='category-title'>Quiz</h1>
                    <hr />
                </div>}
            {!showQuestion ? (<>
                {quiz.length > 0 ? (
                    <div className="quiz-container">
                        {quiz.map((item) => (
                            <div key={item.subCategoryId} className={userDetails?.UserType === "Admin" ? ("quiz-card") : ("quiz-card1")} onClick={() => { { userDetails?.UserType === "Admin" && setShowQuestion(true); setSelectedQuizId(item.subCategoryId); } }}>
                                <p className='p'>Name: {item.subCategoryName}</p>
                                <p className='p'>Description: {item.subCategoryDescription}</p>
                                <p className='p'>Time(In Minutes): {item.timeLimitInMinutes}</p>
                                {userDetails?.UserType === "Admin" && <div>
                                    <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                        event.stopPropagation();
                                        setPopUp(true);
                                        let updateInitialValues = { subCategoryId: item?.subCategoryId, subCategoryName: item?.subCategoryName, subCategoryDescription: item?.subCategoryDescription, timeLimitInMinutes: item?.timeLimitInMinutes, categoryId: item?.categoryId };
                                        setInitialValues(updateInitialValues);
                                        setTitle("Update Quiz");
                                    }} className='quiz-btn'>Update</button>
                                    <button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                        event.stopPropagation();
                                        QuizUrl.deleteQuiz(item.subCategoryId)
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
                                                    });
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
                                                    });
                                                }
                                            })
                                    }} className='quiz-btn'>Delete</button>
                                </div>}
                                {userDetails?.UserType === "Student" && <button onMouseDown={event => event.stopPropagation()}
                                    className='quiz-btn' onClick={(event) => {
                                        isAttempted(item.subCategoryId);
                                        Swal.fire({
                                            title: 'Instructions:',
                                            html: '<div style="text-align:left">*Once, test started user should not leave the quiz without submit. If not submitted results will not be stored<br>*Each question carries one mark.<br>*Do not Refresh the page<div>',
                                            showConfirmButton: true,
                                            icon: "info",
                                            showCancelButton: true,
                                            background: "#15172b",
                                            color: "white",
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                event.stopPropagation();
                                                setShowQuestion(true);
                                                setSelectedQuizId(item.subCategoryId);
                                                let timer = new Date();
                                                const time_min = item.timeLimitInMinutes * 1;
                                                timer.setMinutes(timer.getMinutes() + time_min);
                                                setTime(timer)
                                            }
                                        })
                                    }} >Start Test</button>}
                            </div>
                        ))}
                    </div>
                ) : (
                    <h2 style={{ textAlign: "center", color: "#31334e" }}>No Quizes</h2>
                )}
                {popUp && (
                    <AddUpdateQuiz title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} />
                )}
            </>
            ) : (
                <>{userDetails?.UserType === "Admin" ? (<Question selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} />) : (<QuestionForStudent selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} time={time} details={details} selectedId={selectedId} setEnable={setEnable} />)}</>
            )}
        </div>
    )
}

export default Quiz;