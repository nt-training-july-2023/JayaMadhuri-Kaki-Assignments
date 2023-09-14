import React,{useState} from 'react';
import axios from 'axios'
import './Question.scss';
import Swal from 'sweetalert2'

const AddUpdateQuestion = (props) =>{
    const {titleQuestion,setPopUp,initialValues,fetchData} = props;
    const [questionDetails,setQuestionDetails] = useState(initialValues);
    const [error,setError] = useState("");
    const handleAdd = () =>{
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
            if(error?.response?.status === 409){
                Swal.fire({
                    title: 'Error',
                    text: 'All Fields are Mandatory',
                    timer: 1500,
                    showConfirmButton:false,
                    showCancelButton:false,
                    icon: "warning",
                    background:"#15172b",
                    color:"white",
                }); 
            }
        })
    }
    const handleUpdate = () =>{
        if(questionDetails?.questionContent.length!=0 ||questionDetails?.optionA.length!=0 ||questionDetails?.optionB.length!=0||questionDetails?.optionC.length!=0||questionDetails?.optionD.length!=0 ||questionDetails?.optionA.length!=0 ||questionDetails?.optionA.length!=0||questionDetails?.optionA.length!=0||questionDetails?.correctAnswer.length!=0){
            setError('')
            axios.put(`http://localhost:6060/updateQuestion/${initialValues.questionId}`,questionDetails)
            .then(response=>{
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
                if(error?.response?.status === 409){
                    Swal.fire({
                        title: 'Error',
                        text: 'All Fields are Mandatory',
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
        setQuestionDetails({...questionDetails,[name]:value})
    }
    return(
        <div className="addquestion-container">
            <h1 className="question-title1">{titleQuestion}</h1>
            <input className='name' type="text" name="questionContent" value={questionDetails?.questionContent} placeholder='Enter Question' onChange={handleChange}/>
            <input className='name' type="text" name="optionA" value={questionDetails?.optionA} placeholder='Enter OptionA' onChange={handleChange}/>
            <input className='name' type="text" name="optionB" value={questionDetails?.optionB} placeholder='Enter optionB' onChange={handleChange}/>
            <input className='name' type="text" name="optionC" value={questionDetails?.optionC} placeholder='Enter optionC' onChange={handleChange}/>
            <input className='name' type="text" name="optionD" value={questionDetails?.optionD} placeholder='Enter optionD' onChange={handleChange}/>
            <select className='name' name='correctAnswer' value={questionDetails?.correctAnswer} onChange={handleChange}>
            <option > -- select an option -- </option>
            <option value="optionA">Option A</option>
            <option value="optionB">Option B</option>
            <option value="optionC">Option C</option>
            <option value="optionD">Option D</option>
            </select>
            <button className='btn' onClick={handleClick}>{titleQuestion == "Add Question"? "Add" : "Update"}</button>
            <button className='btn' onClick={()=>{setPopUp(false)}}>Close</button>
        </div>
    )
}
export default AddUpdateQuestion;