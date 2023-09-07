import './App.scss'
import {useState} from 'react'
import Login from './Components/LoginAndRegister/Login';
import Register from './Components/LoginAndRegister/Register';
import Navbar from './Components/NavBar/NavBar';
import StudentNavbar from './Components/NavBar/StudentNavbar'
function App() {
  const [renderComponent,setRenderComponent] = useState("login");
  const renderPage = () => {
    switch (renderComponent) {
      case 'login':
        return <Login setRenderComponent={setRenderComponent}/>;
      case 'register':
        return <Register setRenderComponent={setRenderComponent}/>;
        case 'navbar':
        return <Navbar setRenderComponent={setRenderComponent}/>;
        case 'studentNavbar':
        return <StudentNavbar setRenderComponent={setRenderComponent}/>;
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
