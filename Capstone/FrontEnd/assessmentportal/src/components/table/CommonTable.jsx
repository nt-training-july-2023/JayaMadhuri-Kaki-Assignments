
const CommonTable = ({ columns, data, rows }) => {
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
          <tr key={item.finalResultId}>
            {rows.map((column) => (
              <td key={column}>{item[column]}</td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default CommonTable;
