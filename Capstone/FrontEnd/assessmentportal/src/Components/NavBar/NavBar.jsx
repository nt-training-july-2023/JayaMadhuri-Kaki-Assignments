import React,{useState} from 'react';
import './NavBar.scss'; // Import your CSS file
import {FaBars} from'react-icons/fa';
import Swal from 'sweetalert2'
import AdminCategory from '../Category/AdminCategory';
import Profile from '../Profile/Profile';
import AdminResults from '../Results/AdminResults';

const Navbar = ({setRenderComponent}) => {
  const [activeButton, setActiveButton] = useState("adminCategory");
  const [isNavExpanded, setIsNavExpanded] = useState(false)
  const toggleMenu = () =>{
    setIsNavExpanded(!isNavExpanded);
  };
  const handleButtonClick = (page) => {
    setActiveButton(page);
  };
  const render = () =>{
    if(activeButton === "adminCategory"){
        return <AdminCategory/>;
    }else if(activeButton === "profile"){
        return <Profile/>;
    }else if(activeButton === "results"){
        return <AdminResults/>
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
            setRenderComponent("navbar")
        }
      })
  }
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
                    <button onClick={() => {handleButtonClick('adminCategory');setIsNavExpanded(false);}}
                className={`nav-button ${
                activeButton === "adminCategory" ? 'active' : ''
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