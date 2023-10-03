import '../../styles/Category.scss'
import React,{useState} from 'react'
import Button from '../button/Button';
import {FaPencilAlt,FaTrashAlt} from 'react-icons/fa'

const CommonCard = (props) => {
    const { data, onClickCard, userType, cardType, onClickUpdate, onClickDelete, onClickStartTest } = props;
    const [expand,setExpand] = useState(false);
    return (
        <div key={data.categoryId} className="category-card" onClick={onClickCard}
        >
            <p>Name: {data.categoryName}</p>
            <div className={expand ? 'long-description' : 'short-description'}>
                <span
                    onClick={(event) => {
                        event.stopPropagation();
                        setExpand(!expand)
                    }}
                    onMouseDown={event => event.stopPropagation()}
                    className='description'
                >
                    Description: {data.categoryDescription.length > 40 ? expand ? data.categoryDescription + " ... Read Less" : data.categoryDescription.slice(0, 40) + " ... Read More" : data.categoryDescription}
                </span>
            </div>
            {cardType == "Quiz" && <p>Time: {data.timeLimitInMinutes} minutes</p>}
            {userType === "Admin" && <div className='categorycard-buttons-div'>
                <Button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                    event.stopPropagation()
                    onClickUpdate();
                }} className='categorycard-button categorycard-button-update'><FaPencilAlt className='icons' />Update</Button>
                <Button onMouseDown={event => event.stopPropagation()} onClick={(event) => {
                    event.stopPropagation()
                    onClickDelete();
                }} className='categorycard-button categorycard-button-delete'><FaTrashAlt className='icons' />Delete</Button>
            </div>}
            {/* {(userType === "Student" && cardType == "Quiz") &&
                <Button onMouseDown={event => event.stopPropagation()}
                    className='categorycard-button start-test-button' 
                    onClick={(event) => {
                        localStorage.setItem("selectedOption", "{}");
                        localStorage.setItem("attemptedQuestions", 0);
                        localStorage.setItem("prevSelectedOption", "");
                        Alert.Instructions(event, setShowQuestion, setSelectedQuizId, setTime, convertMinutesToTime, data, details)
                    }} 
                >
                    Start Test
                </Button>
            } */}
        </div>
    )
}
export default CommonCard;