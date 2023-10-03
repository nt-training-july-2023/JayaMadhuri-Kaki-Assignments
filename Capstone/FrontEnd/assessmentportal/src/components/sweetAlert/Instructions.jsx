import Swal from "sweetalert2";

class Instructions{
    render(event,setShowQuestion,setSelectedQuizId,setTime,convertMinutesToTime,item,details){
        Swal.fire({
            title: 'Instructions:',
            html: '<div style="text-align:left">*Once, test started user should not leave the quiz without submit. If not submitted results will not be stored<br>*Each question carries one mark.<br>*Do not Refresh the page<div>',
            showConfirmButton: true,
            icon: "info",
            showCancelButton: true,
            background: "#15172b",
            color: "white",
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