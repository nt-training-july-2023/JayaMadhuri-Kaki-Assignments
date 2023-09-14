import React,{useState,useEffect} from 'react';
import Timer from '../Timer/Timer';
import './Question.scss'
import Swal from 'sweetalert2'
import axios from 'axios'
const QuestionForStudent = (props) =>{
    const {selectedQuizId,setShowQuestion,time,details,selectedId} = props;
    const [selectedOption,setSelectedOption] = useState({});
    const [question,setQuestion] = useState([]);
    const [attemptedQuestions,setAttemptedQuestions] = useState(0)
    const fetchData = async () => {
        try {
          const response = await axios.get(`http://localhost:6060/getAllQuestions/${selectedQuizId}`);
            setQuestion(response?.data?.QuestionBySubCategoryId);
        } catch (error) {
            Swal.fire({
                title: 'Error',
                text: 'Error in getting Questions List',
                timer: 1500,
                showConfirmButton:false,
                showCancelButton:false,
                icon: "warning",
                background:"#15172b",
                color:"white",
            }); 
        }
    };
    
    const handleChangeRadio = (questionId, optionValue) => {
        setSelectedOption({
          ...selectedOption,
          [questionId]: optionValue,
        });
        setAttemptedQuestions(prevCount => prevCount + 1);
    };
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
            studentId:details.userId,
            subCategoryId:selectedQuizId,
            categoryId:selectedId,
            marksObtained:score,
            totalMarks:question.length,
            totalQuestions:question.length,
            numOfAttemptedQuestions:attemptedQuestions
        }
        handleResults(payload);
        Swal.fire({
            title: 'Quiz Result',
            text: `Your Score: ${score} out of ${question.length}`,
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: 'info',
            background: '#15172b',
            color: 'white',
        })
        setTimeout(function() {
            setShowQuestion(false)
        }, 2000);
    };
    const handleResults = (results)=>{
        axios.post(`http://localhost:6060/addResults`,results)
        .then(response=>{
            console.log(response)
        }).catch(error=>{
            console.log(error)
            Swal.fire({
                title: 'Error in Storing Results',
                timer: 2000,
                showConfirmButton: false,
                showCancelButton: false,
                icon: 'info',
                background: '#15172b',
                color: 'white',
            })
        })
    }
    useEffect(() => {
        fetchData();
    }, []);
    return(
        <div>
            <div>
            {question.length===0 && (<button className='addquestion-btn' onClick={()=>{setShowQuestion(false);}}>Back</button>)}
                <h1 className='category-title'>Questions</h1>
                <hr/>
            </div>
            {question.length>0 ? (
            <>
            <div className='timer'>
                <Timer expiryTimestamp={time} setShowQuestion={setShowQuestion} checkAnswers={checkAnswers}/>
            </div>
            <div>
                {question.map((item) => (
                <div key={item.questionId}>
                    <h3>{item.questionContent}</h3>
                    <div>
                    <label>
                        <input
                        type="radio"
                        className="radio-input"
                        onChange={() => handleChangeRadio(item.questionId, 'optionA')}
                        name={`question-${item.questionId}`}
                        value="optionA"
                        />
                        Option A: {item.optionA}
                    </label>
                    </div>
                    <div>
                    <label>
                        <input
                        type="radio"
                        className="radio-input"
                        onChange={() => handleChangeRadio(item.questionId, 'optionB')}
                        name={`question-${item.questionId}`}
                        value="optionB"
                        />
                        Option B: {item.optionB}
                    </label>
                    </div>
                    <div>
                    <label>
                        <input
                        type="radio"
                        className="radio-input"
                        onChange={() => handleChangeRadio(item.questionId, 'optionC')}
                        name={`question-${item.questionId}`}
                        value="optionC"
                        />
                        Option C: {item.optionC}
                    </label>
                    </div>
                    <div>
                    <label>
                        <input
                        type="radio"
                        className="radio-input"
                        onChange={() => handleChangeRadio(item.questionId, 'optionD')}
                        name={`question-${item.questionId}`}
                        value="optionD"
                        />
                        Option D: {item.optionD}
                    </label>
                    </div>
                </div>
                ))}
                </div>
                <button className="submit-quiz-btn" onClick={checkAnswers}>
                    Submit Quiz
                </button>
            </>
            ):(
                <h2 style={{textAlign:"center",color:"#31334e"}}>No Questions</h2>
            )}
        </div>
    )
}

export default QuestionForStudent