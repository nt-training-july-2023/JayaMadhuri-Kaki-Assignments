import Button from "../button/Button";
import {FaPlus, FaBackward} from 'react-icons/fa'

const Heading = (props) => {
    const {className, onClick, buttonName, headingText, userDetails, backButton, backClassName, backOnClick, backButtonName, question} = props;
    return(
        <div>
            {userDetails?.UserType === "Admin" && <Button className={className} onClick={onClick}><FaPlus className="icons"/>{buttonName}</Button>}
            {backButton && <Button className={backClassName} onClick={backOnClick}><FaBackward className="icons"/>{backButtonName}</Button>}
            {question && <Button className={backClassName} onClick={backOnClick}><FaBackward className="icons"/>{backButtonName}</Button>}
            <h1 className='category-title'>{headingText}</h1>
            <hr />
        </div>
    )
}
export default Heading;