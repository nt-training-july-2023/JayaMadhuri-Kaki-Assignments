
const StudentDashboard = (props) =>{
    const {setRenderComponent} = props;
    return(
        <div>
            <p>This is student dashboard</p>
            <button onClick={()=>{
                setRenderComponent("login")
            }}>Logout</button>
        </div>
    )
}

export default StudentDashboard;