import React,{useState,useEffect} from 'react';
import axios from 'axios'
import './Quiz.scss'
import Swal from 'sweetalert2'
import AddUpdateQuiz from './AddUpdateQuiz';
import Question from '../Question/Question';

const Quiz = (props) =>{
    const {userDetails,setShowQuiz,selectedId} = props;
    const [quiz,setQuiz] = useState([]);
    const [title,setTitle] = useState("Add Quiz");
    const [selectedQuizId,setSelectedQuizId] = useState(null)
    const message = "No Quiz Found!";
    const [popUp,setPopUp] = useState(false);
    const [showQuestion,setShowQuestion] = useState(false);
    const [initialValues,setInitialValues] = useState({
        subCategoryName:"",
        subCategoryDescription:"",
        timeLimitInMinutes:"",
        categoryId:"",
    })
    const handleAdd = () =>{
        setTitle("Add Quiz");
        setInitialValues({
            subCategoryName:"",
            subCategoryDescription:"",
            timeLimitInMinutes:"",
            categoryId:selectedId
        })
        setPopUp(true);
    }
    const fetchData = async () => {
        try {
          const response = await axios.get(`http://localhost:6060/subCategoryByCategory/${selectedId}`);
            setQuiz(response?.data?.SubCategoryByCategoryId);
        } catch (error) {
            if(error?.response?.statusCode == 400){
                console.log(message);
            }
        }
    };
    useEffect(() => {
        fetchData();
    }, []);
    return(
        <div>
            {!showQuestion && 
            <div>
                {userDetails?.UserType === "Admin" && <button className='addquiz-btn' onClick={handleAdd}>Add Quiz</button>}
                <button className='backquiz-btn' onClick={()=>{setShowQuiz(false)}}>Back</button>
                <h1 className='category-title'>Quiz</h1>
                <hr/>
            </div>}
            {!showQuestion ?(<>
            {quiz.length>0 ? (
            <div className="quiz-container">
            {quiz.map((item) => (
            <div key={item.subCategoryId} className="quiz-card" onClick={()=>{setShowQuestion(true);setSelectedQuizId(item.subCategoryId);}}>
                {/* <p>subCategoryId ID: {item.subCategoryId}</p> */}
                <p className='p'>Name: {item.subCategoryName}</p>
                <p className='p'>Description: {item.subCategoryDescription}</p>
                <p className='p'>Time(In Minutes): {item.timeLimitInMinutes}</p>
                {userDetails?.UserType === "Admin" && <div>
                    <button onClick={()=>{
                        setPopUp(true);
                        let updateInitialValues = {subCategoryId:item?.subCategoryId, subCategoryName:item?.subCategoryName, subCategoryDescription:item?.subCategoryDescription, timeLimitInMinutes:item?.timeLimitInMinutes, categoryId:item?.categoryId};
                        setInitialValues(updateInitialValues);
                        setTitle("Update Quiz");
                    }}  className='quiz-btn'>Update</button>
                    <button onClick={()=>{
                            axios.delete(`http://localhost:6060/deleteSubCategory/${item.subCategoryId}`)
                            .then(response=>{
                                if(response?.data?.statusCode == 200){
                                    Swal.fire({
                                        title: 'Delete',
                                        text: 'Successfully Deleted',
                                        timer: 1000,
                                        showConfirmButton:false,
                                        showCancelButton:false,
                                        icon: "success",
                                        background:"#15172b",
                                        color:"white",
                                    }); 
                                    fetchData()
                                }
                            }).catch(error=>{
                                if(error?.response?.status == "404"){
                                    Swal.fire({
                                        title: 'Delete',
                                        text: 'ID Not Found',
                                        timer: 1000,
                                        showConfirmButton:false,
                                        showCancelButton:false,
                                        icon: "warning",
                                        background:"#15172b",
                                        color:"white",
                                    }); 
                                }
                            })
                        }} className='quiz-btn'>Delete</button>
                </div>}
                {userDetails?.UserType === "Student" && <button onMouseDown={event => event.stopPropagation()} 
                className='quiz-btn' onClick={(event)=>{event.stopPropagation()}}>Start Test</button>}
                </div>
            ))}
            </div>
            ):(
                <h2 style={{textAlign:"center",color:"#31334e"}}>No Quizes</h2>
            )}
            {popUp && (
                <AddUpdateQuiz title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData}/>
            )}
            </>
            ):(
                <Question selectedQuizId={selectedQuizId} setShowQuestion={setShowQuestion} userDetails={userDetails}/>
            )}
        </div>
    )
}

export default Quiz;