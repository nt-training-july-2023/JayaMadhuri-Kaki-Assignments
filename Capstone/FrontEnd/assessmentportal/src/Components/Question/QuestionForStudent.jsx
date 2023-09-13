import React from 'react';
import Timer from '../Timer/Timer';
import './Question.scss'
const QuestionForStudent = (props) =>{
    const {selectedQuizId,setShowQuestion,time} = props;
    console.log(time);
    return(
        <div>
            <div>
                <button className='addquestion-btn' onClick={()=>{setShowQuestion(false);}}>Back</button>
                <h1 className='category-title'>Questions</h1>
                <hr/>
            </div>
            <div className='timer'>
                <Timer expiryTimestamp={time} setShowQuestion={setShowQuestion}/>
            </div>
        </div>
    )
}

export default QuestionForStudent;