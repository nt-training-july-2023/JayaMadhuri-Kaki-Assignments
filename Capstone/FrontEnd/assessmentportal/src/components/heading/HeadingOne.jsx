
const HeadingOne = (props) =>{
    const {children, className} = props;
    return(
        <div>
            <h1 className={className}>{children}</h1>
        </div>
    )
}
export default HeadingOne;