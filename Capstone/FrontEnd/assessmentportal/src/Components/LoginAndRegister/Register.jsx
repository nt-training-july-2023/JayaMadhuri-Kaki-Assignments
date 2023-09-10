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
    const initialErrors = {
        firstName:"",
        lastName:"",
        dateOfBirth:"",
        emailId: "", 
        password: "",
        confirmPassword:""
    }
    const {setRenderComponent} = props;
    const [errors,setErrors] = useState(initialErrors);
    const [registerRequestBody,setRegisterRequestBody] = useState(initialValues);
    const [password,setPassword] = useState("")
    const [email,setEmail] = useState("")
    const [flags,setFlags] = useState({
      emailIdFlag: true,
      passwordFlag:false,
      detailsFlag:false,
    });     
    const [buttonName,setButtonName] = useState("Validate")
    const handleClick = () =>{
        setRenderComponent("login");
    }
    const handleChangeRadio = (e) =>{
        setRegisterRequestBody({...registerRequestBody,gender:e.target.value})
    }
    const handleChange = (e) => {
        const { name, value } = e.target;
        const re = {
            capital: /(?=.*[A-Z])/,
            length: /(?=.{7,40}$)/,
            specialChar: /[ -\/:-@\[-\`{-~]/,
            digit: /(?=.*[0-9])/,
        };
        switch (name) {
          case "emailId":
            if (!value) {
              setErrors({ ...errors, emailId: "Email Required" });
            } else if (!/^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$/.test(value)) {
              setErrors({ ...errors, emailId: "Email should contain .nucleusteq" });
            } else {
              setErrors({ ...errors, emailId: "" });
              setRegisterRequestBody({ ...registerRequestBody, emailId: value });
            }
            break;
      
          case "password":
            setPassword(value);
            switch (true) {
                case !value:
                  setErrors({ ...errors, password: "Password Required" });
                  break;
                case !re.digit.test(value):
                  setErrors({ ...errors, password: "Must contain a number" });
                  break;
                case !re.capital.test(value):
                  setErrors({ ...errors, password: "One Capital letter required" });
                  break;
                case !re.specialChar.test(value):
                  setErrors({ ...errors, password: "No special character" });
                  break;
                case !re.length.test(value):
                  setErrors({ ...errors, password: "Minimum 8 characters required" });
                  break;
                default:
                  setErrors({ ...errors, password: "" });
                  setRegisterRequestBody({ ...registerRequestBody, password: value });
                  break;
              }              
            break;
      
          case "firstName":
            if (!value) {
              setErrors({ ...errors, firstName: "First Name Required" });
            } else {
              setErrors({ ...errors, firstName: "" });
              setRegisterRequestBody({ ...registerRequestBody, firstName: value });
            }
            break;
      
          case "lastName":
            if (!value) {
              setErrors({ ...errors, lastName: "Last Name Required" });
            } else {
              setErrors({ ...errors, lastName: "" });
              setRegisterRequestBody({ ...registerRequestBody, lastName: value });
            }
            break;

          case "dateOfBirth":
            if (!value) {
              setErrors({ ...errors, dateOfBirth: "Date of Birth Required" });
            } else {
              setErrors({ ...errors, dateOfBirth: "" });
              setRegisterRequestBody({ ...registerRequestBody, dateOfBirth: value });
            }
            break;

          case "confirmPassword":
            if (!value) {
              setErrors({ ...errors, confirmPassword: "Confirm your password" });
            } else if (password !== value) {
              setErrors({ ...errors, confirmPassword: "Passwords do not match" });
            } else {
              setErrors({ ...errors, confirmPassword: "" });
              setRegisterRequestBody({ ...registerRequestBody, confirmPassword: value });
            }
            break;
            
          default:
            setRegisterRequestBody({ ...registerRequestBody, [name]: value });
            break;
        }
    };
    const emailValidation = () =>{
       if(registerRequestBody?.emailId.length>0){
        axios.get(`http://localhost:6060/getUsers/${registerRequestBody.emailId}`)
        .then(response=>{
           if(response?.data?.statusCode == 200){
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
           }
        }).catch(error=>{
         if(error?.response?.status==404){
          Swal.fire({
            text:'Validated Successfully.......',
            timer:1000,
            showConfirmButton:false,
            color:'white',
            background:'#15172b'
          })
           setEmail(registerRequestBody.emailId)
           setButtonName("Next")
         }
        })
       }else{
        setErrors({ ...errors, emailId: "Email Required" });
       }
    }
    const finalValues = {
        firstName:registerRequestBody.firstName,
        lastName:registerRequestBody.lastName,
        dateOfBirth:registerRequestBody.dateOfBirth,
        gender:registerRequestBody.gender,
        emailId:registerRequestBody.emailId,
        password:registerRequestBody.password
    }
    const handleRegister = () =>{
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
    const handleNext = () =>{
      switch(true){
        case flags?.emailIdFlag:
          if(JSON.stringify(errors) == JSON.stringify(initialErrors)){
            if(registerRequestBody.emailId === email){
              console.log(flags)
              setFlags({ ...flags,emailIdFlag:false,passwordFlag:true,detailsFlag:false });
              console.log(flags)
            }else{
              Swal.fire({
                title: 'Error.....',
                text: 'Email Id Changed Validate Again',
                timer: 2000,
                showConfirmButton:false,
                showCancelButton:false,
                icon: "warning",
                background:"#15172b",
                color:"white",
              });  
              setButtonName("Validate")
            }
          }
          break;
        case flags?.passwordFlag:
          if(JSON.stringify(errors) == JSON.stringify(initialErrors)){
            if(registerRequestBody?.password.length>0 && registerRequestBody?.confirmPassword.length>0){
              setFlags({ ...flags,emailIdFlag:false,passwordFlag:false,detailsFlag:true });
              setButtonName("Register")
              setErrors({ ...errors, firstName: "First Name Required",lastName: "Last Name Required",dateOfBirth: "Date of Birth Required"});
            }else if(registerRequestBody?.password.length>0 && registerRequestBody?.confirmPassword.length===0){
              setErrors({ ...errors, confirmPassword: "Confirm your password" });
            }else if(registerRequestBody?.password.length===0 && registerRequestBody?.confirmPassword.length>0){
              setErrors({ ...errors, password: "Password Required" });
            }else{
              setErrors({ ...errors, password: "Password Required" ,confirmPassword: "Confirm your password" });
            }
          }
          break;
          case flags?.detailsFlag:
            if(JSON.stringify(errors) == JSON.stringify(initialErrors)){
              handleRegister();
            }
            break;
          default:
            console.log("something happend")
            break;
      }
    }
    return(
        <div className="body">
             <div>
                <img src='https://nucleusteq.com/wp-content/uploads/2020/03/logo-header-1.svg' className='logo'/>
            </div>
            <div className="form">
                <h1 className='title'>Sign Up</h1>
                {flags?.emailIdFlag && <><input type="email" name="emailId" placeholder="Email Id" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.emailId}</p></b></>}
                {flags?.passwordFlag && <><input type="password" name="password" placeholder="Password" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.password}</p></b>
                <input type="password" name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} className='input'/>
                <b><p className='error'>{errors.confirmPassword}</p></b> </> }
                {flags?.detailsFlag && <><input type="text" name="firstName" placeholder="First Name" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.firstName}</p></b>
                <input type="text" name="lastName" placeholder="Last Name" onChange={handleChange} className='input' />
                <b><p className='error'>{errors.lastName}</p></b>
                <input type="date" name="dateOfBirth" placeholder="Date of Birth" onChange={handleChange} className='input' max="2023-09-06" />
                <b><p className='error'>{errors.dateOfBirth}</p></b>
                <div className='radio-div'>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="male" checked/><b>Male</b>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="female"/><b>Female</b>
                    <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="others"/><b>Others</b>
                </div></>}
                <div className='button-register'>
                    <button className='login-btn' onClick={buttonName == "Validate"?(emailValidation):(handleNext)}><b>{buttonName}</b></button>
                    {/* {flags.passwordFlag || flags.detailsFlag ? (<button className='login-btn'
                     onClick={flags.passwordFlag?(setFlags({ ...flags,emailIdFlag:true,passwordFlag:false,detailsFlag:false}))
                     :(setFlags({ ...flags,emailIdFlag:false,passwordFlag:true,detailsFlag:false })
                    )}><b>Back</b></button>):(null)} */}
                    <p className='register-btn'> <b>Having an Account!</b> <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Register;