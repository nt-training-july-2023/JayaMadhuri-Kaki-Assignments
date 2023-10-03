import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"

class Info {
    render(setShowQuestion,setEnable,checkAnswers,setIsRunning){
        Swal.fire({
            text: sweetAlertMessages.RELOAD,
            showConfirmButton: true,
            showCancelButton: true,
            icon: sweetAlertMessages.INFO,
            background: '#15172b',
            color: 'white',
        }).then(function (result) {
            if (result.value === true) {
                setShowQuestion(false)
                setEnable(false)
                localStorage.setItem("Current_Quiz_SubWindow","")
                localStorage.setItem('reloadCount', '');
                localStorage.setItem("attemptedQuestions",0);
                localStorage.setItem("time","")
                localStorage.setItem("prevSelectedOption","");
                checkAnswers();
            }else{
                setIsRunning(true)
            }
        })
    }
}
export default new Info;