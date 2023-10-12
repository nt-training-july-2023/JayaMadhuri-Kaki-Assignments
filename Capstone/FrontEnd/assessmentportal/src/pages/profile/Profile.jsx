import React, { useState, useEffect } from 'react';
import '../../styles/Profile.scss';
import UsersUrl from '../../service/Url';
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import maleProfileImage from '../../assets/images/profile/male-profile.jpg';
import femaleProfileImage from '../../assets/images/profile/female-profile.jpg';
import Alert from '../../components/sweetAlert/Alert';
import HeadingOne from '../../components/heading/HeadingOne';
const Profile = ({ userDetails,setRenderComponent }) => {
    const [details, setDetails] = useState({});
    const handleUserDetails = async () => {
        UsersUrl.getUserByEmail(userDetails.EmailId)
            .then(response => {
                if (response?.data?.statusCode === 200) {
                    const user = response?.data?.responseData;
                    setDetails(user);
                }
            }).catch(error => {
                if (error?.message === sweetAlertMessages.NETWORK_ERROR) {
                    Alert.NetworkError(setRenderComponent)
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
                <HeadingOne className='category-title' children="Profile"/>
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
