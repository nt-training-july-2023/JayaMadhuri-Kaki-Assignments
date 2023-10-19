import '../../styles/Category.scss'
import React from 'react'
import Button from '../button/Button';
import { FaPencilAlt, FaTrashAlt } from 'react-icons/fa'
import Paragraph from '../paragraph/Paragraph';

const CommonCard = (props) => {
    const { data, onClickCard, userType, cardType, onClickUpdate, onClickDelete, onClickStartTest } = props;
    let cardClassName;
    if (userType === "Admin") {
        cardClassName = cardType === "Category" ? 'category-card category-card-height' : 'category-card quiz-card-height';
    } else if (userType === "Student") {
        cardClassName = cardType === "Category" ? 'category-card' : 'category-card category-card-margin';
    }
    return (
        <div key={cardType == "Category" ? data.categoryId : data.subCategoryId} className={cardClassName} onClick={onClickCard}
        >
            <div className='long-description'>
                <Paragraph children={`Name: ${cardType === "Category" ? data.categoryName : data.subCategoryName}`} />
                {cardType == "Category" && <Paragraph>Description: {data.categoryDescription}</Paragraph>}
                {cardType == "Quiz" && <Paragraph>Description: {data.subCategoryDescription}</Paragraph>}
            </div>
            {cardType == "Quiz" && <Paragraph children={`Time: ${data.timeLimitInMinutes} minutes`} />}
            {userType === "Admin" && <div className='categorycard-buttons-div'>
                <Button onClick={(event) => {
                    event.stopPropagation()
                    onClickUpdate();
                }} className='categorycard-button categorycard-button-update'><FaPencilAlt className='icons' />Update</Button>
                <Button onClick={(event) => {
                    event.stopPropagation()
                    onClickDelete();
                }} className='categorycard-button categorycard-button-delete'><FaTrashAlt className='icons' />Delete</Button>
            </div>}
            {(userType === "Student" && cardType == "Quiz") &&
                <Button
                    className='categorycard-button start-test-button'
                    onClick={onClickStartTest}
                    children="Start Test"
                />
            }
        </div>
    )
}
export default CommonCard;