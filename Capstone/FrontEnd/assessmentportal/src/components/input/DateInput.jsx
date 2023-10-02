
const DateInput = (props) =>{
    const {onChange, value}  = props;
    return(
        <div>
            <input
            className="input"
            type="date"
            name="dateOfBirth"
            placeholder="Date of Birth"
            onChange={onChange}
            value={value}
            />
        </div>
    )
}
export default DateInput;