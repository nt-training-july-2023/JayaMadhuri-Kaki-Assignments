import { useState } from 'react'
import '../../styles/Register.scss';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import Swal from 'sweetalert2'
import UsersUrl from '../../services/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import EmailInput from '../../components/input/EmailInput';
import PasswordInput from '../../components/input/PasswordInput';
import TextInput from '../../components/input/TextInput';
import DateInput from '../../components/input/DateInput';
import RadioInput from '../../components/input/RadioInput';
import FormButton from '../../components/button/FormButton';
import logo from '../../assests/images/loginAndRegister/logo.svg';

const Register = (props) => {
  const { setRenderComponent } = props;
  const initialValues = {
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    gender: "male",
    emailId: "",
    password: "",
    confirmPassword: ""
  };
  const initialErrors = {
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    emailId: "",
    password: "",
    confirmPassword: ""
  }
  const [errors, setErrors] = useState(initialErrors);
  const [registerRequestBody, setRegisterRequestBody] = useState(initialValues);
  const [password, setPassword] = useState("")
  const [email, setEmail] = useState("")
  const [passwordVisible, setPasswordVisible] = useState("false")
  const [confirmPasswordVisible, setConfirmPasswordVisible] = useState("false")
  const [buttonName, setButtonName] = useState("Validate")
  const [flags, setFlags] = useState({
    emailIdFlag: true,
    passwordFlag: false,
    detailsFlag: false,
  });
  const togglePasswordVisible = () => {
    setPasswordVisible(!passwordVisible);
  };
  const toggleConfirmPasswordVisible = () => {
    setConfirmPasswordVisible(!confirmPasswordVisible);
  };
  const handleClick = () => {
    setRenderComponent("login");
  }
  const handleChangeRadio = (e) => {
    setRegisterRequestBody({ ...registerRequestBody, gender: e.target.value })
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
        setRegisterRequestBody({ ...registerRequestBody, emailId: value });
        if (value.length < 1) {
          setErrors({ ...errors, emailId: "Email Required" });
        } else if (!/^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$/.test(value)) {
          setErrors({ ...errors, emailId: "Email should contain @nucleusteq" });
        } else {
          setErrors({ ...errors, emailId: "" });
        }
        if (value != email) {
          setButtonName("Validate")
        } else {
          setButtonName("Next")
        }
        break;

      case "password":
        setPassword(value);
        setRegisterRequestBody({ ...registerRequestBody, password: value });
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
            break;
        }
        break;

      case "firstName":
        setRegisterRequestBody({ ...registerRequestBody, firstName: value });
        if (!value) {
          setErrors({ ...errors, firstName: "First Name Required" });
        } else {
          setErrors({ ...errors, firstName: "" });
        }
        break;

      case "lastName":
        setRegisterRequestBody({ ...registerRequestBody, lastName: value });
        if (!value) {
          setErrors({ ...errors, lastName: "Last Name Required" });
        } else {
          setErrors({ ...errors, lastName: "" });
        }
        break;

      case "dateOfBirth":
        setRegisterRequestBody({ ...registerRequestBody, dateOfBirth: value });
        if (!value) {
          setErrors({ ...errors, dateOfBirth: "Date of Birth Required" });
        } else {
          setErrors({ ...errors, dateOfBirth: "" });
        }
        break;

      case "confirmPassword":
        setRegisterRequestBody({ ...registerRequestBody, confirmPassword: value });
        if (!value) {
          setErrors({ ...errors, confirmPassword: "Confirm your password" });
        } else if (password !== value) {
          setErrors({ ...errors, confirmPassword: "Passwords do not match" });
        } else {
          setErrors({ ...errors, confirmPassword: "" });
        }
        break;

      default:
        setRegisterRequestBody({ ...registerRequestBody, [name]: value });
        break;
    }
  };
  const emailValidation = () => {
    if (errors.emailId == "") {
      if (registerRequestBody?.emailId.length > 0) {
        UsersUrl.CheckUserByEmail(registerRequestBody.emailId)
          .then(response => {
            if (response?.data?.statusCode == 200) {
              setEmail(registerRequestBody.emailId)
              setButtonName("Next")
            }
          }).catch(error => {
            if (error?.response?.status == 409) {
              Swal.fire({
                text: sweetAlertMessages.EMAIL_ALREADY_EXISTS,
                icon: sweetAlertMessages.WARNING,
                timer: 1000,
                showConfirmButton: false,
                color: 'white',
                background: '#15172b'
              })
            }else{
              Swal.fire({
                text: sweetAlertMessages.NETWORK_ERROR,
                icon: sweetAlertMessages.WARNING,
                timer: 1000,
                showConfirmButton: false,
                color: 'white',
                background: '#15172b'
              })
            }
          })
      } else {
        setErrors({ ...errors, emailId: "Email Required" });
      }
    }
  }
  const finalValues = {
    firstName: registerRequestBody.firstName,
    lastName: registerRequestBody.lastName,
    dateOfBirth: registerRequestBody.dateOfBirth,
    gender: registerRequestBody.gender,
    emailId: registerRequestBody.emailId,
    password: registerRequestBody.password
  }
  const handleRegister = () => {
    UsersUrl.userRegistration(finalValues)
      .then(response => {
        if (response?.data?.statusCode == 200) {
          Swal.fire({
            title: sweetAlertMessages.USER_SUCCESS_REGISTER,
            text: sweetAlertMessages.LOGIN_REDIRECT,
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.SUCCESS,
            background: "#15172b",
            color: "white",
          });
          setTimeout(function () {
            setRenderComponent("login")
          }, 2000)
        }
      })
      .catch(error => {
        if (error?.message == "Network Error") {
          Swal.fire({
            title: sweetAlertMessages.ERROR,
            text: sweetAlertMessages.NETWORK_ERROR,
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.WARNING,
            background: "#15172b",
            color: "white",
          });
        } else {
          Swal.fire({
            title:  sweetAlertMessages.ERROR,
            text: sweetAlertMessages.AGE_VALIDATION,
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.WARNING,
            background: "#15172b",
            color: "white",
          });
        }
      })
  }
  const handleValidateRegister = () => {
    let firstNameError = "", lastNameError = "", dateOfBirthError = "";
    switch (true) {
      case registerRequestBody?.firstName.length < 1:
        firstNameError = "First Name Required";
      case registerRequestBody?.lastName.length < 1:
        lastNameError = "Last Name Required"
      case registerRequestBody?.dateOfBirth.length < 1:
        dateOfBirthError = "Date of Birth Required"
      default:
        setErrors({ ...errors, firstName: firstNameError, lastName: lastNameError, dateOfBirth: dateOfBirthError });
        if(errors.firstName === "" && errors.lastName === "" && errors.dateOfBirth === "" && registerRequestBody?.firstName.length > 1){
          handleRegister();
        }
    }
  }
  const handleNext = () => {
    if (flags?.emailIdFlag) {
      if (errors.emailId == "") {
        if (registerRequestBody.emailId === email) {
          setFlags({ ...flags, emailIdFlag: false, passwordFlag: true, detailsFlag: false });
        }
      }
    } else if (flags?.passwordFlag) {
      if (errors.password == "") {
        if (registerRequestBody?.password.length > 0 && registerRequestBody?.confirmPassword.length > 0) {
          setFlags({ ...flags, emailIdFlag: false, passwordFlag: false, detailsFlag: true });
          setButtonName("Register")
        } else if (registerRequestBody?.password.length > 0 && registerRequestBody?.confirmPassword.length === 0) {
          setErrors({ ...errors, confirmPassword: "Confirm your password" });
        } else if (registerRequestBody?.password.length === 0 && registerRequestBody?.confirmPassword.length > 0) {
          setErrors({ ...errors, password: "Password Required" });
        } else {
          setErrors({ ...errors, password: "Password Required", confirmPassword: "Confirm your password" });
        }
      }
    }
  }
  const handleCommonButtonClick = () => {
    if (buttonName == "Validate") {
      emailValidation()
    } else if (buttonName == "Next") {
      handleNext();
    } else {
      handleValidateRegister();
    }
  }
  const handleBack = () => {
    if (flags?.passwordFlag) {
      setFlags({ ...flags, emailIdFlag: true, passwordFlag: false, detailsFlag: false })
    } else if (flags?.detailsFlag) {
      setFlags({ ...flags, emailIdFlag: false, passwordFlag: true, detailsFlag: false })
    }
    setButtonName("Next")
  }
  return (
    <div className="body">
      <div>
        <img src={logo} className='logo' />
      </div>
      <div className="form">
        <h1 className='title'>Sign Up</h1>
        {flags?.emailIdFlag && <><EmailInput value={registerRequestBody?.emailId} onChange={handleChange}/>
          <b><p className='error'>{errors.emailId}</p></b></>}
        {flags?.passwordFlag && <><div className="password-container"><PasswordInput type={passwordVisible ? 'password' : 'text'} name="password" placeholder="Password" value={registerRequestBody?.password} onChange={handleChange}/>
          <FormButton className="toggle-password" onClick={togglePasswordVisible}>
            {passwordVisible ? <FaEyeSlash /> : <FaEye />}
          </FormButton>
        </div>
          <b><p className='error'>{errors.password}</p></b>
          <div className="password-container"><PasswordInput type={confirmPasswordVisible ? 'password' : 'text'} name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} value={registerRequestBody?.confirmPassword}/>
            <FormButton className="toggle-password" onClick={toggleConfirmPasswordVisible}>
              {confirmPasswordVisible ? <FaEyeSlash /> : <FaEye />}
            </FormButton>
          </div>
          <b><p className='error'>{errors.confirmPassword}</p></b> </>}
        {flags?.detailsFlag && <><TextInput name="firstName" placeholder="First Name" onChange={handleChange} value={registerRequestBody?.firstName} className='input' />
          <b><p className='error'>{errors.firstName}</p></b>
          <TextInput name="lastName" placeholder="Last Name" onChange={handleChange} value={registerRequestBody?.lastName} className='input' />
          <b><p className='error'>{errors.lastName}</p></b>
          <DateInput onChange={handleChange} value={registerRequestBody?.dateOfBirth}/>
          <b><p className='error'>{errors.dateOfBirth}</p></b>
          <div className='radio-div'>
            <RadioInput onChange={handleChangeRadio} value="male" checked="true"/><b>Male</b>
            <RadioInput onChange={handleChangeRadio} value="female" /><b>Female</b>
            <RadioInput onChange={handleChangeRadio} value="others" /><b>Others</b>
          </div></>}
        <div className='button-register'>
          <FormButton className='login-btn' onClick={handleCommonButtonClick}><b>{buttonName}</b></FormButton>
          {(flags?.passwordFlag || flags?.detailsFlag) && <FormButton className='login-btn'
            onClick={handleBack}><b>Back</b></FormButton>}
          <p className='register-btn'> <b>Having an Account!</b> <FormButton onClick={handleClick} className='click-btn'><b>Click here</b></FormButton></p>
        </div>
      </div>
    </div>
  )
}
export default Register;