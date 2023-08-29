import {useState,useEffect} from 'react'
import './Register.scss'

const Register = (props) =>{
    const initialValues = {
        firstName:"",
        lastName:"",
        dateOfBirth:"",
        gender:"male",
        emailId: "", 
        password: "",
        userType: "Student",
        confirmPassword:""
    };
    const {setRenderComponent} = props;
    const [errors,setErrors] = useState({});
    const [registerRequestBody,setRegisterRequestBody] = useState(initialValues);
    const [password,setPassword] = useState("")
    const [isDisable,setIsDisable] = useState(true);
    const handleClick = () =>{
        setRenderComponent("login");
    }
    const handleChangeRadio = (e) =>{
        setRegisterRequestBody({...registerRequestBody,gender:e.target.value})
    }
    const finalValues ={
        firstName:"First Name is required",
        lastName:"Last Name is required",
        dateOfBirth:"Date of Birth is required",
        emailId: "Email ID is required", 
        password: "Password is required",
        confirmPassword:"Confirm password is required"
    };
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name === "emailId"){
            if(!value){
                setErrors({...errors,emailId:'Email Required'});
            }
            else if(!/^[A-Z0-9a-z+_-]+@nucleusteq[.]com$/.test(value)){
                setErrors({...errors,emailId:'Invalid Email'});
            }
            else{
                setErrors({...errors,emailId:''});
                setRegisterRequestBody({...registerRequestBody,emailId:value})
            }
        }
        else if(name === "password"){
            setPassword(value);
            var re = {
                capital: /(?=.*[A-Z])/,
                length: /(?=.{7,40}$)/,
                specialChar: /[ -\/:-@\[-\`{-~]/,
                digit: /(?=.*[0-9])/,
            };
            if(!value){
                setErrors({...errors,password:'Password Required'});
            }
            else if((!re.capital.test(value))||(!re.specialChar.test(value))||(!re.length.test(value))||(!re.digit.test(value))){
                setErrors({...errors,password:'Password must contain atleast a Capital, Special character, Number and minimum 8 characters '});
            }
            else{
                setErrors({...errors,password:''});
                setRegisterRequestBody({...registerRequestBody,password:value})
            }
        }else if(name === "firstName"){
            if(!value){
                setErrors({...errors,firstName:'First Name Required'});
            }
            else{
                setErrors({...errors,firstName:''});
                setRegisterRequestBody({...registerRequestBody,firstName:value})
            }
        }
        else if(name === "lastName"){
            if(!value){
                setErrors({...errors,lastName:'Last Name Required'});
            }
            else{
                setErrors({...errors,lastName:''});
                setRegisterRequestBody({...registerRequestBody,lastName:value})
            }
        }
        else if(name === "dateOfBirth"){
            if(!value){
                setErrors({...errors,dateOfBirth:'Date of Birth Required'});
            }
            else{
                setErrors({...errors,dateOfBirth:''});
                setRegisterRequestBody({...registerRequestBody,dateOfBirth:value})
            }
        }
        else if(name === "confirmPassword"){
            if(!value){
                setErrors({...errors,confirmPassword:'Confirm your password'});
            }
            else if(password!=value){
                
                setErrors({...errors,confirmPassword:'Passwords does not match'});
            }
            else{
                setErrors({...errors,confirmPassword:''});
                setRegisterRequestBody({...registerRequestBody,confirmPassword:value})
            }
        }
        setRegisterRequestBody({...registerRequestBody,[name]:value});
    }
    console.log(registerRequestBody)
    console.log(errors)
    const handleRegister=()=>{
        setErrors(finalValues);
        if (errors?.firstName?.length == 0 && errors?.lastName?.length == 0 && errors?.dateOfBirth?.length == 0 && errors?.emailId?.length == 0 && errors?.password?.length == 0 && errors?.confirmPassword?.length == 0) {
            setRenderComponent("login")
        }
        else if(errors?.firstName?.length != 0 || errors?.lastName?.length != 0 || errors?.dateOfBirth?.length != 0 || errors?.emailId?.length != 0 || errors?.password?.length != 0 || errors?.confirmPassword?.length != 0){
            setRenderComponent("register")
        }
    }
    useEffect(() => {
        if (errors?.firstName?.length == 0 && errors?.lastName?.length == 0 && errors?.dateOfBirth?.length == 0 && errors?.emailId?.length == 0 && errors?.password?.length == 0 && errors?.confirmPassword?.length == 0) {
            setIsDisable(false);
        }
        else if(errors?.firstName?.length != 0 || errors?.lastName?.length != 0 || errors?.dateOfBirth?.length != 0 || errors?.emailId?.length != 0 || errors?.password?.length != 0 || errors?.confirmPassword?.length != 0){
            setIsDisable(true);
        }
    },[errors]);
    return(
        <div className="body">
            <div className="form">
                <h1>Sign Up</h1>
                <input type="text" name="firstName" placeholder="First Name" onChange={handleChange} className='input' />
                <p className='error'>{errors.firstName}</p>
                <input type="text" name="lastName" placeholder="Last Name" onChange={handleChange} className='input' />
                <p className='error'>{errors.lastName}</p>
                <input type="date" name="dateOfBirth" placeholder="Date of Birth" onChange={handleChange} className='input' />
                <p className='error'>{errors.dateOfBirth}</p>
                <div className='radio-div'>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="male"/>Male
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="female"/>Female
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="others"/>Other
                </div>
                <input type="email" name="emailId" placeholder="Email Id" onChange={handleChange} className='input' />
                <p className='error'>{errors.emailId}</p>
                <input type="password" name="password" placeholder="Password" onChange={handleChange} className='input' />
                <p className='error' style={{marginLeft:"30px"}}>{errors.password}</p>
                <input type="password" name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} className='input'/>
                <p className='error'>{errors.confirmPassword}</p>
                <div className='btn'>
                    <button className='login-btn' onClick={handleRegister}>Register</button>
                    <p className='register-btn'> Having an account! <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Register;