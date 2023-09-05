import './App.scss'
import {useState} from 'react'
import Login from './Components/LoginAndRegister/Login';
import Register from './Components/LoginAndRegister/Register';
import AdminDashboard from './Components/Admin/AdminDashboard';
import AdminCategory from './Components/Admin/AdminCateogy';
import StudentDashboard from './Components/Student/StudentDashboard';
import StudentCategory from './Components/Student/StudentCategory';
function App() {
  const [renderComponent,setRenderComponent] = useState("login");
  const renderPage = () => {
    switch (renderComponent) {
      case 'login':
        return <Login setRenderComponent={setRenderComponent}/>;
      case 'register':
        return <Register setRenderComponent={setRenderComponent}/>;
      case 'adminDashboard':
        return <AdminDashboard setRenderComponent={setRenderComponent}/>;
      case 'studentDashboard':
        return <StudentDashboard setRenderComponent={setRenderComponent}/>;
      case 'adminCategory':
        return <AdminCategory setRenderComponent={setRenderComponent}/>;
        case 'studentCategory':
        return <StudentCategory setRenderComponent={setRenderComponent}/>;
      default:
        return null;
    }
    };
  return (
    <div>
      {renderPage()}
    </div>
  );
}

export default App;
