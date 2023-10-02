import React, { useEffect, useState } from 'react'
import '../../styles/NavBar.scss';
import { FaBars } from 'react-icons/fa'
import Swal from 'sweetalert2'
import Category from '../category/Category'
import Profile from '../profile/Profile'
import Results from '../results/Results'
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import logo from '../../assests/images/loginAndRegister/logo.svg';
import FormButton from '../../components/button/FormButton';

const Navbar = (props) => {
    const { setRenderComponent, userDetails} = props
    let userDetails_AfterReload = JSON.parse(localStorage.getItem("UserDetails"));
    const userInfo = Object.keys(userDetails).length > 0 ? userDetails : userDetails_AfterReload
    const role = userInfo?.UserType;
    const Current_SubWindow = localStorage.getItem("Current_SubWindow")
    const [activeButton, setActiveButton] = useState(Current_SubWindow)
    const [isNavExpanded, setIsNavExpanded] = useState(false)
    const [enable, setEnable] = useState(false)
    const toggleMenu = () => {
        setIsNavExpanded(!isNavExpanded)
    }
    const handleButtonClick = (page) => {
        setActiveButton(page)
    }
    const render = () => {
        if (activeButton === "profile") {
            return <Profile userDetails={userInfo} />
        } else if (activeButton === "results") {
            return <Results userDetails={userInfo} />
        }
        else {
            return <Category userDetails={userInfo} setEnable={setEnable}/>
        }
    }
    const handleLogOut = () => {
        setIsNavExpanded(false)
        Swal.fire({
            text: sweetAlertMessages.LOGOUT,
            type: sweetAlertMessages.WARNING,
            showCancelButton: true,
            confirmButtonColor: 'white',
            cancelButtonColor: 'white',
            cancelButtonText: '<span style="color:#15172b"> Stay </span>',
            confirmButtonText: '<span style="color: #15172b">Logout</span>',
            background: "#15172b",
            color: "white",
            customClass: {
                confirmButton: 'custom-button-text',
                cancelButton: 'custom-button-text',
            },
        }).then(function (result) {
            if (result.value === true) {
                Swal.fire({
                    text: sweetAlertMessages.LOGOUT_REDIRECT,
                    timer: 1900,
                    showConfirmButton: false,
                    color: 'white',
                    background: '#15172b'
                })
                setTimeout(function () {
                    localStorage.setItem("UserDetails","");
                    localStorage.setItem("Current_Window","");
                    localStorage.setItem("Current_SubWindow","")
                    localStorage.setItem("LastVisited_Window","");
                    localStorage.setItem("Current_Category_SubWindow","")
                    localStorage.setItem("CategoryId","")
                    localStorage.setItem("CategoryName","")
                    localStorage.setItem("QuizName","")
                    localStorage.setItem("QuizId","")
                    localStorage.setItem("Current_Quiz_SubWindow","")
                    localStorage.setItem("details","")
                    setRenderComponent("login")
                }, 2000)
            } else {
                setRenderComponent("navbar")
            }
        })
    }
    useEffect(() => {
        if( Object.keys(userDetails).length > 0){
            if (role === "Admin") {
                Swal.fire({
                    text: sweetAlertMessages.WELCOME,
                    timer: 1900,
                    showConfirmButton: false,
                    color: 'white',
                    background: '#15172b'
                })
            } else {
                Swal.fire({
                    text: `WELCOME ${userInfo?.Name} TO STUDENT DASHBOARD!`,
                    timer: 1900,
                    showConfirmButton: false,
                    color: 'white',
                    background: '#15172b'
                })
            }
        }
    }, [])
    return (
        <div>
            <nav className='navigation'>
                <img src={logo} alt="Logo" className="logo-navbar" />
                <div className="menu-icon" onClick={toggleMenu}>
                    <FaBars />
                </div>
                <div className={
                    isNavExpanded ? "navigation-menu expanded" : "navigation-menu"
                }>
                    <FormButton onClick={toggleMenu} className='close-icon'>
                       X
                    </FormButton>
                    <ul>
                        <li>
                            <FormButton onClick={() => {
                                handleButtonClick('category')
                                localStorage.setItem("Current_SubWindow","category")
                                setIsNavExpanded(false)
                            }}
                                className={`nav-button ${activeButton === "category"
                                        ? 'active'
                                        : ''
                                    }`}>
                                Home
                            </FormButton>
                        </li>
                        <li>
                            <FormButton onClick={() => { 
                                handleButtonClick('results'); 
                                setIsNavExpanded(false)
                                localStorage.setItem("Current_SubWindow","results")
                             }}
                                className={`nav-button ${activeButton === "results" ? 'active' : ''
                                    }`} disabled={enable}>
                                Results
                            </FormButton>
                        </li>
                        <li>
                            <FormButton onClick={() => { 
                                handleButtonClick('profile'); 
                                setIsNavExpanded(false)
                                localStorage.setItem("Current_SubWindow","profile")
                             }}
                                className={`nav-button ${activeButton === "profile" ? 'active' : ''
                                    }`} disabled={enable}>
                                Profile
                            </FormButton>
                        </li>
                        <li>
                            <FormButton onClick={handleLogOut} className="nav-button" disabled={enable}>
                                Logout
                            </FormButton>
                        </li>
                    </ul>
                </div>
            </nav>
            <div>
                {render()}
            </div>
        </div>
    )
}
export default Navbar 