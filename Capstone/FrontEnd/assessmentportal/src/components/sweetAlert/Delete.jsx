import Swal from "sweetalert2";
import {sweetAlertMessages}  from "../../constants/ValidationMessages"
import Success from "./Success";
import Warning from "./Warning";
import QuestionUrl from '../../service/Url';
import CategoryUrl from '../../service/Url';
import QuizUrl from '../../service/Url';

class Delete {
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
    render(fetchData,Id,question,quiz,category){
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
                            Success.render(sweetAlertMessages.DELETE,sweetAlertMessages.DELETE_SUCCESS)
                            fetchData()
                        }
                    }).catch(error => {
                        if (error?.response?.status == "404") {
                            Warning.render(sweetAlertMessages.DELETE,sweetAlertMessages.ID_NOT_FOUND)
                        }
                    })
                }
        })
    }
}
export default new Delete;