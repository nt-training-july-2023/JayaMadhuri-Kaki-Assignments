
const Paragraph = (props) =>{
    const {children, className} = props;
    return(
        <div>
            <p className={className}>{children}</p>
        </div>
    )
}
export default Paragraph;