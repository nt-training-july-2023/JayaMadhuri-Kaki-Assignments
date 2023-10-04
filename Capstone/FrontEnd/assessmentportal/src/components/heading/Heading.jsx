import Button from "../button/Button";
import {FaPlus, FaBackward} from 'react-icons/fa'
import HeadingOne from "./HeadingOne";

const Heading = (props) => {
    const {className, onClick, buttonName, headingText, userDetails, backButton, backClassName, backOnClick, backButtonName, question} = props;
    return(
        <div>
            {userDetails?.UserType === "Admin" && <Button className={className} onClick={onClick}><FaPlus className="icons"/>{buttonName}</Button>}
            {backButton && <Button className={backClassName} onClick={backOnClick}><FaBackward className="icons"/>{backButtonName}</Button>}
            {question && <Button className={backClassName} onClick={backOnClick}><FaBackward className="icons"/>{backButtonName}</Button>}
            <HeadingOne className='category-title' children={headingText}/>
            <hr />
        </div>
    )
}
export default Heading;