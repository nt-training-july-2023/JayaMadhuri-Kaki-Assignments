
const Button = (props) =>{
    const {className, onClick, children, onMouseDown, disabled}  = props;
    return(
        <div>
            <button
            onMouseDown={onMouseDown}
            className={className}
            onClick={onClick}
            disabled={disabled}
            >
            {children}
            </button>
        </div>
    )
}
export default Button;