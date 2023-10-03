import CardButton from "../button/CardButton";
import Delete from "../sweetAlert/Delete";

const CommonTable = (props) => {
  const { columns, data, rows, setPopUp, setInitialValues, setTitleQuestion, fetchData } = props;
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
              <CardButton onMouseDown={event => event.stopPropagation()} onClick={(event) => {
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
                  event.stopPropagation()
              }} className='question-btn'>Update</CardButton>
              <CardButton onMouseDown={event => event.stopPropagation()}
                  onClick={(event) => {
                      event.stopPropagation()
                      Delete.render(fetchData,item.questionId,true,false,false);
                  }} className='question-btn2'>Delete</CardButton>
            </td>}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default CommonTable;
