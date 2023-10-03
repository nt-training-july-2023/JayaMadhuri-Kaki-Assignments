import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"

class Error {
    render(title,text){
        Swal.fire({
            title: title,
            text: text,
            timer: 1500,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.ERROR,
            background: "#15172b",
            color: "white",
        });
    }
}
export default new Error;