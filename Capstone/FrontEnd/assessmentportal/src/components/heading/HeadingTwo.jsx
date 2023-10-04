
const HeadingTwo = (props) =>{
    const {children, className} = props;
    return(
        <div>
            <h2 className={className}>{children}</h2>
        </div>
    )
}
export default HeadingTwo;