import { useState } from 'react'
import '../../styles/Register.scss';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UsersUrl from '../../service/Url';
import {errorMessages, sweetAlertMessages}  from "../../constants/ValidationMessages"
import Input from '../../components/input/Input';
import Button from '../../components/button/Button';
import logo from '../../assets/images/loginAndRegister/logo.svg';
import Alert from '../../components/sweetAlert/Alert';
import HeadingOne from '../../components/heading/HeadingOne';
import Paragraph from '../../components/paragraph/Paragraph';

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
          setErrors({ ...errors, emailId: errorMessages.EMAIL_REQUIRED });
        } else if (!/^[A-Z0-9a-z.+_-]+@nucleusteq[.]com$/.test(value)) {
          setErrors({ ...errors, emailId: errorMessages.INVALID_EMAIL});
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
            setErrors({ ...errors, password: errorMessages.PASSWORD_REQUIRED });
            break;
          case !re.digit.test(value):
            setErrors({ ...errors, password: errorMessages.PASSWORD_CONTAIN_NUMBER });
            break;
          case !re.capital.test(value):
            setErrors({ ...errors, password: errorMessages.PASSWORD_CONTAIN_LETTER });
            break;
          case !re.specialChar.test(value):
            setErrors({ ...errors, password: errorMessages.PASSWORD_CONTAIN_SPECIALCHAR});
            break;
          case !re.length.test(value):
            setErrors({ ...errors, password: errorMessages.PASSWORD_CONTAIN_EIGHTCHAR});
            break;
          default:
            setErrors({ ...errors, password: "" });
            break;
        }
        break;

      case "firstName":
        setRegisterRequestBody({ ...registerRequestBody, firstName: value });
        if (!value) {
          setErrors({ ...errors, firstName: errorMessages.FIRST_NAME_REQUIRED });
        } else {
          setErrors({ ...errors, firstName: "" });
        }
        break;

      case "lastName":
        setRegisterRequestBody({ ...registerRequestBody, lastName: value });
        if (!value) {
          setErrors({ ...errors, lastName: errorMessages.LAST_NAME_REQUIRED });
        } else {
          setErrors({ ...errors, lastName: "" });
        }
        break;

      case "dateOfBirth":
        setRegisterRequestBody({ ...registerRequestBody, dateOfBirth: value });
        if (!value) {
          setErrors({ ...errors, dateOfBirth: errorMessages.DATE_OF_BIRTH_REQUIRED});
        } else {
          setErrors({ ...errors, dateOfBirth: "" });
        }
        break;

      case "confirmPassword":
        setRegisterRequestBody({ ...registerRequestBody, confirmPassword: value });
        if (!value) {
          setErrors({ ...errors, confirmPassword: errorMessages.CONFIRM_PASSWORD_REQUIRED });
        } else if (password !== value) {
          setErrors({ ...errors, confirmPassword: errorMessages.PASSWORD_DO_NOT_MATCH });
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
            if (response?.status == 200) {
              setEmail(registerRequestBody.emailId)
              setButtonName("Next")
            }
          }).catch(error => {
            if (error?.response?.status == 409) {
              Alert.Warning(sweetAlertMessages.EMAIL_ALREADY_EXISTS)
            }else if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
              Alert.Warning(sweetAlertMessages.SERVER_DOWN)
            } 
          })
      } else {
        setErrors({ ...errors, emailId: errorMessages.EMAIL_REQUIRED });
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
          Alert.Success(sweetAlertMessages.LOGIN_SUCCESS,sweetAlertMessages.LOGIN_REDIRECT)
          setTimeout(function () {
            setRenderComponent("login")
          }, 1500)
        }
      })
      .catch(error => {
        if (error?.message == sweetAlertMessages.NETWORK_ERROR) {
          Alert.Warning(sweetAlertMessages.SERVER_DOWN)
        } else if(error?.response?.data?.message == "Age should be atleast 18 years old"){
          Alert.Warning(sweetAlertMessages.AGE_VALIDATION)
        }else if(error?.response?.status == 400){
          Alert.Warning(error?.response?.data?.message)
        }else{
          console.error(error)
        }
      })
  }
  const handleValidateRegister = () => {
    let firstNameError = "", lastNameError = "", dateOfBirthError = "";
    switch (true) {
      case registerRequestBody?.firstName.length < 1:
        firstNameError = errorMessages.FIRST_NAME_REQUIRED;
      case registerRequestBody?.lastName.length < 1:
        lastNameError = errorMessages.LAST_NAME_REQUIRED
      case registerRequestBody?.dateOfBirth.length < 1:
        dateOfBirthError = errorMessages.DATE_OF_BIRTH_REQUIRED
      default:
        setErrors({ ...errors, firstName: firstNameError, lastName: lastNameError, dateOfBirth: dateOfBirthError });
        if(errors.firstName === "" && errors.lastName === "" && errors.dateOfBirth === "" && registerRequestBody?.firstName.length > 1 && registerRequestBody?.lastName.length > 1 && registerRequestBody?.dateOfBirth.length > 1){
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
          setErrors({ ...errors, confirmPassword: errorMessages.CONFIRM_PASSWORD_REQUIRED });
        } else if (registerRequestBody?.password.length === 0 && registerRequestBody?.confirmPassword.length > 0) {
          setErrors({ ...errors, password: errorMessages.PASSWORD_REQUIRED });
        } else {
          setErrors({ ...errors, password: errorMessages.PASSWORD_REQUIRED, confirmPassword: errorMessages.CONFIRM_PASSWORD_REQUIRED });
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
        <HeadingOne className='title' children="Sign Up"/>
        {flags?.emailIdFlag && <><Input className="input" type="email" name="emailId" placeholder="Email Id" value={registerRequestBody?.emailId} onChange={handleChange}/>
          <b><Paragraph className='error' children={errors.emailId}/></b></>}
        {flags?.passwordFlag && <><div className="password-container"><Input type={passwordVisible ? 'password' : 'text'} className="input" name="password" placeholder="Password" value={registerRequestBody?.password} onChange={handleChange}/>
          <Button className="toggle-password" onClick={togglePasswordVisible} children={passwordVisible ? <FaEyeSlash /> : <FaEye />}/>
        </div>
          <b><Paragraph className='error' children={errors.password}/></b>
          <div className="password-container"><Input type={confirmPasswordVisible ? 'password' : 'text'} className="input" name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} value={registerRequestBody?.confirmPassword}/>
            <Button className="toggle-password" onClick={toggleConfirmPasswordVisible} children={confirmPasswordVisible ? <FaEyeSlash /> : <FaEye />}/>
          </div>
          <b><Paragraph className='error' children={errors.confirmPassword}/></b> </>}
        {flags?.detailsFlag && <><Input type="text" name="firstName" placeholder="First Name" onChange={handleChange} value={registerRequestBody?.firstName} className='input' />
          <b><Paragraph className='error' children={errors.firstName}/></b>
          <Input type="text" name="lastName" placeholder="Last Name" onChange={handleChange} value={registerRequestBody?.lastName} className='input' />
          <b><Paragraph className='error' children={errors.lastName}/></b>
          <Input type="date" name="dateOfBirth" className={registerRequestBody?.dateOfBirth ? 'input': 'input input-date-color'} onChange={handleChange} value={registerRequestBody?.dateOfBirth}/>
          <b><Paragraph className='error' children={errors.dateOfBirth}/></b>
          <div className='radio-div'>
            <Input type="radio" name="gender" onChange={handleChangeRadio} value="male" checked="true"/><b>Male</b>
            <Input type="radio" name="gender" onChange={handleChangeRadio} value="female" /><b>Female</b>
            <Input type="radio" name="gender" onChange={handleChangeRadio} value="others" /><b>Others</b>
          </div></>}
        <div className='button-div'>
          <Button className='login-button' onClick={handleCommonButtonClick} children={<b>{buttonName}</b>}/>
          {(flags?.passwordFlag || flags?.detailsFlag) && <Button className='login-button'
            onClick={handleBack} children={<b>Back</b>}/>}
          <Paragraph className='register-button'> <b>Having an Account!</b> <Button onClick={handleClick} className='click-button' children={<b>Click here</b>}/></Paragraph>
        </div>
      </div>
    </div>
  )
}
export default Register;