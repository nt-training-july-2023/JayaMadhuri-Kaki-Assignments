import React, { useState, useEffect } from 'react';
import '../../styles/Category.scss';
import AddUpdateQuiz from './AddUpdateQuiz';
import Question from '../question/Question';
import QuestionForStudent from '../question/QuestionForStudent';
import Url from '../../service/Url';
import { sweetAlertMessages } from "../../constants/ValidationMessages"
import Heading from '../../components/heading/Heading';
import Alert from '../../components/sweetAlert/Alert';
import CommonCard from '../../components/card/CommonCard';
import HeadingTwo from '../../components/heading/HeadingTwo';

const Quiz = (props) => {
    const { userDetails, setShowQuiz, selectedId, setEnable, selectedName, setRenderComponent } = props;
    const showQuestion_AfterRefresh = localStorage.getItem("Current_Quiz_SubWindow")
    const categoryId = localStorage.getItem("CategoryId")
    const categoryName = localStorage.getItem("CategoryName")
    const [quiz, setQuiz] = useState([]);
    const [details, setDetails] = useState({})
    const [title, setTitle] = useState("Add Quiz");
    const [selectedQuizId, setSelectedQuizId] = useState(null)
    const [selectedQuizName, setSelectedQuizName] = useState("")
    const [popUp, setPopUp] = useState(false);
    const [loading, setLoading] = useState(false);
    const [isDisable,setIsDisable] = useState(false)
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
                    Alert.Warning(sweetAlertMessages.ERROR_GETTING_LIST)
                }else if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
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
                if (error?.response?.message === sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
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
    const onClickQuizStartTest = (item) =>{
        localStorage.setItem("selectedOption", "{}");
        localStorage.setItem("attemptedQuestions", 0);
        Alert.Instructions(setShowQuestion, setSelectedQuizId, setTime, convertMinutesToTime, item, details)
    }
    const onClickQuizUpdate = (item) =>{
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
        setIsDisable(true)
    }
    const onClickQuizDelete = (item) =>{
        Alert.Delete(fetchData, item.subCategoryId, false, true, false)
    }
    const onClickQuizCard = (item) =>{
        {
            userDetails?.UserType === "Admin" &&
            setShowQuestion(true);
            localStorage.setItem("Current_Quiz_SubWindow", "question")
            localStorage.setItem("QuizId", item.subCategoryId)
            localStorage.setItem("QuizName", item.subCategoryName)
            setSelectedQuizId(item.subCategoryId);
            setSelectedQuizName(item.subCategoryName);
        }
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
                                localStorage.setItem("Current_Category_SubWindow", "category")
                            }}
                            isDisable={isDisable}
                        />
                    </div>
                    {userDetails?.UserType === "Admin" && <div>
                        <HeadingTwo className='sub-heading-title' children={selectedName ? selectedName : categoryName}/>
                    </div>}
                </>}
            {!showQuestion ? (<>
                {loading &&
                    <div className='category-container-wrapper scroll-height'>
                        {quiz.length > 0 ? (
                            <div className={popUp ? 'display-none' : 'category-container'}>
                                {quiz.map((item) => (
                                    <CommonCard
                                        data={item}
                                        userType={userDetails.UserType}
                                        cardType={"Quiz"}
                                        onClickCard={()=>{onClickQuizCard(item)}}
                                        onClickUpdate={()=>{onClickQuizUpdate(item)}}
                                        onClickDelete={()=>{onClickQuizDelete(item)}}
                                        onClickStartTest={()=>{onClickQuizStartTest(item)}}
                                    />
                                ))}
                            </div>
                        ) : (
                            <HeadingTwo className='h2-no-list' children="No Quizes"/>
                        )}
                        {popUp && (
                            <AddUpdateQuiz title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData} setIsDisable={setIsDisable} setRenderComponent={setRenderComponent}/>
                        )}
                    </div>}
            </>
            ) : (
                <>{userDetails?.UserType === "Admin" ? (<Question selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} selectedQuizName={selectedQuizName} selectedName={selectedName} setRenderComponent={setRenderComponent}/>) : (<QuestionForStudent selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} time={time} details={details} selectedId={categoryId} setEnable={setEnable} setRenderComponent={setRenderComponent}/>)}</>
            )}
        </div>
    )
}
export default Quiz;