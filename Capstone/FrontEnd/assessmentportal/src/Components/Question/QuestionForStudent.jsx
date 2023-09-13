import React from 'react';
const QuestionForStudent = (props) =>{
    const {selectedQuizId,setShowQuestion} = props;
    return(
        <div>
            <div>
                <button className='backquestion-btn' onClick={()=>{setShowQuestion(false);}}>Back</button>
                <h1 className='category-title'>Questions</h1>
                <hr/>
            </div>
        </div>
    )
}

export default QuestionForStudent;