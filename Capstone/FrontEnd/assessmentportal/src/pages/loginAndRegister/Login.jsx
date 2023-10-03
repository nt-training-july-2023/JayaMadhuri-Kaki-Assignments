import { useState, useEffect } from 'react';
import '../../styles/Login.scss';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UsersUrl from '../../service/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import EmailInput from '../../components/input/EmailInput';
import PasswordInput from '../../components/input/PasswordInput';
import FormButton from '../../components/button/FormButton';
import logo from '../../assests/images/loginAndRegister/logo.svg';
import Success from '../../components/sweetAlert/Success';
import Warning from '../../components/sweetAlert/Warning';

const Login = (props) => {
    const { setRenderComponent, setUserDetails } = props;
    const initialValues = {
        "emailId": "",
        "password": ""
    }
    const [loginRequestBody, setLoginRequestBody] = useState(initialValues);
    const [emailError, setEmailError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [passwordVisible, setPasswordVisible] = useState("false")
    const finalValues = {
        emailId: loginRequestBody.emailId,
        password: loginRequestBody.password
    }
    const togglePasswordVisible = () => {
        setPasswordVisible(!passwordVisible);
    };
    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === "emailId") {
            if (!value) {
                setEmailError('Email Required');
            }
            else {
                setEmailError('');
                setLoginRequestBody({ ...loginRequestBody, emailId: value })
            }
        }
        else if (name === "password") {
            if (!value) {
                setPasswordError('Password Required');
            }
            else {
                setPasswordError('');
                setLoginRequestBody({ ...loginRequestBody, password: value })
            }
        }
    }
    const handleLogin = () => {
        if (finalValues.emailId.length != 0 && finalValues.password.length != 0) {
            setPasswordError('');
            setEmailError('');
            UsersUrl.userLogin(finalValues)
                .then(response => {
                    if (response?.data?.statusCode == 200) {
                        localStorage.setItem("UserDetails",JSON.stringify(response?.data?.responseData));
                        localStorage.setItem("Current_Window","navbar");
                        localStorage.setItem("Current_SubWindow","category")
                        localStorage.setItem("LastVisited_Window","login");
                        if (response?.data?.responseData?.UserType === "Admin") {
                            Success.render(sweetAlertMessages.LOGIN_SUCCESS)
                            setTimeout(function () {
                                setRenderComponent("navbar")
                            }, 1500);
                            setUserDetails(response?.data?.responseData);
                        } else if (response?.data?.responseData?.UserType === "Student") {
                            Success.render(sweetAlertMessages.LOGIN_SUCCESS)
                            setTimeout(function () {
                                setRenderComponent("navbar")
                            }, 1500);
                            setUserDetails(response?.data?.responseData);
                        } else {
                            Warning.render(sweetAlertMessages.SOMETHING_WENT_WRONG)
                        }
                    }
                }).catch(error => {
                    if (error?.response?.status == 401) {
                        Warning.render(sweetAlertMessages.INCORRECT_CREDENTIALS)
                    } else if (error?.message == "Network Error") {
                        Warning.render(sweetAlertMessages.NETWORK_ERROR)
                    }
                })
        }
        else if (finalValues.emailId.length != 0) {
            setPasswordError('Password Required');
            setEmailError('');
        }
        else if (finalValues.password.length != 0) {
            setEmailError('Email Required');
            setPasswordError('');
        }
        else {
            setPasswordError('Password Required');
            setEmailError('Email Required');
        }
    }
    const handleClick = () => {
        setRenderComponent("register");
    }
    useEffect(() => {
    }, []);
    return (
        <div className='login-body'>
            <div>
                <img src={logo} className='logo' />
            </div>
            <div>
                <h1 className='title'>Login</h1>
                <div className="assessment-heading">ASSESSMENT PORTAL</div>
                <div className='para'>NucleusTeq Members Can Log In Here To Access<br />The Online Assessments</div>
                <EmailInput onChange={handleChange}/>
                <b><p className='errors'>{emailError}</p></b>
                <div className='password-container'>
                    <PasswordInput type={passwordVisible ? 'password' : 'text'} placeholder="Password" onChange={handleChange} name='password'/>
                    <FormButton className="toggle-password" onClick={togglePasswordVisible} >
                        {passwordVisible ? <FaEyeSlash /> : <FaEye />}
                    </FormButton>
                </div>
                <b><p className='errors'>{passwordError}</p></b>
                <div>
                    <FormButton className='login-btn' onClick={handleLogin}><b>Login</b></FormButton>
                    <p className='register-btn'><b>Not having an account! </b> <FormButton onClick={handleClick} className='click-btn'><b>Click here</b></FormButton></p>
                </div>
            </div>
        </div>
    )
}
export default Login;