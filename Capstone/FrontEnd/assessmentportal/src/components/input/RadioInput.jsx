
const RadioInput = (props) =>{
    const {onChange, value, checked}  = props;
    return(
        <div>
            <input
            type="radio"
            name="gender"
            onChange={onChange}
            value={value}
            checked={checked}
            />
        </div>
    )
}
export default RadioInput;