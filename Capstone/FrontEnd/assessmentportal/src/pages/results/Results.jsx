import React, { useEffect, useState } from 'react';
import '../../styles/Question.scss';
import Swal from 'sweetalert2'
import FinalResultsUrl from '../../services/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CommonTable from '../../components/table/CommonTable';

const Results = ({ userDetails }) => {
    const [results, setResults] = useState([]);
    const handleResutlts = async () => {
        FinalResultsUrl.getResults()
            .then(response => {
                if (response?.data?.statusCode == 200) {
                    setResults(response?.data?.responseData)
                }
            }).catch(error => {
                if (error?.response?.message === "Network Error") {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.NETWORK_ERROR,
                        timer: 2000,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    });
                }
            })
    }
    const handleResutltsOfStudents = async () => {
        FinalResultsUrl.getResultsByStudentEmail(userDetails.EmailId)
            .then(response => {
                if (response?.data?.statusCode == 200) {
                    setResults(response?.data?.responseData)
                }
            }).catch(error => {
                if (error?.response?.message === "Network Error") {
                    Swal.fire({
                        title: sweetAlertMessages.ERROR,
                        text: sweetAlertMessages.NETWORK_ERROR,
                        timer: 2000,
                        showConfirmButton: false,
                        showCancelButton: false,
                        icon: sweetAlertMessages.WARNING,
                        background: "#15172b",
                        color: "white",
                    });
                }
            })
    }
    useEffect(() => {
        localStorage.setItem('reloadCount', '');
        if (userDetails.UserType == "Admin") {
            handleResutlts();
        } else {
            handleResutltsOfStudents();
        }
    }, []);
    const rows = [
        "studentId",
        "studentEmailId",
        "studentName",
        "categoryName",
        "quizName",
        "marksObtained",
        "totalMarks",
        "numOfAttemptedQuestions",
        "totalQuestions",
        "dateAndTime"
    ];
    const columns = [
        "Email ID",
        "Student ID",
        "Full Name",
        "Category",
        "Quiz",
        "Marks Obtained",
        "Total Marks",
        "Number Of Questions Attempted",
        "Total Questions",
        "Date & Time"
    ];
    return (
        <div>
            <div>
                <h1 className='category-title'>Results</h1>
                <hr />
            </div>
            <div className='question-container'>
                {results.length > 0 ? (
                    <CommonTable columns={columns} data={results.reverse()} rows={rows}/>
                ) : (
                    <h2 style={{ textAlign: "center", color: "#31334e" }}>No Results</h2>
                )}
            </div>
        </div>
    )
}
export default Results;