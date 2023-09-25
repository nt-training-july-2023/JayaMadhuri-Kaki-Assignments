import { useState, useEffect } from 'react';
import '../Styles/Login.scss'
import Swal from 'sweetalert2';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UsersUrl from '../../Services/Url';

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
                        localStorage.setItem("UserDetails",JSON.stringify(response?.data?.UserDetails));
                        localStorage.setItem("Current_Window","navbar");
                        localStorage.setItem("Current_SubWindow","category")
                        localStorage.setItem("LastVisited_Window","login");
                        if (response?.data?.UserDetails?.UserType === "Admin") {
                            Swal.fire({
                                title: 'Login Successfully',
                                timer: 2000,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "success",
                                background: "#15172b",
                                color: "white",
                            });
                            setTimeout(function () {
                                setRenderComponent("navbar")
                            }, 2000);
                            setUserDetails(response?.data?.UserDetails);
                        } else if (response?.data?.UserDetails?.UserType === "Student") {
                            Swal.fire({
                                title: 'Login Successfully',
                                timer: 2000,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "success",
                                background: "#15172b",
                                color: "white",
                            });
                            setTimeout(function () {
                                setRenderComponent("navbar")
                            }, 2000);
                            setUserDetails(response?.data?.UserDetails);
                        } else {
                            Swal.fire({
                                title: 'Login Successfully',
                                text: 'Something went wrong! Cannot found UserType',
                                timer: 2000,
                                showConfirmButton: false,
                                showCancelButton: false,
                                icon: "success",
                                background: "#15172b",
                                color: "white",
                            });
                        }
                    }
                }).catch(error => {
                    if (error?.response?.status == 401) {
                        Swal.fire({
                            title: 'Error',
                            text: 'InCorrect Credentials',
                            timer: 2000,
                            showConfirmButton: false,
                            showCancelButton: false,
                            icon: "warning",
                            background: "#15172b",
                            color: "white",
                        });
                    } else if (error?.message == "Network Error") {
                        Swal.fire({
                            title: 'Error',
                            text: 'NetWork Error',
                            timer: 2000,
                            showConfirmButton: false,
                            showCancelButton: false,
                            icon: "warning",
                            background: "#15172b",
                            color: "white",
                        });
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
                <img src='https://nucleusteq.com/wp-content/uploads/2020/03/logo-header-1.svg' className='logo' />
            </div>
            <div>
                <h1 className='title'>Login</h1>
                <div className="assessment-heading">ASSESSMENT PORTAL</div>
                <div className='para'>NucleusTeq Members Can Log In Here To Access<br />The Online Assessments</div>
                <input type="email" placeholder="Email Id" onChange={handleChange} name='emailId' className='input' />
                <b><p className='errors'>{emailError}</p></b>
                <div className='password-container'>
                    <input type={passwordVisible ? 'password' : 'text'} placeholder="Password" onChange={handleChange} name='password' className='input' style={{ paddingRight: '40px' }} />
                    <button className="toggle-password" onClick={togglePasswordVisible} >
                        {passwordVisible ? <FaEyeSlash /> : <FaEye />}
                    </button>
                </div>
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