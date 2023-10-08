import './App.scss'
import {useState} from 'react'
import Login from './pages/loginAndRegister/Login';
import Register from './pages/loginAndRegister/Register';
import Navbar from './pages/navbar/NavBar';

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
    <div className='app'>
      {renderPage()}
    </div>
  );
}

export default App;
