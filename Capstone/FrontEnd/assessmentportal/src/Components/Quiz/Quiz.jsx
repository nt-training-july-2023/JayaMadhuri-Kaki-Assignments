import React,{useState,useEffect} from 'react';
import axios from 'axios'
import './Quiz.scss'
import Swal from 'sweetalert2'
import AddQuiz from './AddQuiz';

const Quiz = (props) =>{
    const {userDetails,setShowQuiz} = props;
    const [quiz,setQuiz] = useState([]);
    const [title,setTitle] = useState("Add Quiz");
    const message = "No Quiz Found!";
    const [popUp,setPopUp] = useState(false);
    const [initialValues,setInitialValues] = useState({
        subCategoryName:"",
        subCategoryDescription:"",
        timeLimitInMinutes:"",
        categoryId:""
    })
    const handleAdd = () =>{
        setTitle("Add Quiz");
        setInitialValues({
            subCategoryName:"",
            subCategoryDescription:"",
            timeLimitInMinutes:"",
            categoryId:""
        })
        setPopUp(true);
    }
    const fetchData = async () => {
        try {
          const response = await axios.get(`http://localhost:6060/subCategoryByCategory/504`);
            setQuiz(response?.data?.SubCategoryByCategoryId);
        } catch (error) {
            message();
        }
    };
    useEffect(() => {
        fetchData();
    }, []);
    return(
        <div>
            <div>
                {userDetails?.UserType === "Admin" && <button className='addquiz-btn' onClick={handleAdd}>Add Quiz</button>}
                {userDetails?.UserType === "Admin" && <button className='backquiz-btn' onClick={()=>{setShowQuiz(false)}}>Back</button>}
                <h1 className='category-title'>Quiz</h1>
                <hr/>
            </div>
            <div className="quiz-container">
            {quiz.map((item) => (
            <div key={item.subCategoryId} className="quiz-card">
                {/* <p>subCategoryId ID: {item.subCategoryId}</p> */}
                <p className='p'>Name: {item.subCategoryName}</p>
                <p className='p'>Description: {item.subCategoryDescription}</p>
                <p className='p'>Time(In Minutes): {item.timeLimitInMinutes}</p>
                {userDetails?.UserType === "Admin" && <div>
                    <button onClick={()=>{
                        setPopUp(true);
                        let updateInitialValues = {subCategoryId:item?.subCategoryId, subCategoryName:item?.subCategoryName, subCategoryDescription:item?.subCategoryDescription, timeLimitInMinutes:item?.timeLimitInMinutes, categoryId:item?.categoryId};
                        setInitialValues(updateInitialValues);
                        setTitle("Update Quiz");
                    }}  className='quiz-btn'>Update</button>
                    <button onClick={()=>{
                            axios.delete(`http://localhost:6060/deleteSubCategory/${item.subCategoryId}`)
                            .then(response=>{
                                if(response?.data?.statusCode == 200){
                                    Swal.fire({
                                        title: 'Delete',
                                        text: 'Successfully Deleted',
                                        timer: 1000,
                                        showConfirmButton:false,
                                        showCancelButton:false,
                                        icon: "success",
                                        background:"#15172b",
                                        color:"white",
                                    }); 
                                    fetchData()
                                }
                            }).catch(error=>{
                                if(error?.response?.status == "404"){
                                    Swal.fire({
                                        title: 'Delete',
                                        text: 'ID Not Found',
                                        timer: 1000,
                                        showConfirmButton:false,
                                        showCancelButton:false,
                                        icon: "warning",
                                        background:"#15172b",
                                        color:"white",
                                    }); 
                                }
                            })
                        }} className='quiz-btn'>Delete</button>
                </div>}
                </div>
            ))}
            </div>
            {popUp && (
                <AddQuiz title={title} initialValues={initialValues} setPopUp={setPopUp} fetchData={fetchData}/>
            )}
        </div>
    )
}

export default Quiz;