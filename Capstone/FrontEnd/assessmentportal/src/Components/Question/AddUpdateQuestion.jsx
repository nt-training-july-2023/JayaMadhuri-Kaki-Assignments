import React,{useState} from 'react';
import axios from 'axios'
import './Question.scss';
import Swal from 'sweetalert2'

const AddUpdateQuestion = (props) =>{
    const {titleQuestion,setPopUp,initialValues,fetchData} = props;
    const [questionDetails,setQuestionDetails] = useState(initialValues);
    const [error,setError] = useState("");
    const handleAdd = () =>{
        if(questionDetails?.questionContent.length!=0 ||questionDetails?.optionA.length!=0 ||questionDetails?.optionB.length!=0||questionDetails?.optionC.length!=0||questionDetails?.optionD.length!=0 ||questionDetails?.optionA.length!=0 ||questionDetails?.optionA.length!=0||questionDetails?.optionA.length!=0||questionDetails?.correctAnswer.length!=0){
                setError('')
                axios.post("http://localhost:6060/addQuestion",questionDetails)
                .then(response=>{
                    if(response?.data?.statusCode === 200){
                        Swal.fire({
                            title: 'Add',
                            text: 'Successfully Added',
                            timer: 1500,
                            showConfirmButton:false,
                            showCancelButton:false,
                            icon: "success",
                            background:"#15172b",
                            color:"white",
                        }); 
                        fetchData();
                        setPopUp(false);
                    }
                    console.log(response)
                }).catch(error=>{
                    if(error?.response?.status === 404){
                        Swal.fire({
                            title: 'Error',
                            text: 'Quiz Id Not Found',
                            timer: 1500,
                            showConfirmButton:false,
                            showCancelButton:false,
                            icon: "warning",
                            background:"#15172b",
                            color:"white",
                        }); 
                    }
                    console.log(error)
                })
            }else{
            setError('Question Required')
        }
    }
    const handleUpdate = () =>{
        if(questionDetails?.questionContent.length!=0 ||questionDetails?.optionA.length!=0 ||questionDetails?.optionB.length!=0||questionDetails?.optionC.length!=0||questionDetails?.optionD.length!=0 ||questionDetails?.optionA.length!=0 ||questionDetails?.optionA.length!=0||questionDetails?.optionA.length!=0||questionDetails?.correctAnswer.length!=0){
            setError('')
            axios.put(`http://localhost:6060/updateQuestion/${initialValues.questionId}`,questionDetails)
            .then(response=>{
                console.log(response)
                if(response?.data?.statusCode === 200){
                    Swal.fire({
                        title: 'Update',
                        text: 'Successfully Updated',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "success",
                        background:"#15172b",
                        color:"white",
                    }); 
                    fetchData();
                    setPopUp(false);
                }
            }).catch(error=>{
                console.log(error)
                if(error?.response?.status === 409){
                    Swal.fire({
                        title: 'Error',
                        text: 'Something Wrong',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "warning",
                        background:"#15172b",
                        color:"white",
                    }); 
                }else{
                    Swal.fire({
                        title: 'Error',
                        text: 'Internal Server Error',
                        timer: 1500,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "warning",
                        background:"#15172b",
                        color:"white",
                    }); 
                }
            })
        }else{
        setError('Question Required')
    }
    }
    const handleClick = () =>{
        if(titleQuestion == "Add Question"){
            handleAdd();
        }else{
            handleUpdate();
        }
    }
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name == "questionContent"){
            if(!value){
                setError('question required')
            }else{
                setError('')
            }
        }
        setQuestionDetails({...questionDetails,[name]:value})
    }
    return(
        <div className="addquestion-container">
            <h1 className="question-title1">{titleQuestion}</h1>
            <input className='name' type="text" name="questionContent" value={questionDetails?.questionContent} placeholder='Enter Question' onChange={handleChange}/>
            <p className='err'>{error}</p>
            <input className='name' type="text" name="optionA" value={questionDetails?.optionA} placeholder='Enter OptionA' onChange={handleChange}/>
            <p className='err'>{error}</p>
            <input className='name' type="text" name="optionB" value={questionDetails?.optionB} placeholder='Enter optionB' onChange={handleChange}/>
            <p className='err'>{error}</p>
            <input className='name' type="text" name="optionC" value={questionDetails?.optionC} placeholder='Enter optionC' onChange={handleChange}/>
            <p className='err'>{error}</p>
            <input className='name' type="text" name="optionD" value={questionDetails?.optionD} placeholder='Enter optionD' onChange={handleChange}/>
            <p className='err'>{error}</p>
            <textarea className='name' type="text" name='correctAnswer' value={questionDetails?.correctAnswer} placeholder='Enter Correct answer' onChange={handleChange}/>
            <button className='btn' onClick={handleClick}>{titleQuestion == "Add Question"? "Add" : "Update"}</button>
            <button className='btn' onClick={()=>{setPopUp(false)}}>Close</button>
        </div>
    )
}
export default AddUpdateQuestion;