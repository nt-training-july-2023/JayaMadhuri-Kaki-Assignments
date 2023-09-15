import React from 'react';
import {useTimer} from 'react-timer-hook';
import Swal from 'sweetalert2'
const Timer = (props) =>{
    const {expiryTimestamp,setShowQuestion,checkAnswers,setEnable} = props;
    const {
        seconds,
        minutes,
        hours
        } = useTimer({expiryTimestamp, onExpire: () =>{
            Swal.fire({
                title: 'Times Up',
                text: 'Quiz Submitted Successfully',
                timer:1500,
                showConfirmButton:false,
                showCancelButton:false,
                icon:"warning",
                background:"#15172b",
                color:"white",
            });
            checkAnswers();
            setTimeout(function() {
                setEnable(false)
                setShowQuestion(false)
            }, 1500);
        }});
    return(
        <div>
            <div style={{fontSize: '50px'}}>
                <span>{hours}</span>:<span>{minutes}</span>:<span>{seconds}</span>
            </div>
        </div>
    )
}
export default Timer;