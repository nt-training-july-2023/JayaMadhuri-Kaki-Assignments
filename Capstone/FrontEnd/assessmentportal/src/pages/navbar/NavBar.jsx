import React, { useEffect, useState } from 'react'
import '../../styles/NavBar.scss';
import { FaBars } from 'react-icons/fa'
import Category from '../category/Category'
import Profile from '../profile/Profile'
import Results from '../results/Results'
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import logo from '../../assests/images/loginAndRegister/logo.svg';
import FormButton from '../../components/button/FormButton';
import Text from '../../components/sweetAlert/Text';
import LogOut from '../../components/sweetAlert/LogOut';

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
        LogOut.render(setRenderComponent)
    }
    useEffect(() => {
        if( Object.keys(userDetails).length > 0){
            if (role === "Admin") {
                Text.render(sweetAlertMessages.WELCOME)
            } else {
                Text.render(`WELCOME ${userInfo?.Name} TO STUDENT DASHBOARD!`)
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