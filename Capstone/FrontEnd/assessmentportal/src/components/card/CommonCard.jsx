import '../../styles/Category.scss'
import React, { useState } from 'react'
import Button from '../button/Button';
import { FaPencilAlt, FaTrashAlt } from 'react-icons/fa'
import Paragraph from '../paragraph/Paragraph';

const CommonCard = (props) => {
    const { data, onClickCard, userType, cardType, onClickUpdate, onClickDelete, onClickStartTest } = props;
    const [expand, setExpand] = useState(false);
    return (
        <div key={cardType == "Category" ? data.categoryId : data.subCategoryId} className={cardType == "Category" ? 'category-card category-card-height' : 'category-card quiz-card-height'} onClick={onClickCard}
        >
            <Paragraph children={`Name: ${cardType === "Category" ? data.categoryName : data.subCategoryName}`} />
            <div className={expand ? 'long-description' : 'short-description'}>
                <span
                    onClick={(event) => {
                        event.stopPropagation();
                        setExpand(!expand)
                    }}
                    onMouseDown={event => event.stopPropagation()}
                    className='description'
                >
                    {cardType == "Category" && <>Description: {data.categoryDescription.length > 40 ? expand ? data.categoryDescription + " ... Read Less" : data.categoryDescription.slice(0, 40) + " ... Read More" : data.categoryDescription}</>}
                    {cardType == "Quiz" && <>Description: {data.subCategoryDescription.length > 40 ? expand ? data.subCategoryDescription + " ... Read Less" : data.subCategoryDescription.slice(0, 40) + " ... Read More" : data.subCategoryDescription}</>}
                </span>
            </div>
            {cardType == "Quiz" && <Paragraph children={`Time: ${data.timeLimitInMinutes} minutes`}/>}
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
            {(userType === "Student" && cardType == "Quiz") &&
                <Button onMouseDown={event => event.stopPropagation()}
                    className='categorycard-button start-test-button'
                    onClick={onClickStartTest}
                    children="Start Test"
                />
            }
        </div>
    )
}
export default CommonCard;