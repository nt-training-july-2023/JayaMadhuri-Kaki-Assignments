
const Button = (props) =>{
    const {className, onClick, children,onMouseDown}  = props;
    return(
        <div>
            <button
            onMouseDown={onMouseDown}
            className={className}
            onClick={onClick}
            >
            {children}
            </button>
        </div>
    )
}
export default Button;