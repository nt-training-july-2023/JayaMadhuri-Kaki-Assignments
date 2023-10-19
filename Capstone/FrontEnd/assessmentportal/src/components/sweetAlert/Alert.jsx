import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import QuestionUrl from '../../service/Url';
import CategoryUrl from '../../service/Url';
import QuizUrl from '../../service/Url';

class Alert{
    Success(title){
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
    Success(title,text){
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
    Info(setShowQuestion,setEnable,checkAnswers,setIsRunning){
        Swal.fire({
            text: sweetAlertMessages.RELOAD,
            showConfirmButton: true,
            showCancelButton: true,
            icon: sweetAlertMessages.INFO,
            background: '#15172b',
            color: 'white',
            customClass: {
                confirmButton: 'custom-button-text',
                cancelButton: 'custom-button-text',
            },
        }).then((result) => {
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
    Url(question,quiz,category,Id){
        if(question === true){
            return QuestionUrl.deleteQuestion(Id);
        }
        else if(quiz === true){
            return QuizUrl.deleteQuiz(Id)
        }
        else if(category == true){
            return CategoryUrl.deleteCategory(Id)
        }
    }
    Delete(fetchData,Id,question,quiz,category,setRenderComponent){
        Swal.fire({
            text: sweetAlertMessages.DELETE_MSG,
            icon: sweetAlertMessages.WARNING,
            background: "#15172b",
            color: "white",
            showCancelButton: true,
            customClass: {
                confirmButton: 'custom-button-text',
                cancelButton: 'custom-button-text',
            },
        }).then(async(result) =>{
            if (result.value === true) {
                    await this.Url(question,quiz,category,Id).then(response => {
                        if (response?.data?.statusCode == 200) {
                            this.Success(sweetAlertMessages.DELETE,sweetAlertMessages.DELETE_SUCCESS)
                            fetchData()
                        }
                    }).catch(error => {
                        if (error?.response?.status == "404") {
                            this.Warning(sweetAlertMessages.DELETE,sweetAlertMessages.ID_NOT_FOUND)
                        }else  if (error?.message == "Network Error") {
                            this.NetworkError(setRenderComponent)
                        } 
                    })
                }
        })
    }
    Warning(text){
        Swal.fire({
            title: sweetAlertMessages.ERROR,
            text: text,
            timer: 1500,
            icon: "warning",
            showConfirmButton: false,
            showCancelButton: false,
            background: "#15172b",
            color: "white",
        });
    }
    NetworkError(setRenderComponent){
        Swal.fire({
            text: sweetAlertMessages.SERVER_DOWN,
            showCancelButton: true,
            confirmButtonColor: 'white',
            cancelButtonColor: 'white',
            icon: "warning",
            cancelButtonText: sweetAlertMessages.CANCEL_BUTTON_TEXT_RELOAD,
            confirmButtonText: sweetAlertMessages.CONFIRM_BUTTON_TEXT,
            background: "#15172b",
            color: "white",
            customClass: {
                confirmButton: 'custom-button-text',
                cancelButton: 'custom-button-text',
            },
        }).then(async(result) =>{
            if (result.value === true) {
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
                }, 1000)   
            }else{
                window.location.reload(false);
            }
        })
    }
    Error(title,text){
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
    Text(text){
        Swal.fire({
            text: text,
            timer: 1900,
            showConfirmButton: false,
            color: 'white',
            background: '#15172b'
        })
    }
    LogOut(setRenderComponent){
        Swal.fire({
            text: sweetAlertMessages.LOGOUT,
            type: sweetAlertMessages.WARNING,
            showCancelButton: true,
            confirmButtonColor: 'white',
            cancelButtonColor: 'white',
            cancelButtonText: sweetAlertMessages.CANCEL_BUTTON_TEXT,
            confirmButtonText: sweetAlertMessages.CONFIRM_BUTTON_TEXT,
            background: "#15172b",
            color: "white",
            customClass: {
                confirmButton: 'custom-button-text',
                cancelButton: 'custom-button-text',
            },
        }).then((result) => {
            if (result.value === true) {
                this.Text(sweetAlertMessages.LOGOUT_REDIRECT)
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
    Instructions(setShowQuestion,setSelectedQuizId,setTime,convertMinutesToTime,item,details){
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
export default new Alert;
