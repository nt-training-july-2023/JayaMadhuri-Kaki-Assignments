import React, { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import '../../styles/Profile.scss';
import UsersUrl from '../../services/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import maleProfileImage from '../../assests/images/profile/male-profile.jpg';
import femaleProfileImage from '../../assests/images/profile/female-profile.jpg';

const Profile = ({ userDetails }) => {
    const [details, setDetails] = useState({});
    const handleUserDetails = async () => {
        UsersUrl.getUserByEmail(userDetails.EmailId)
            .then(response => {
                if (response?.data?.statusCode === 200) {
                    const user = response?.data?.responseData;
                    setDetails(user);
                }
            }).catch(error => {
                if (error?.response?.message === "Network Error") {
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
                }
            })
    }
    useEffect(() => {
        localStorage.setItem('reloadCount', '');
        handleUserDetails()
    }, [])
    return (
        <div>
            <div>
                <h1 className='category-title'>Profile</h1>
                <hr />
            </div>
            <div className='profile-container'>
                <div className='card'>
                    <img src={details.gender === "male" ? maleProfileImage
                    : femaleProfileImage} alt="Avatar" className='image' />
                    <div className="profile-card">
                        <table>
                            <tr><td>First Name:</td><td className='second-td'>{details.firstName}</td></tr>
                            <tr><td>Last Name:</td><td className='second-td'>{details.lastName}</td></tr>
                            <tr><td>Date Of Birth:</td><td className='second-td'>{details.dateOfBirth}</td></tr>
                            <tr><td>Gender:</td><td className='second-td'>{details.gender}</td></tr>
                            <tr><td>Email Id:</td><td className='second-td'>{details.emailId}</td></tr>
                            <tr><td>User Type:</td><td className='second-td'>{details.userType}</td></tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default Profile;
