import './App.scss'
import {useState} from 'react'
import Login from './Components/LoginAndRegister/Login';
import Register from './Components/LoginAndRegister/Register';
function App() {
  const [renderComponent,setRenderComponent] = useState("login");
  const renderPage = () => {
    switch (renderComponent) {
      case 'login':
        return <Login setRenderComponent={setRenderComponent}/>;
      case 'register':
        return <Register setRenderComponent={setRenderComponent}/>;
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
