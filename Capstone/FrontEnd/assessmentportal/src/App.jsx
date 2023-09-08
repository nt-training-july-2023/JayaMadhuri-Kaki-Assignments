import './App.scss'
import {useState} from 'react'
import Login from './Components/LoginAndRegister/Login';
import Register from './Components/LoginAndRegister/Register';
import Navbar from './Components/NavBar/NavBar';
function App() {
  const [renderComponent,setRenderComponent] = useState("login");
  const [userDetails,setUserDetails] = useState({});
  const renderPage = () => {
    switch (renderComponent) {
      case 'login':
        return <Login setRenderComponent={setRenderComponent} setUserDetails={setUserDetails}/>;
      case 'register':
        return <Register setRenderComponent={setRenderComponent}/>;
        case 'navbar':
        return <Navbar setRenderComponent={setRenderComponent} userDetails={userDetails}/>;
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
