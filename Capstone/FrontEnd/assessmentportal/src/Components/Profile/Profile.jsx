import React, { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import '../Styles/Profile.scss'
import UsersUrl from '../../Services/Url';

const Profile = ({ userDetails }) => {
    const [details, setDetails] = useState({});
    const handleUserDetails = async () => {
        UsersUrl.getUserByEmail(userDetails.EmailId)
            .then(response => {
                if (response?.data?.statusCode === 200) {
                    const user = response?.data?.StudentDetails;
                    setDetails(user);
                }
            }).catch(error => {
                if (error?.response?.message === "Network Error") {
                    Swal.fire({
                        title: 'Erro',
                        text: 'NetWork Error',
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
    useEffect(() => {
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
                    <img src={details.gender === "male" ? ("https://static.vecteezy.com/system/resources/thumbnails/002/002/403/small/man-with-beard-avatar-character-isolated-icon-free-vector.jpg")
                    : ("https://static.vecteezy.com/system/resources/thumbnails/001/993/889/small/beautiful-latin-woman-avatar-character-icon-free-vector.jpg")} alt="Avatar" className='image' />
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
