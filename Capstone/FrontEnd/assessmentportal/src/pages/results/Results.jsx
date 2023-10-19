import React, { useEffect, useState } from 'react';
import '../../styles/Question.scss';
import FinalResultsUrl from '../../service/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import CommonTable from '../../components/table/CommonTable';
import Alert from '../../components/sweetAlert/Alert';
import HeadingOne from '../../components/heading/HeadingOne';
import HeadingTwo from '../../components/heading/HeadingTwo';

const Results = ({ userDetails, setRenderComponent }) => {
    const [results, setResults] = useState([]);
    const handleResutlts = async () => {
        FinalResultsUrl.getResults()
            .then(response => {
                if (response?.data?.statusCode == 200) {
                    setResults(response?.data?.responseData)
                }
            }).catch(error => {
                if (error?.message === sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
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
                if (error?.message === sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
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
                <HeadingOne className='category-title' children="Results"/>
                <hr />
            </div>
            <div className='question-container scroll'>
                {results.length > 0 ? (
                    <CommonTable columns={columns} data={results.reverse()} rows={rows}/>
                ) : (
                    <HeadingTwo className='h2-no-list' children="No Results"/>
                )}
            </div>
        </div>
    )
}
export default Results;