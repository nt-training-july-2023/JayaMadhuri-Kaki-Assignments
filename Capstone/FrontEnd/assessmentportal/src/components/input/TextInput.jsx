
const TextInput = (props) =>{
    const {className, name, placeholder, onChange, value, readOnly}  = props;
    return(
        <div>
            <input
            className={className}
            type="text"
            name={name}
            placeholder={placeholder}
            onChange={onChange}
            value={value}
            readOnly={readOnly}
            />
        </div>
    )
}
export default TextInput;