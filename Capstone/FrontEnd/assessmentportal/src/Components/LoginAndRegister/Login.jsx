import { useState,useEffect } from 'react';
import './Login.scss'
import swal from 'sweetalert';
import axios from 'axios';

const Login = (props) =>{
    const initialValues = {
        "emailId":"",
        "password":""
    }
    const [loginRequestBody,setLoginRequestBody] = useState(initialValues);
    const [emailError,setEmailError] = useState({});
    const [passwordError,setPasswordError] = useState({});
    const finalValues={
        emailId: loginRequestBody.emailId,
        password: loginRequestBody.password
    }
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name === "emailId"){
            if(!value){
                setPasswordError({...passwordError,emailId:'Email Required'});
            }
            else{
                setPasswordError({...passwordError,emailId:''});
                setLoginRequestBody({...loginRequestBody,emailId:value})
            }
        }
        else if(name === "password"){
            if(!value){
                setEmailError({...emailError,password:'Password Required'});
            }
            else{
                setEmailError({...emailError,password:''});
                setLoginRequestBody({...loginRequestBody,password:value})
            }
        }
    }
    const handleLogin = () =>{
        if(finalValues.emailId.length!=0 && finalValues.password.length!=0){
            setPasswordError({...passwordError,password:''});
            setEmailError({...emailError,emailId:''});
            axios.post('http://localhost:6060/userLogin',finalValues)
            .then(response=>{
                if(response?.data?.statusCode == 200){
                    if(response?.data?.UserDetails?.UserType === "Admin"){
                        swal({
                            title: 'Redirecting to Admin Dashboard.....',
                            text: 'Login Successfully',
                            timer: 2000,
                            showCancelButton: false,
                            showConfirmButton: false
                        }); 
                        setTimeout(function() {
                            setRenderComponent("adminDashboard") 
                        }, 1000);
                    }
                    if(response?.data?.UserDetails?.UserType === "Student"){
                        swal({
                            title: 'Redirecting to Student Dashboard.....',
                            text: 'Login Successfully',
                            timer: 2000,
                            showCancelButton: false,
                            showConfirmButton: false
                        });  
                        setTimeout(function() {
                            setRenderComponent("studentDashboard") 
                        }, 1000);
                    }
                }
            }).catch(error=>{
                if(error?.response?.status == 404){
                    swal({
                        title: 'Oops.....',
                        text: 'InCorrect Credentials',
                        timer: 2000,
                        showCancelButton: false,
                        showConfirmButton: false
                    });  
                }
            })
        }
        else if(finalValues.emailId.length!=0){
            setPasswordError({...passwordError,password:'Password Required'});
            setEmailError({...emailError,emailId:''});
        }
        else if(finalValues.password.length!=0){
            setEmailError({...emailError,emailId:'Email Required'});
            setPasswordError({...passwordError,password:''});
        }
        else{
            setPasswordError({...passwordError,password:'Password Required'});
            setEmailError({...emailError,emailId:'Email Required'});
        }
    }
    const {setRenderComponent} = props;
    const handleClick = () =>{
        setRenderComponent("register");
    }
    useEffect(() => {
        if(finalValues.emailId.length!=0 && finalValues.password.length!=0){
            setPasswordError({...passwordError,password:''});
            setEmailError({...emailError,emailId:''});
        }else if(finalValues.emailId.length!=0 && finalValues.password.length==0 ){
            setEmailError({...emailError,emailId:''});
        }else if(finalValues.password.length!=0 && finalValues.emailId.length!=0){
            setPasswordError({...passwordError,password:''});
        }
    },[emailError,passwordError]);
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
                <b><p>{emailError.emailId}</p></b>
                <input type="password" placeholder="Password" onChange={handleChange} name='password' className='input'/>
                <b><p>{passwordError.password}</p></b>
                <div>
                    <button className='login-btn' onClick={handleLogin}><b>Login</b></button>
                    <p className='register-btn'><b>Not having an account! </b> <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Login;