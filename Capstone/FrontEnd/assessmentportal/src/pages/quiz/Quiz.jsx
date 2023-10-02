import React, { useState, useEffect } from 'react';
import '../../styles/Category.scss';
import Swal from 'sweetalert2'
import AddUpdateQuiz from './AddUpdateQuiz';
import Question from '../question/Question';
import QuestionForStudent from '../question/QuestionForStudent';
import Url from '../../services/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CardButton from '../../components/button/CardButton';
import Heading from '../../components/heading/Heading';

const Quiz = (props) => {
    const { userDetails, setShowQuiz, selectedId, setEnable, selectedName } = props;
    const showQuestion_AfterRefresh =  localStorage.getItem("Current_Quiz_SubWindow")
    const categoryId = localStorage.getItem("CategoryId")
    const categoryName = localStorage.getItem("CategoryName")
    const [quiz, setQuiz] = useState([]);
    const [details, setDetails] = useState({})
    const [title, setTitle] = useState("Add Quiz");
    const [selectedQuizId, setSelectedQuizId] = useState(null)
    const [selectedQuizName, setSelectedQuizName] = useState("")
    const [popUp, setPopUp] = useState(false);
    const [loading, setLoading] = useState(false);
    const [time, setTime] = useState(null)
    const [showQuestion, setShowQuestion] = useState(showQuestion_AfterRefresh === 'question');
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
            categoryId: selectedId ? selectedId : categoryId
        })
        setPopUp(true);
    }
    const fetchData = async () => {
        Url.getQuizByCategoryId(selectedId ? selectedId : categoryId)
            .then(response => {
                setQuiz(response?.data?.responseData);
                setLoading(true)
            }).catch(error => {
                if (error?.response?.statusCode == 400) {
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
                    });
                }
            })
    };
    const getUserDetails = async () => {
        Url.getUserByEmail(userDetails.EmailId)
            .then(response => {
                if (response?.data?.statusCode === 200) {
                    const user = response?.data?.responseData;
                    setDetails(user);
                }
            }).catch(error => {
                if (error?.response?.message === "Network Error") {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.NETWORK_ERROR,
                        timer: 2000,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    });
                }
            })
    }
    function convertMinutesToTime(minutes) {
        const hours = Math.floor(minutes / 60);
        const remainingMinutes = minutes % 60;
        const formattedHours = hours.toString().padStart(2, '0');
        const formattedMinutes = remainingMinutes.toString().padStart(2, '0');
        const formattedSeconds = '00'; 
        return `${formattedHours}:${formattedMinutes}:${formattedSeconds}`;
    }      
    useEffect(() => {
        fetchData();
        getUserDetails();
    }, []);
    return (
        <div>
            {!showQuestion &&
                <>
                    <div>
                        <Heading className="addcategory-btn" onClick={handleAdd} buttonName="Add Quiz" headingText="Quiz" userDetails={userDetails}
                            backButton={true} backButtonName="Back" backClassName={userDetails?.UserType === "Admin" ? ('backquiz-btn') : ('addcategory-btn')}
                            backOnClick={() => { 
                                setShowQuiz(false)
                                localStorage.setItem("Current_Category_SubWindow","category")
                            }}
                        />
                    </div>
                    {userDetails?.UserType === "Admin" && <div>
                        <h2 className='sub-title'>{selectedName ? selectedName : categoryName}/</h2>
                    </div>}
                </>}
            {!showQuestion ? (<>
                {loading &&
                    <div>
                        {quiz.length > 0 ? (
                            <div  className={popUp ? 'display-none' : 'category-container'}>
                                {quiz.map((item) => (
                                    <div key={item.subCategoryId} className="category-card" onClick={() => { { userDetails?.UserType === "Admin" && 
                                    setShowQuestion(true); 
                                    localStorage.setItem("Current_Quiz_SubWindow","question")
                                    localStorage.setItem("QuizId",item.subCategoryId)
                                    localStorage.setItem("QuizName",item.subCategoryName)
                                    setSelectedQuizId(item.subCategoryId); 
                                    setSelectedQuizName(item.subCategoryName);
                                     } }}>
                                        <p className='p'>Name: {item.subCategoryName}</p>
                                        <p className='p'>Description: {item.subCategoryDescription}</p>
                                        <p className='p'>Time: {item.timeLimitInMinutes} minutes</p>
                                        {userDetails?.UserType === "Admin" && <div className='button-categorycard'>
                                            <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                                event.stopPropagation();
                                                setPopUp(true);
                                                let updateInitialValues = {
                                                    subCategoryId: item?.subCategoryId,
                                                    subCategoryName: item?.subCategoryName,
                                                    subCategoryDescription: item?.subCategoryDescription,
                                                    timeLimitInMinutes: item?.timeLimitInMinutes,
                                                    categoryId: item?.categoryId
                                                };
                                                setInitialValues(updateInitialValues);
                                                setTitle("Update Quiz");
                                            }} className='category-btn category-btn1'>Update</CardButton>
                                            <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                                event.stopPropagation();
                                                Swal.fire({
                                                    text: 'do you really want to delete?',
                                                    icon: "warning",
                                                    background: "#15172b",
                                                    color: "white",
                                                    showCancelButton: true
                                                }).then(function (result) {
                                                    if (result.value === true) {
                                                        Url.deleteQuiz(item.subCategoryId)
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
                                                    }
                                                })
                                            }} className='category-btn category-btn2'>Delete</CardButton>
                                        </div>}
                                        {userDetails?.UserType === "Student" && <button onMouseDown={event => event.stopPropagation()}
                                            className='category-btn start-test-btn' onClick={(event) => {
                                                localStorage.setItem("selectedOption","{}");
                                                localStorage.setItem("attemptedQuestions",0);
                                                localStorage.setItem("prevSelectedOption","");
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
                                                        const formattedTime = convertMinutesToTime(item.timeLimitInMinutes)
                                                        setTime(formattedTime)
                                                        localStorage.setItem("details",JSON.stringify(details))
                                                    }else{
                                                        localStorage.setItem("Current_Quiz_SubWindow","")
                                                    }
                                                })
                                            }} >Start Test</button>}
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <h2 className='h2-no-list'>No Quizes</h2>
                        )}
                        {popUp && (
                            <AddUpdateQuiz title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} />
                        )}
                    </div>}
            </>
            ) : (
                <>{userDetails?.UserType === "Admin" ? (<Question selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} selectedQuizName={selectedQuizName} selectedName={selectedName} />) : (<QuestionForStudent selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} time={time} details={details} selectedId={categoryId} setEnable={setEnable}/>)}</>
            )}
        </div>
    )
}
export default Quiz;