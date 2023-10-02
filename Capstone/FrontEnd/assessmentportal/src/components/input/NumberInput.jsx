
const NumberInput = (props) =>{
    const {onChange, value}  = props;
    return(
        <div>
            <input
            className="name"
            type="number"
            name="timeLimitInMinutes"
            placeholder="Enter Time Limit(In Minutes)"
            onChange={onChange}
            value={value}
            />
        </div>
    )
}
export default NumberInput;