import React,{useEffect, useState} from 'react';
import './NavBar.scss'; 
import {FaBars} from'react-icons/fa';
import Swal from 'sweetalert2'
import Category from '../Category/Category';
import Profile from '../Profile/Profile';
import Results from '../Results/Results';

const Navbar = (props) => {
  const {setRenderComponent,userDetails} = props;
  const role = userDetails.UserType;
  const [activeButton, setActiveButton] = useState("");
  const [isNavExpanded, setIsNavExpanded] = useState(false)
  const toggleMenu = () =>{
    setIsNavExpanded(!isNavExpanded);
  };
  const handleButtonClick = (page) => {
    setActiveButton(page);
  };
  const render = () =>{
    if(activeButton === "category"){
        return <Category userDetails={userDetails}/>;
    }else if(activeButton === "profile"){
        return <Profile userDetails={userDetails}/>;
    }else if(activeButton === "results"){
        return <Results/>
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
        cancelButtonText: '<span style="color:#15172b"> Stay </span>',
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
            setRenderComponent("navbar")
        }
      })
  }
  useEffect(()=>{
    if(role === "Admin"){
        setActiveButton("category")
        Swal.fire({
            text:'WELCOME TO ADMIN DASHBOARD!',
            timer:1900,
            showConfirmButton:false,
            color:'white',
            background:'#15172b'
        })
    }else{
        setActiveButton("category")
        Swal.fire({
            text:`WELCOME ${userDetails?.Name} TO STUDENT DASHBOARD!`,
            timer:1900,
            showConfirmButton:false,
            color:'white',
            background:'#15172b'
        })
    }
  },[]);
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
                    <button onClick={()=>{
                        handleButtonClick('category')
                        setIsNavExpanded(false);
                    }}
                className={`nav-button ${
                    activeButton === "category"
                    ? 'active'
                    : ''
                }`}>
                Home
                </button>
                    </li>
                    <li>
                    <button onClick={() => {handleButtonClick('results');setIsNavExpanded(false);}}
                className={`nav-button ${
                activeButton === "results" ? 'active' : ''
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
export default Navbar;