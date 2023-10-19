import 
Button from "../button/Button";
import Alert from "../sweetAlert/Alert";
import {FaTrashAlt, FaPencilAlt} from 'react-icons/fa'

const CommonTable = (props) => {
  const { columns, data, rows, setPopUp, setInitialValues, setTitleQuestion, fetchData, setIsDisable, setRenderComponent} = props;
  return (
    <table className="table table-responsive">
      <thead>
        <tr>
          {columns.map((column) => (
            <th key={column}>{column}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((item) => (
          <tr key={item.questionId}>
            {rows.map((column) => (
              <td key={column}>{item[column]}</td>
            ))}
            {setPopUp && <td>
              <Button onClick={(event) => {
                  setPopUp(true)
                  let updateInitialValues = {
                      questionId: item?.questionId,
                      questionContent: item?.questionContent,
                      optionA: item?.optionA,
                      optionB: item?.optionB,
                      optionC: item?.optionC,
                      optionD: item?.optionD,
                      correctAnswer: item?.correctAnswer,
                      subCategoryId: item?.subCategoryId
                  }
                  setInitialValues(updateInitialValues)
                  setTitleQuestion("Update Question")
                  setIsDisable(true)
                  event.stopPropagation()
              }} className='question-button'><FaPencilAlt className='icons'/>Update</Button>
              <Button 
                  onClick={(event) => {
                      event.stopPropagation()
                      Alert.Delete(fetchData,item.questionId,true,false,false,setRenderComponent);
                  }} className='question-button delete-button'><FaTrashAlt className='icons'/>Delete</Button>
            </td>}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default CommonTable;
