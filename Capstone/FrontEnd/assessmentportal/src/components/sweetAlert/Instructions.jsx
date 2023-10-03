import Swal from "sweetalert2";
import { sweetAlertMessages } from "../../constants/ValidationMessages";

class Instructions{
    render(event,setShowQuestion,setSelectedQuizId,setTime,convertMinutesToTime,item,details){
        Swal.fire({
            title: sweetAlertMessages.INSTRUCTIONS,
            html: sweetAlertMessages.INSTRUCTIONS_MSG,
            showConfirmButton: true,
            icon: sweetAlertMessages.INFO,
            showCancelButton: true,
            background: "#15172b",
            color: "white",
            customClass: {
                confirmButton: 'custom-button-text',
                cancelButton: 'custom-button-text',
            },
        }).then((result) => {
            if (result.isConfirmed) {
                event.stopPropagation();
                setShowQuestion(true);
                setSelectedQuizId(item.subCategoryId);
                const formattedTime = convertMinutesToTime(item.timeLimitInMinutes)
                setTime(formattedTime)
                localStorage.setItem("details",JSON.stringify(details))
            }else{
                localStorage.setItem("Current_Quiz_SubWindow","")
            }
        })
    }
}
export default new Instructions;