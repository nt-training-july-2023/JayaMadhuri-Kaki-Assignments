import React from 'react';
import {useTimer} from 'react-timer-hook'

const Timer = ({expiryTimestamp}) =>{
    console.log(expiryTimestamp)
    const {
    seconds,
    minutes,
    hours,
    isRunning,
    start,
    pause,
    resume,
    restart,
    } = useTimer({expiryTimestamp, onExpire: () =>console.warn('OnExpire called')});
    return(
        <div style={{textAlign: 'center'}}>
      <h1>react-timer-hook </h1>
      <p>Timer Demo</p>
      <div style={{fontSize: '100px'}}>
        <span>{hours}</span>:<span>{minutes}</span>:<span>{seconds}</span>
      </div>
      <p>{isRunning ? 'Running' : 'Not running'}</p>
      <button onClick={start}>Start</button>
      <button onClick={pause}>Pause</button>
      <button onClick={resume}>Resume</button>
      <button onClick={() => {
        // Restarts to 5 minutes timer
        const time = new Date();
        time.setSeconds(time.getSeconds() + 30);
        restart(time)
      }}>Restart</button>
    </div>
    );
}
export default Timer;