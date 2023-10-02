
const PasswordInput = (props) =>{
    const {type, name, placeholder, onChange, value}  = props;
    return(
        <div>
            <input
            className="input"
            type={type}
            name={name}
            placeholder={placeholder}
            onChange={onChange}
            value={value}
            />
        </div>
    )
}
export default PasswordInput;