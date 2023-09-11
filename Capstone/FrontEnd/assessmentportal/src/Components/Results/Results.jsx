import React,{useEffect, useState} from 'react';
import './Results.scss';
import axios from 'axios'
import Swal from 'sweetalert2'
const Results = () =>{
    const [results,setResults] = useState([]);
    const handleResutlts = async() =>{
        await axios.get('http://localhost:6060/getAllFinalResults')
        .then(response=>{
            if(response?.data?.statusCode == 200){
                setResults(response?.data?.FinalResults)
            }
        }).catch(error=>{
            if(error?.response?.message === "Network Error"){
                Swal.fire({
                    title: 'Erro',
                    text: 'NetWork Error',
                    timer: 2000,
                    showConfirmButton:false,
                    showCancelButton:false,
                    icon: "warning",
                    background:"#15172b",
                    color:"white",
                });  
            }
        })
    }
    // const handleResutltsOfStudents = async() =>{
    //     await axios.get(`http://localhost:6060/getAllFinalResults\${studentId}`)
    //     .then(response=>{
    //         if(response?.data?.statusCode == 200){
    //             setResults(response?.data?.FinalResults)
    //         }
    //     }).catch(error=>{
    //         if(error?.response?.message === "Network Error"){
    //             Swal.fire({
    //                 title: 'Erro',
    //                 text: 'NetWork Error',
    //                 timer: 2000,
    //                 showConfirmButton:false,
    //                 showCancelButton:false,
    //                 icon: "warning",
    //                 background:"#15172b",
    //                 color:"white",
    //             });  
    //         }
    //     })
    // }
    useEffect(()=>{
        handleResutlts();
    },[])
    return (
        <div>
            <div>
              <h1 className='category-title'>Results</h1>
              <hr/>
            </div>
            <div className='result-container'>
                <table class= "table table-responsive">
                    <tr>
                        <th>Student Id</th>
                        <th>Email Id</th>
                        <th>Full Name</th>
                        <th>Category</th>
                        <th>Quiz</th>
                        <th>Marks Obtained</th>
                        <th>Total Marks</th>
                        <th>Number Of Questions Attempted</th>
                        <th>Total Questions</th>
                        <th>Date & Time</th>
                    </tr> 
                    <tbody>
                        {results.map((result) => (
                        <tr  key = {result.finalResultId}>
                            <td>{result.studentId}</td>
                            <td>{result.studentEmailId}</td>
                            <td>{result.studentName}</td>
                            <td>{result.categoryName}</td>
                            <td>{result.quizName}</td>
                            <td>{result.marksObtained}</td>
                            <td>{result.totalMarks}</td>
                            <td>{result.numOfAttemptedQuestions}</td>
                            <td>{result.totalQuestions}</td>
                            <td>{result.dateAndTime}</td>
                        </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
export default Results;