const Label = (props) =>{
    const {className, children} = props;
    return(
        <div>
            <label className={className}>{children}</label>
        </div>
    )
}
export default Label;