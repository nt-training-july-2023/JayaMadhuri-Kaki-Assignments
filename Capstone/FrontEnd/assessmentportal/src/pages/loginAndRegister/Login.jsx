import { useState, useEffect } from 'react';
import '../../styles/Login.scss';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UsersUrl from '../../service/Url';
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import Input from '../../components/input/Input';
import Button from '../../components/button/Button';
import logo from '../../assets/images/loginAndRegister/logo.svg';
import Alert from '../../components/sweetAlert/Alert';

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
                setEmailError(errorMessages.EMAIL_REQUIRED);
            }
            else {
                setEmailError('');
                setLoginRequestBody({ ...loginRequestBody, emailId: value })
            }
        }
        else if (name === "password") {
            if (!value) {
                setPasswordError(errorMessages.PASSWORD_REQUIRED);
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
                            Alert.Success(sweetAlertMessages.LOGIN_SUCCESS)
                            setTimeout(function () {
                                setRenderComponent("navbar")
                            }, 1500);
                            setUserDetails(response?.data?.responseData);
                        } else if (response?.data?.responseData?.UserType === "Student") {
                            Alert.Success(sweetAlertMessages.LOGIN_SUCCESS)
                            setTimeout(function () {
                                setRenderComponent("navbar")
                            }, 1500);
                            setUserDetails(response?.data?.responseData);
                        } else {
                            Alert.Warning(sweetAlertMessages.SOMETHING_WENT_WRONG)
                        }
                    }
                }).catch(error => {
                    if (error?.response?.status == 401) {
                        Alert.Warning(sweetAlertMessages.INCORRECT_CREDENTIALS)
                    } else if (error?.message == "Network Error") {
                        Alert.Warning(sweetAlertMessages.NETWORK_ERROR)
                    }
                })
        }
        else if (finalValues.emailId.length != 0) {
            setPasswordError(errorMessages.PASSWORD_REQUIRED);
            setEmailError('');
        }
        else if (finalValues.password.length != 0) {
            setEmailError(errorMessages.EMAIL_REQUIRED);
            setPasswordError('');
        }
        else {
            setPasswordError(errorMessages.PASSWORD_REQUIRED);
            setEmailError(errorMessages.EMAIL_REQUIRED);
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
                <Input className="input" type="email" name="emailId" placeholder="Email Id" onChange={handleChange}/>
                <b><p className='errors'>{emailError}</p></b>
                <div className='password-container'>
                    <Input type={passwordVisible ? 'password' : 'text'} placeholder="Password" onChange={handleChange} name='password' className="input"/>
                    <Button className="toggle-password" onClick={togglePasswordVisible} >
                        {passwordVisible ? <FaEyeSlash /> : <FaEye />}
                    </Button>
                </div>
                <b><p className='errors'>{passwordError}</p></b>
                <div>
                    <Button className='login-button' onClick={handleLogin}><b>Login</b></Button>
                    <p className='register-button'><b>Not having an account! </b> <Button onClick={handleClick} className='click-button'><b>Click here</b></Button></p>
                </div>
            </div>
        </div>
    )
}
export default Login;