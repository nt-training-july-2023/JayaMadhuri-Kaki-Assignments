import React,{useState,useEffect} from 'react';
import axios from 'axios'
import './Question.scss'
import Swal from 'sweetalert2'
import AddUpdateQuestion from './AddUpdateQuestion';

const Question = (props) =>{
    const {selectedQuizId,setShowQuestion,userDetails} = props;
    const [question,setQuestion] = useState([]);
    const [titleQuestion,setTitleQuestion] = useState("Add Question");
    const message = "No Questions Found!";
    const [popUp,setPopUp] = useState(false);
    const [initialValues,setInitialValues] = useState({
        questionContent:"",
        optionA:"",
        optionB:"",
        optionC:"",
        optionD:"",
        correctAnswer:"",
        subCategoryId:selectedQuizId
    })
    const handleAdd = () =>{
        setTitleQuestion("Add Question");
        setInitialValues({
            questionContent:"",
            optionA:"",
            optionB:"",
            optionC:"",
            optionD:"",
            correctAnswer:"",
            subCategoryId:selectedQuizId
        })
        setPopUp(true);
    }
    const fetchData = async () => {
        try {
          const response = await axios.get(`http://localhost:6060/getAllQuestions/${selectedQuizId}`);
            setQuestion(response?.data?.QuestionBySubCategoryId);
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
            <div>
                {userDetails?.UserType === "Admin" && <button className='addquestion-btn' onClick={handleAdd}>Add Question</button>}
                <button className='backquestion-btn' onClick={()=>{setShowQuestion(false);}}>Back</button>
                <h1 className='category-title'>Questions</h1>
                <hr/>
            </div>
            {question.length>0 ? (
            <div className="question-container">
            {question.map((item) => (
            <div key={item.questionId} className="question-card">
                {/* <p>subCategoryId ID: {item.subCategoryId}</p> */}
                <p className='p'>{item.questionContent}</p>
                <p className='p'>{item.optionA}</p>
                <p className='p'>{item.optionB}</p>
                <p className='p'>{item.optionC}</p>
                <p className='p'>{item.optionD}</p>
                <p className='p'>{item.correctAnswer}</p>
                {userDetails?.UserType === "Admin" && <div>
                    <button onClick={()=>{
                        setPopUp(true);
                        let updateInitialValues = {questionId:item?.questionId, questionContent:item?.questionContent, optionA:item?.optionA, optionB:item?.optionB, optionC:item?.optionC, optionD:item?.optionD, correctAnswer:item?.correctAnswer, subCategoryId:item?.subCategoryId};
                        setInitialValues(updateInitialValues);
                        setTitleQuestion("Update Question");
                    }}  className='quiz-btn'>Update</button>
                    <button onClick={()=>{
                            axios.delete(`http://localhost:6060/deleteQuestion/${item.questionId}`)
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
                </div>
            ))}
            </div>
            ):(
                <h2 style={{textAlign:"center",color:"#31334e"}}>No Questions</h2>
            )}
            {popUp && (
                <AddUpdateQuestion titleQuestion={titleQuestion} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData}/>
            )}
        </div>
    )
}

export default Question;