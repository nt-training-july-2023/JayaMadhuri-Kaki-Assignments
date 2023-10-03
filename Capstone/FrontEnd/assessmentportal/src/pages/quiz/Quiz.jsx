import React, { useState, useEffect } from 'react';
import '../../styles/Category.scss';
import AddUpdateQuiz from './AddUpdateQuiz';
import Question from '../question/Question';
import QuestionForStudent from '../question/QuestionForStudent';
import Url from '../../service/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CardButton from '../../components/button/CardButton';
import Heading from '../../components/heading/Heading';
import Warning from '../../components/sweetAlert/Warning';
import Delete from '../../components/sweetAlert/Delete';
import Instructions from '../../components/sweetAlert/Instructions';

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
                    Warning.render(sweetAlertMessages.ERROR_GETTING_LIST)
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
                    Warning.render(sweetAlertMessages.NETWORK_ERROR)
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
                        <Heading className="addcategory-button" onClick={handleAdd} buttonName="Add Quiz" headingText="Quiz" userDetails={userDetails}
                            backButton={true} backButtonName="Back" backClassName={userDetails?.UserType === "Admin" ? ('backquiz-button') : ('addcategory-button')}
                            backOnClick={() => { 
                                setShowQuiz(false)
                                localStorage.setItem("Current_Category_SubWindow","category")
                            }}
                        />
                    </div>
                    {userDetails?.UserType === "Admin" && <div>
                        <h2 className='sub-heading-title'>{selectedName ? selectedName : categoryName}/</h2>
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
                                        <p>Name: {item.subCategoryName}</p>
                                        <p>Description: {item.subCategoryDescription}</p>
                                        <p>Time: {item.timeLimitInMinutes} minutes</p>
                                        {userDetails?.UserType === "Admin" && <div className='categorycard-buttons-div'>
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
                                            }} className='categorycard-button categorycard-button-update'>Update</CardButton>
                                            <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                                                event.stopPropagation();
                                                Delete.render(fetchData,item.subCategoryId,false,true,false)
                                            }} className='categorycard-button categorycard-button-delete'>Delete</CardButton>
                                        </div>}
                                        {userDetails?.UserType === "Student" && <button onMouseDown={event => event.stopPropagation()}
                                            className='categorycard-button start-test-button' onClick={(event) => {
                                                localStorage.setItem("selectedOption","{}");
                                                localStorage.setItem("attemptedQuestions",0);
                                                localStorage.setItem("prevSelectedOption","");
                                                Instructions.render(event,setShowQuestion,setSelectedQuizId,setTime,convertMinutesToTime,item,details)
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