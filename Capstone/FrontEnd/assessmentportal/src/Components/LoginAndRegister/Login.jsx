import { useState,useEffect } from 'react';
import './Login.scss'
import Swal from 'sweetalert2';
import axios from 'axios';

const Login = (props) =>{
    const {setRenderComponent,setUserDetails} = props;
    const initialValues = {
        "emailId":"",
        "password":""
    }
    const [loginRequestBody,setLoginRequestBody] = useState(initialValues);
    const [emailError,setEmailError] = useState('');
    const [passwordError,setPasswordError] = useState('');
    const finalValues={
        emailId: loginRequestBody.emailId,
        password: loginRequestBody.password
    }
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name === "emailId"){
            if(!value){
                setEmailError('Email Required');
            }
            else{
                setEmailError('');
                setLoginRequestBody({...loginRequestBody,emailId:value})
            }
        }
        else if(name === "password"){
            if(!value){
                setPasswordError('Password Required');
            }
            else{
                setPasswordError('');
                setLoginRequestBody({...loginRequestBody,password:value})
            }
        }
    }
    const handleLogin = () =>{
        if(finalValues.emailId.length!=0 && finalValues.password.length!=0){
            setPasswordError('');
            setEmailError('');
            axios.post('http://localhost:6060/userLogin',finalValues)
            .then(response=>{
                if(response?.data?.statusCode == 200){
                    if(response?.data?.UserDetails?.UserType === "Admin"){
                        Swal.fire({
                            title: 'Login Successfully',
                            text: 'Redirecting to Admin Dashboard.....',
                            timer: 2000,
                            showConfirmButton:false,
                            showCancelButton:false,
                            icon: "success",
                            background:"#15172b",
                            color:"white",
                        }); 
                        setTimeout(function() {
                            setRenderComponent("navbar") 
                        }, 2000);
                        setUserDetails(response?.data?.UserDetails);
                    }
                    if(response?.data?.UserDetails?.UserType === "Student"){
                        Swal.fire({
                            title: 'Login Successfully',
                            text: 'Redirecting to Student Dashboard.....',
                            timer: 2000,
                            showConfirmButton:false,
                            showCancelButton:false,
                            icon: "success",
                            background:"#15172b",
                            color:"white",
                        });  
                        setTimeout(function() {
                            setRenderComponent("studentNavbar") 
                        }, 2000);
                        setUserDetails(response?.data?.UserDetails);
                    }
                }
            }).catch(error=>{
                if(error?.response?.status == 404){
                    Swal.fire({
                        title: 'Error',
                        text: 'InCorrect Credentials',
                        timer: 2000,
                        showConfirmButton:false,
                        showCancelButton:false,
                        icon: "warning",
                        background:"#15172b",
                        color:"white",
                    });  
                }else if(error?.message == "Network Error"){
                    Swal.fire({
                        title: 'Error',
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
        else if(finalValues.emailId.length!=0){
            setPasswordError('Password Required');
            setEmailError('');
        }
        else if(finalValues.password.length!=0){
            setEmailError('Email Required');
            setPasswordError('');
        }
        else{
            setPasswordError('Password Required');
            setEmailError('Email Required');
        }
    }
    const handleClick = () =>{
        setRenderComponent("register");
    }
    useEffect(() => {
    },[]);
    return(
        <div className='login-body'>
            <div>
                <img src='https://nucleusteq.com/wp-content/uploads/2020/03/logo-header-1.svg' className='logo'/>
            </div>
            <div>
                <h1 className='title'>Login</h1>
                <div className="assessment-heading">ASSESSMENT PORTAL</div>
                <div className='para'>NucleusTeq Members Can Log In Here To Access<br/>The Online Assessments</div>
                <input type="email" placeholder="Email Id" onChange={handleChange} name='emailId' className='input'/>
                <b><p className='errors'>{emailError}</p></b>
                <input type="password" placeholder="Password" onChange={handleChange} name='password' className='input'/>
                <b><p className='errors'>{passwordError}</p></b>
                <div>
                    <button className='login-btn' onClick={handleLogin}><b>Login</b></button>
                    <p className='register-btn'><b>Not having an account! </b> <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Login;