import { useState } from 'react'
import '../Styles/Register.scss';
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import Swal from 'sweetalert2'
import UsersUrl from '../../Services/Url';

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
              Swal.fire({
                text: 'Validation Successful........',
                timer: 2000,
                showConfirmButton: false,
                showCancelButton: false,
                icon: "info",
                background: "#15172b",
                color: "white",
              })
              setEmail(registerRequestBody.emailId)
              setButtonName("Next")
            }
          }).catch(error => {
            if (error?.response?.status == 409) {
              Swal.fire({
                text: 'An Account already exists with this Email',
                icon: "warning",
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
            title: 'User Registered Successfully',
            text: 'Redirecting to Login page.....',
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: "success",
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
            title: 'Error',
            text: 'NetWork Error',
            timer: 2000,
            showConfirmButton: false,
            showCancelButton: false,
            icon: "warning",
            background: "#15172b",
            color: "white",
          });
        } else {
          Swal.fire({
            title: 'Error',
            text: 'Enter Valid Date! Age should be Minimum 18',
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
        handleRegister();
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
        <img src='https://nucleusteq.com/wp-content/uploads/2020/03/logo-header-1.svg' className='logo' />
      </div>
      <div className="form">
        <h1 className='title'>Sign Up</h1>
        {flags?.emailIdFlag && <><input type="email" name="emailId" placeholder="Email Id" value={registerRequestBody?.emailId} onChange={handleChange} className='input' />
          <b><p className='error'>{errors.emailId}</p></b></>}
        {flags?.passwordFlag && <><div className="password-container"><input type={passwordVisible ? 'password' : 'text'} name="password" placeholder="Password" value={registerRequestBody?.password} onChange={handleChange} className='input' />
          <button className="toggle-password" onClick={togglePasswordVisible}>
            {passwordVisible ? <FaEyeSlash /> : <FaEye />}
          </button>
        </div>
          <b><p className='error'>{errors.password}</p></b>
          <div className="password-container"><input type={confirmPasswordVisible ? 'password' : 'text'} name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} value={registerRequestBody?.confirmPassword} className='input' />
            <button className="toggle-password" onClick={toggleConfirmPasswordVisible}>
              {confirmPasswordVisible ? <FaEyeSlash /> : <FaEye />}
            </button>
          </div>
          <b><p className='error'>{errors.confirmPassword}</p></b> </>}
        {flags?.detailsFlag && <><input type="text" name="firstName" placeholder="First Name" onChange={handleChange} value={registerRequestBody?.firstName} className='input' />
          <b><p className='error'>{errors.firstName}</p></b>
          <input type="text" name="lastName" placeholder="Last Name" onChange={handleChange} value={registerRequestBody?.lastName} className='input' />
          <b><p className='error'>{errors.lastName}</p></b>
          <input type="date" name="dateOfBirth" placeholder="Date of Birth" onChange={handleChange} value={registerRequestBody?.dateOfBirth} className='input' />
          <b><p className='error'>{errors.dateOfBirth}</p></b>
          <div className='radio-div'>
            <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="male" checked /><b>Male</b>
            <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="female" /><b>Female</b>
            <input type="radio" className='radio-input' onChange={handleChangeRadio} name='gender' value="others" /><b>Others</b>
          </div></>}
        <div className='button-register'>
          <button className='login-btn' onClick={handleCommonButtonClick}><b>{buttonName}</b></button>
          {(flags?.passwordFlag || flags?.detailsFlag) && <button className='login-btn'
            onClick={handleBack}><b>Back</b></button>}
          <p className='register-btn'> <b>Having an Account!</b> <button onClick={handleClick} className='click-btn'><b>Click here</b></button></p>
        </div>
      </div>
    </div>
  )
}
export default Register;