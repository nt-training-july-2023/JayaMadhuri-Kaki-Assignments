import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"

class Success{
    render(title){
        Swal.fire({
            title: title,
            timer: 1500,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.SUCCESS,
            background: "#15172b",
            color: "white",
        });
    }
    render(title,text){
        Swal.fire({
            title: title,
            text: text,
            timer: 1500,
            showConfirmButton: false,
            showCancelButton: false,
            icon: sweetAlertMessages.SUCCESS,
            background: "#15172b",
            color: "white",
        });
    }
}
export default new Success;