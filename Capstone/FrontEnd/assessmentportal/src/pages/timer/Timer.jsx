import React, { useState, useEffect } from 'react';
import '../../styles/Timer.scss';
const Timer = (props) => {
  const { expiryTimestamp, checkAnswers, setEnable, isRunning} = props;
  const [hours, setHours] = useState(0);
  const [minutes, setMinutes] = useState(0);
  const [seconds, setSeconds] = useState(0);
  useEffect(() => {
    const [hh, mm, ss] = expiryTimestamp?.split(':')?.map(Number);
    setHours(hh);
    setMinutes(mm);
    setSeconds(ss);
  }, [expiryTimestamp]);
  useEffect(() => {
    let hr = "00", min = "00", sec = "00";
    const timer = setInterval(() => {
     if(isRunning){
      if (seconds === 0) {
        if (minutes === 0) {
          if (hours === 0) {
            clearInterval(timer);
            checkAnswers();
            setEnable(false)
          } else {
            hr = hours - 1;
            min = 59;
            sec = 59;
            setHours(hr);
            setMinutes(min);
            setSeconds(sec);
          }
        } else {
          min = minutes - 1;
          sec = 59;
          setMinutes(min);
          setSeconds(sec);
        }
      } else {
        sec = seconds - 1
        setSeconds(sec);
      }
      localStorage.setItem("time",`${hr?.toString()?.padStart(2, '0')}:${min?.toString()?.padStart(2, '0')}:${sec?.toString()?.padStart(2, '0')}`)
    }
    }, 1000);

    return () => clearInterval(timer);
  }, [hours, minutes, seconds, isRunning]);

  return (
    <div>
        <span className='timer'>{hours.toString().padStart(2, '0')}:</span>
        <span className='timer'>{minutes.toString().padStart(2, '0')}:</span>
        <span className='timer'>{seconds.toString().padStart(2, '0')}</span>
    </div>
  );
};

export default Timer;
