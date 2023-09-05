
const AdminDashboard = (props) =>{
    const {setRenderComponent} = props;
    return(
        <div>
            <p>This is Admin Dashboard</p>
            <button onClick={()=>{
                setRenderComponent("login")
            }}>logout</button>
        </div>
    )
}

export default AdminDashboard;