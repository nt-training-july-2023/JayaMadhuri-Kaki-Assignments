
const EmailInput = (props) =>{
    const {onChange, value}  = props;
    return(
        <div>
            <input
            className="input"
            type="email"
            name="emailId"
            placeholder="Email Id"
            onChange={onChange}
            value={value}
            />
        </div>
    )
}
export default EmailInput;