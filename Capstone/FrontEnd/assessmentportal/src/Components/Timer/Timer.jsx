import React from 'react';
import { useTimer } from 'react-timer-hook';
const Timer = (props) => {
    const { expiryTimestamp, setShowQuestion, checkAnswers, setEnable } = props;
    const {
        seconds,
        minutes,
        hours
    } = useTimer({
        expiryTimestamp, onExpire: () => {
            checkAnswers();
            setTimeout(function () {
                setEnable(false)
                setShowQuestion(false)
            }, 1500);
        }
    });
    return (
        <div>
            <div style={{ fontSize: '50px' }}>
                <span>{hours}</span>:<span>{minutes}</span>:<span>{seconds}</span>
            </div>
        </div>
    )
}
export default Timer;