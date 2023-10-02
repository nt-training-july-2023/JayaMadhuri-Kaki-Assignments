
const FormButton = (props) =>{
    const {className, onClick, children}  = props;
    return(
        <div>
            <button
            className={className}
            onClick={onClick}
            >
            {children}
            </button>
        </div>
    )
}
export default FormButton;