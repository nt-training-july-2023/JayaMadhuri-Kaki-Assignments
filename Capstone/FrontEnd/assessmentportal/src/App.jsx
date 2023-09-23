import './App.scss'
import {useState} from 'react'
import Login from './Components/LoginAndRegister/Login';
import Register from './Components/LoginAndRegister/Register';
import Navbar from './Components/NavBar/NavBar';
function App() {
  const renderComponent_AfterRefresh = localStorage.getItem("Current_Window")
  const [renderComponent,setRenderComponent] = useState(renderComponent_AfterRefresh);
  const [userDetails,setUserDetails] = useState({});
  const renderPage = () => {
    switch (renderComponent) {
      case 'register':
        return <Register setRenderComponent={setRenderComponent}/>;
      case 'navbar':
        return <Navbar setRenderComponent={setRenderComponent} userDetails={userDetails}/>;
      default:
        return <Login setRenderComponent={setRenderComponent} setUserDetails={setUserDetails}/>;
    }
    }
  return (
    <div>
      {renderPage()}
    </div>
  );
}

export default App;
