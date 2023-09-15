import React,{useState,useEffect} from 'react';
import Timer from '../Timer/Timer';
import './Question.scss'
import Swal from 'sweetalert2'
import axios from 'axios'
const QuestionForStudent = (props) =>{
    const {selectedQuizId,setShowQuestion,time,details,selectedId,setEnable} = props;
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
    
    const handleAnswerClick = (questionId, optionValue) => {
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
            {question.length===0 && <>{setEnable(false)}<button className='addquestion-btn' onClick={()=>{setShowQuestion(false);}}>Back</button></>}
                <h1 className='category-title'>Questions</h1>
                <hr/>
            </div>
            {question.length>0 ? (
            <>
            <div className='timer'>
                <Timer expiryTimestamp={time} setShowQuestion={setShowQuestion} checkAnswers={checkAnswers}/>
            </div>
            <div className='question-body'>
            <div className='card'>
            <div className='student-question-container'>
                {setEnable(true)}
                {question.map((item) => (
                <div key={item.questionId}>
                    <h3>{item.questionContent}</h3>
                    <div className='answer-options'>
                    <button
                        className={`answer-button ${
                        selectedOption[item.questionId] === 'optionA' ? 'selected' : ''
                        }`}
                        onClick={() => handleAnswerClick(item.questionId, 'optionA')}
                    >
                        {item.optionA}
                    </button>
                    <button
                        className={`answer-button ${
                        selectedOption[item.questionId] === 'optionB' ? 'selected' : ''
                        }`}
                        onClick={() => handleAnswerClick(item.questionId, 'optionB')}
                    >
                        {item.optionB}
                    </button>
                    <button
                        className={`answer-button ${
                        selectedOption[item.questionId] === 'optionC' ? 'selected' : ''
                        }`}
                        onClick={() => handleAnswerClick(item.questionId, 'optionC')}
                    >
                        {item.optionC}
                    </button>
                    <button
                        className={`answer-button ${
                        selectedOption[item.questionId] === 'optionD' ? 'selected' : ''
                        }`}
                        onClick={() => handleAnswerClick(item.questionId, 'optionD')}
                    >
                        {item.optionD}
                    </button>
                    </div>
                </div>
                ))}
                </div>
                <div className="submit-btn">
                    <button className="submit-quiz" onClick={checkAnswers}>
                        Submit Quiz
                    </button>
                </div>
            </div>
            </div>
            </>
            ):(
                <h2 style={{textAlign:"center",color:"#31334e"}}>No Questions</h2>
            )}
        </div>
    )
}

export default QuestionForStudent