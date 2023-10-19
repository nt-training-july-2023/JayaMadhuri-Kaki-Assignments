import logo from './logo.svg';
import './App.css';
import Timer from './Timer';

function App() {
  const time = new Date();
  time.setMinutes(time.getMinutes() + 10);
  return (
    <div>
      <Timer expiryTimestamp={time}/>
    </div>
  );
}

export default App;
