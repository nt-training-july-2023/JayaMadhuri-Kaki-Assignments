import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import Text from "./Text";
class LogOut {
    render(setRenderComponent){
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
                Text.render(sweetAlertMessages.LOGOUT_REDIRECT)
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
}
export default new LogOut;