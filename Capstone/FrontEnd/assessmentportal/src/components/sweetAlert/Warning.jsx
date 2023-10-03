import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"

class Warning{
    render(text){
        Swal.fire({
            title: sweetAlertMessages.ERROR,
            text: text,
            timer: 1500,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.WARNING,
            background: "#15172b",
            color: "white",
        });
    }
}
export default new Warning;