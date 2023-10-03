
const TextInput = (props) =>{
    const {className, type, name, placeholder, onChange, value, readOnly, checked}  = props;
    return(
        <div>
            <input
            className={className}
            type={type}
            name={name}
            placeholder={placeholder}
            onChange={onChange}
            value={value}
            readOnly={readOnly}
            checked={checked}
            />
        </div>
    )
}
export default TextInput;