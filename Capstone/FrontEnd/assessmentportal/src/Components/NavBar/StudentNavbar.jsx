import React,{useEffect, useState} from 'react';
import './NavBar.scss'; 
import {FaBars} from'react-icons/fa';
import Swal from 'sweetalert2'
import StudentCategory from '../Student/StudentCategory';
import Profile from '../Profile/Profile';
import StudentResults from '../Results/StudentResults';
import axios from 'axios';

const StudentNavbar = (props) => {
  const {setRenderComponent,userDetails} = props;
  const [activeButton, setActiveButton] = useState("studentCategory");
  const [isNavExpanded, setIsNavExpanded] = useState(false)
  const toggleMenu = () =>{
    setIsNavExpanded(!isNavExpanded);
  };
  const handleButtonClick = (page) => {
    setActiveButton(page);
  };
  const render = () =>{
    if(activeButton === "studentCategory"){
        return <StudentCategory/>;
    }else if(activeButton === "profile"){
        return <Profile userDetails={userDetails}/>;
    }else if(activeButton === "studentResults"){
        return <StudentResults/>
    }
    else{
        return null;
    }
  }
  const handleLogOut = () =>{
    setIsNavExpanded(false);
    Swal.fire({
        text: "Are You Really Want To LogOut?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: 'white',
        cancelButtonColor: 'white',
        cancelButtonText: '<span style="color:#15172b"> StayIn </span>',
        confirmButtonText:'<span style="color: #15172b">LogOut</span>',
        background:"#15172b",
        color:"white",
        customClass: {
            confirmButton: 'custom-button-text', 
            cancelButton: 'custom-button-text', 
          },
      }).then(function(result) {
        if(result.value === true){
            Swal.fire({
                text:'Logging Out! Redirecting to Login Page...',
                timer:1900,
                showConfirmButton:false,
                color:'white',
                background:'#15172b'
            })
            setTimeout(function() {
                setRenderComponent("login") 
            }, 2000);
        }else{
            setRenderComponent("studentNavbar")
        }
      })
  }
  useEffect(()=>{
    Swal.fire({
        text:`WELCOME ${userDetails?.Name} to Student Dashboard!`,
        timer:1900,
        showConfirmButton:false,
        color:'white',
        background:'#15172b'
    });
  },[])
  return (
    <div>
        <nav className='navigation'>
                <img src="https://nucleusteq.com/wp-content/uploads/2020/03/logo-header-1.svg" alt="Logo" className="logo-navbar" />
                <div className="menu-icon" onClick = {toggleMenu}>
                    <FaBars/>
                </div>
                <div className={
            isNavExpanded ? "navigation-menu expanded" : "navigation-menu"
            }>
                <ul>
                    <li>
                    <button onClick={() => {handleButtonClick('studentCategory');setIsNavExpanded(false);}}
                className={`nav-button ${
                activeButton === "studentCategory" ? 'active' : ''
                }`}>
                Home
                </button>
                    </li>
                    <li>
                    <button onClick={() => {handleButtonClick('studentResults');setIsNavExpanded(false);}}
                className={`nav-button ${
                activeButton === "studentResults" ? 'active' : ''
                }`}>
                Results
                </button>
                    </li>
                    <li>
                    <button onClick={() => {handleButtonClick('profile');setIsNavExpanded(false);}}
                className={`nav-button ${
                activeButton === "profile" ? 'active' : ''
                }`}>
                Profile
                </button>
                    </li>
                    <li>
                    <button onClick={handleLogOut} className="nav-button">
                LogOut
                </button>
                    </li>
                </ul>
            </div>
        </nav>
        <div>
            {render()}
        </div>
    </div>
  );
};
export default StudentNavbar;