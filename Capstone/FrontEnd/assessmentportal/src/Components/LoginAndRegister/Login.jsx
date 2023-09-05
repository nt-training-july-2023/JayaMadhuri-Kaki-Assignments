import { useState,useEffect } from 'react';
import './Login.scss'
const Login = (props) =>{
    const initialValues = {
        "emailId":"",
        "password":""
    }
    const [loginRequestBody,setLoginRequestBody] = useState(initialValues);
    const [loginErrors,setLoginErrors] = useState({});
    const [isDisable,setIsDisable] = useState(true);
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
        setRenderComponent("register")
    }
    const {setRenderComponent} = props;
    const handleClick = () =>{
        setRenderComponent("register");
    }
    useEffect(() => {
        if (loginErrors?.emailId?.length == 0 && loginErrors?.password?.length == 0) {
            setIsDisable(false);
        }
        else if(loginErrors?.emailId?.length != 0 || loginErrors?.password?.length != 0){
            setIsDisable(true);
        }
    },[loginErrors]);
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
                <b><p>{loginErrors.emailId}</p></b>
                <input type="password" placeholder="Password" onChange={handleChange} name='password' className='input'/>
                <b><p>{loginErrors.password}</p></b>
                <div>
                    <button className='login-btn' onClick={handleLogin}><b>Login</b></button>
                    <p className='register-btn'><b>Not having an account! </b> <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
                </div>
            </div>
        </div>
    )
}

export default Login;