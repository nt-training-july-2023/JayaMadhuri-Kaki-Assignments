import React,{useState,useEffect} from 'react';
import axios from 'axios'
import '../Category/AdminCategory.scss'

const StudentCategory = () =>{
    const [category,setCategory] = useState([]);
    const fetchData = async () => {
        try {
          const response = await axios.get("http://localhost:6060/allCategories");
          setCategory(response?.data?.listOfCategories);
        } catch (error) {
          console.error(error);
        }
    };
    useEffect(() => {
        fetchData();
    }, []);
    return(
        <div>
            <div>
                <h1 className='category-title'>Category</h1>
                <hr/>
            </div>
            <div className="category-container">
            {category.map((item) => (
            <div key={item.categoryId} className="category-card">
                {/* <p>Category ID: {item.categoryId}</p> */}
                <p>Name: {item.categoryName}</p>
                <p>Description: {item.categoryDescription}</p>
            </div>
            ))}
            </div>
        </div>
    )
}

export default StudentCategory;