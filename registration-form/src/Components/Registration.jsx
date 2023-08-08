import './Registration.scss';
import React,{useState,useEffect} from 'react';
function Registration() {
    const details = {
        fullName: "",
        email: "",
        password: "",
        phone: "",
        username: "",
        confirm: "",
        gender: ""
    };
    const [registerRequestBody,setRegisterRequestBody] = useState(details);
    const [error,setError] = useState({});
    const [disable,setDisable] = useState(false);
    const handleChange = (e) =>{
        const {name,value} = e.target;
        if(name == "fullName"){
            if(!value){
                setError({...error,fullName:"Full Name is Required"});
            }
            else{
                setError({...error,fullName:""});
                // setRegisterRequestBody({...registerRequestBody,fullName:value});
            }
        }
        if(name == "email"){
            if(!value){
                setError({...error,email:"Email is Required"});
            }
            else{
                setError({...error,email:""});
                // setRegisterRequestBody({...registerRequestBody,email:value});
            }
        }
        if(name == "phone"){
            if(!value){
                setError({...error,phone:"Phone Number is Required"});
            }
            else{
                setError({...error,phone:""});
                // setRegisterRequestBody({...registerRequestBody,phone:value});
            }
        }
        if(name == "username"){
            if(!value){
                setError({...error,username:"Username is Required"});
            }
            else{
                setError({...error,username:""});
                // setRegisterRequestBody({...registerRequestBody,username:value});
            }
        }
        if(name == "password"){
            if(!value){
                setError({...error,password:"password is Required"});
            }
            else{
                setError({...error,password:""});
                // setRegisterRequestBody({...registerRequestBody,password:value});
            }
        }
        if(name == "confirm"){
            if(!value){
                setError({...error,confirm:"confirm is Required"});
            }
            else{
                setError({...error,confirm:""});
                // setRegisterRequestBody({...registerRequestBody,confirm:value});
            }
        }
        setRegisterRequestBody({...registerRequestBody,[name]:value});
    }
    const handleChangeRadio = (e) =>{
        const {name,value} = e.target;
        if(name == "gender"){
            if(!value){
                setError({...error,gender:"gender is Required"});
            }
            else{
                setError({...error,gender:""});
                // setRegisterRequestBody({...registerRequestBody,gender:value});
            }
        }
        setRegisterRequestBody({...registerRequestBody,[name]:value});
    }
    console.log(registerRequestBody);
    console.log(error);
    const submit = ()=>{
        alert("submitted successfully");
    }
    useEffect(()=>{
        if(error?.fullName?.length == 0 && error?.email?.length == 0 && error?.phone?.length == 0 && error?.confirm?.length == 0 && error?.password?.length == 0 && error?.gender?.length == 0 && error?.username?.length == 0){
            setDisable(false);
        }
        else if(error?.fullName?.length != 0 || error?.email?.length != 0 || error?.phone?.length != 0 || error?.confirm?.length != 0 || error?.password?.length != 0 || error?.gender?.length != 0 || error?.username?.length != 0){
            setDisable(true);
        }
    },[error]);
    return(
        <div>
            <div className='body'>
                <form className='form-div'>
                    <div className='title'>
                        <h2>Registration</h2>
                    </div>
                    <div className='inputs'>
                        <div className='first-div'>
                            <label className='first-div-label'><b>Full Name</b></label><br/>
                            <input type='text' name="fullName" value={registerRequestBody?.fullName} onChange={handleChange} className='first-div-input' placeholder='Enter Full Name'/><br/>
                            <label className='first-div-label'><b>Email</b></label><br/>
                            <input type='email' name="email" value={registerRequestBody?.email} onChange={handleChange} className='first-div-input' placeholder='Enter Email Id'/><br/>
                            <label className='first-div-label'><b>Password</b></label><br/>
                            <input type='password' name="password" value={registerRequestBody?.password} onChange={handleChange} className='first-div-input' placeholder='Enter Password'/>
                        </div>
                        <div className='second-div'>
                            <label className='second-div-label'><b>Username</b></label><br/>
                            <input type='text' name="username" value={registerRequestBody?.username} onChange={handleChange} className='second-div-input' placeholder='Enter Username'/><br/>
                            <label className='second-div-label'><b>Phone Number</b></label><br/>
                            <input type='Number' name="phone"value={registerRequestBody?.phone}  onChange={handleChange} className='second-div-input' placeholder='Enter Phone Number'/><br/>
                            <label className='second-div-label'><b>Confirm Password</b></label><br/>
                            <input type='password' name="confirm" value={registerRequestBody?.confirm} onChange={handleChange} className='second-div-input' placeholder='Enter Confirm Password'/><br/>
                        </div>
                        <div className='third-div'>
                            <label className='third-div-label'><b>Gender</b></label><br/>
                            <input type='radio' value="male" onChange={handleChangeRadio} className="third-div-input1" name='gender' /><b style={{fontSize:"14px"}}>Male</b>
                            <input type='radio' value="female" onChange={handleChangeRadio} className="third-div-input" name='gender' /><b style={{fontSize:"14px"}}>Female</b>
                            <input type='radio' value="prefer not to say" onChange={handleChangeRadio} className="third-div-input" name='gender'/><b style={{fontSize:"14px"}}>Prefer not to say</b><br/>
                        </div>
                        <div className='fourth-div'>
                            <button className='btn' onClick={submit} disabled={disable}>Register</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Registration;
