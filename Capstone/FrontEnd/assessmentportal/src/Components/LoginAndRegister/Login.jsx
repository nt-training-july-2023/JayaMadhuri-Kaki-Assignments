import { useState,useEffect } from 'react';
import './Login.scss'
const Login = (props) =>{
    const initialValues = {
        "emailId":"",
        "password":""
    }
    const [loginRequestBody,setLoginRequestBody] = useState(initialValues);
    const [loginErrors,setLoginErrors] = useState({});
    const finalValues={
        emailId:"Email Id is required",
        password:"Password is required"
    }
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name === "emailId"){
            if(!value){
                setLoginErrors({...loginErrors,emailId:'Email Required'});
            }
            else{
                setLoginErrors({...loginErrors,emailId:''});
                setLoginRequestBody({...loginRequestBody,emailId:value})
            }
        }
        else if(name === "password"){
            if(!value){
                setLoginErrors({...loginErrors,password:'Password Required'});
            }
            else{
                setLoginErrors({...loginErrors,password:''});
                setLoginRequestBody({...loginRequestBody,password:value})
            }
        }
    }
    const handleLogin = () =>{
        setLoginErrors(finalValues)
        if (loginErrors?.emailId?.length == 0 && loginErrors?.password?.length == 0) {
            
        }
        else if(loginErrors?.emailId?.length != 0 || loginErrors?.password?.length != 0){
            setRenderComponent("login")
        }
    }
    const {setRenderComponent} = props;
    const handleClick = () =>{
        setRenderComponent("register");
    }
    return(
        <div className='login-body'>
            <div className="login-form">
                <h1>Login</h1>
                <input type="email" placeholder="Email Id" onChange={handleChange} name='emailId' className='input'/>
                <p className='error'>{loginErrors.emailId}</p>
                <input type="password" placeholder="Password" onChange={handleChange} name='password' className='input'/>
                <p className='error'>{loginErrors.password}</p>
                <div className='btn'>
                    <button className='login-btn' onClick={handleLogin}>Login</button>
                    <p className='register-btn'> Not having an account! <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Login;