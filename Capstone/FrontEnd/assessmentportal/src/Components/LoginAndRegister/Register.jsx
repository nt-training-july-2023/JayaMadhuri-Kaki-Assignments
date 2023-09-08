import {useState,useEffect} from 'react'
import './Register.scss';
import axios from 'axios';
import Swal from 'sweetalert2'

const Register = (props) =>{
    const initialValues = {
        firstName:"",
        lastName:"",
        dateOfBirth:"",
        gender:"male",
        emailId: "", 
        password: "",
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
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name === "emailId"){
            if(!value){
                setErrors({...errors,emailId:'Email Required'});
            }
            else if(!/^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$/.test(value)){
                setErrors({...errors,emailId:'Email should contain .nucleusteq'});
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
            else if(!re.digit.test(value)){
                setErrors({...errors,password:'Must contain a number'});
            }
            else if(!re.capital.test(value)){
                setErrors({...errors,password:'One Captial letter required'})
            }
            else if(!re.specialChar.test(value)){
                setErrors({...errors,password:'No special character'})
            }
            else if(!re.length.test(value)){
                setErrors({...errors,password:'Minimum 8 characters required'})
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
    const finalValues = {
        firstName:registerRequestBody.firstName,
        lastName:registerRequestBody.lastName,
        dateOfBirth:registerRequestBody.dateOfBirth,
        gender:registerRequestBody.gender,
        emailId:registerRequestBody.emailId,
        password:registerRequestBody.password
    }
    const handleRegister = (e) =>{
        e.preventDefault();
        console.log(finalValues)
        axios.post('http://localhost:6060/studentRegister',finalValues)
        .then(response =>{
            console.log(response)
            if(response?.data?.statusCode == 200){
                Swal.fire({
                    title: 'User Registered Successfully',
                    text: 'Redirecting to Login page.....',
                    timer: 2000,
                    showConfirmButton:false,
                    showCancelButton:false,
                    icon: "success",
                    background:"#15172b",
                    color:"white",
                  });  
                setTimeout(function() {
                    setRenderComponent("login")
                }, 2000)
            }
        })
        .catch(error=>{
            console.log(error)
            if(error?.response?.status==409){
                Swal.fire({
                    title: 'Error.....',
                    text: 'An Account already exists with this Email',
                    timer: 2000,
                    showConfirmButton:false,
                    showCancelButton:false,
                    icon: "warning",
                    background:"#15172b",
                    color:"white",
                  });  
            }else if(error?.message == "Network Error"){
                Swal.fire({
                    title: 'Erro',
                    text: 'NetWork Error',
                    timer: 2000,
                    showConfirmButton:false,
                    showCancelButton:false,
                    icon: "warning",
                    background:"#15172b",
                    color:"white",
                });  
            }
        })
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
             <div>
                <img src='https://nucleusteq.com/wp-content/uploads/2020/03/logo-header-1.svg' className='logo'/>
            </div>
            <div className="form">
                <h1 className='title'>Sign Up</h1>
                <b><p className='error'>*Fill all fields to enable Register button</p></b>
                <input type="text" name="firstName" placeholder="First Name" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.firstName}</p></b>
                <input type="text" name="lastName" placeholder="Last Name" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.lastName}</p></b>
                <input type="date" name="dateOfBirth" placeholder="Date of Birth" onChange={handleChange} className='input' max="2023-09-06" />
                <b><p className='error'>{errors.dateOfBirth}</p></b>
                <div className='radio-div'>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="male" checked/><b>Male</b>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="female"/><b>Female</b>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="others"/><b>Others</b>
                </div>
                <input type="email" name="emailId" placeholder="Email Id" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.emailId}</p></b>
                <input type="password" name="password" placeholder="Password" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.password}</p></b>
                <input type="password" name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} className='input'/>
                <b><p className='error'>{errors.confirmPassword}</p></b>
                <div className='button-register'>
                    <button className='login-btn' onClick={handleRegister} disabled={isDisable}><b>Register</b></button>
                    <p className='register-btn'> <b>Having an Account!</b> <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Register;