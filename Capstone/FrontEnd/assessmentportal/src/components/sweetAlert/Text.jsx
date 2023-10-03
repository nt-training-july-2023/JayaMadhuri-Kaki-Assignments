import Swal from "sweetalert2";

class Text {
    render(text){
        Swal.fire({
            text: text,
            timer: 1900,
            showConfirmButton: false,
            color: 'white',
            background: '#15172b'
        })
    }
}
export default new Text;