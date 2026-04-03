import axios from "axios";
import { use, useEffect, useState } from "react"
import { Link } from "react-router-dom";


function GetTaskList(props){
    const [myList, setMyList] = useState([]);

    const getTaskListAxios = async() =>{
            const response = await axios.get("http://localhost:8080/api/task");
            console.log(response.data);
            const result = response.data;
            setMyList(result);
    }
    const deleteTask = async() => {
        const response = await axios.delete("http://localhost:8080/api/task?")
    }
    useEffect(()=>{
        getTaskListAxios();
    },[]);

    let trTag = myList.map((data) => {
        return(
            <tr key={data.id}>
                <td><a href={`/task/detail?id=${data.id}`} >{data.title}</a></td>
                <td>{data.requester}</td>
                <td>{data.status}</td>
                <td>{data.createDate.split('T')[0]}</td>
                <td>
                    <button onClick={}>수정</button>
                    <button onClick={}>삭제</button>
                </td>
            </tr>
        )
    })

    return(
        <div>
            <table border='1'>
                <thead>
                    <tr>
                        <th>제목</th><th>요청자</th><th>상태</th><th>요청일</th><th>비고</th>
                    </tr>
                </thead>
                <tbody>{trTag}</tbody>
            </table>
        </div>
    )

}

export default function Index(){
    return(<>
        <h2>전체조회</h2>
        <Link to="/task/create">등록</Link>
        <GetTaskList></GetTaskList>
    </>)
}