import FormButton from "../button/FormButton";

const Heading = (props) => {
    const {className, onClick, buttonName, headingText, userDetails, backButton, backClassName, backOnClick, backButtonName, question} = props;
    return(
        <div>
            {userDetails?.UserType === "Admin" && <FormButton className={className} onClick={onClick}>{buttonName}</FormButton>}
            {backButton && <FormButton className={backClassName} onClick={backOnClick}>{backButtonName}</FormButton>}
            {question && <FormButton className={backClassName} onClick={backOnClick}>{backButtonName}</FormButton>}
            <h1 className='category-title'>{headingText}</h1>
            <hr />
        </div>
    )
}
export default Heading;