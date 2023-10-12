import React, { useEffect, useState } from 'react'
import '../../styles/NavBar.scss';
import { FaBars, FaSignOutAlt, FaHome, FaUserCircle, FaDeezer } from 'react-icons/fa'
import Category from '../category/Category'
import Profile from '../profile/Profile'
import Results from '../results/Results'
import { sweetAlertMessages } from "../../constants/ValidationMessages"
import logo from '../../assets/images/loginAndRegister/logo.svg';
import Button from '../../components/button/Button';
import Alert from '../../components/sweetAlert/Alert';
import Label from '../../components/label/Label';

const Navbar = (props) => {
    const { setRenderComponent, userDetails } = props
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
            return <Profile userDetails={userInfo} setRenderComponent={setRenderComponent}/>
        } else if (activeButton === "results") {
            return <Results userDetails={userInfo} setRenderComponent={setRenderComponent}/>
        } else {
            return <Category userDetails={userInfo} setEnable={setEnable} setRenderComponent={setRenderComponent}/>
        }
    }
    const handleLogOut = () => {
        setIsNavExpanded(false)
        Alert.LogOut(setRenderComponent)
    }
    useEffect(() => {
        if (Object.keys(userDetails).length > 0) {
            if (role === "Admin") {
                Alert.Text(sweetAlertMessages.WELCOME)
            } else {
                Alert.Text(`WELCOME ${userInfo?.Name} TO STUDENT DASHBOARD!`)
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
                    <Button onClick={toggleMenu} className='close-icon' children={"X"} />
                    <ul>
                        <li>
                            <Button onClick={() => {
                                handleButtonClick('category')
                                localStorage.setItem("Current_SubWindow", "category")
                                setIsNavExpanded(false)
                            }}
                                className={`nav-button ${activeButton === "category"
                                    ? 'active'
                                    : ''
                                    }`}
                                children={<Label className='nav-button-icon'>Home<FaHome className="nav-icons" /></Label>} />
                        </li>
                        <li>
                            <Button onClick={() => {
                                handleButtonClick('results');
                                setIsNavExpanded(false)
                                localStorage.setItem("Current_SubWindow", "results")
                            }}
                                className={`nav-button ${activeButton === "results" ? 'active' : ''
                                    }`} disabled={enable} children={<Label className='nav-button-icon'>Results<FaDeezer className="nav-icons" /></Label>} />
                        </li>
                        <li>
                            <Button onClick={() => {
                                handleButtonClick('profile');
                                setIsNavExpanded(false)
                                localStorage.setItem("Current_SubWindow", "profile")
                            }}
                                className={`nav-button ${activeButton === "profile" ? 'active' : ''
                                    }`} disabled={enable} children={<Label className='nav-button-icon'>Profile<FaUserCircle className="nav-icons" /></Label>} />
                        </li>
                        <li>
                            <Button onClick={handleLogOut} className="nav-button" disabled={enable} children={<Label className='nav-button-icon'>Logout<FaSignOutAlt className="nav-icons" /></Label>} />
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