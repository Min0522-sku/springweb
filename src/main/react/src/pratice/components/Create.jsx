import axios from "axios";
import { useNavigate } from "react-router-dom";

function WriteForm(props){
    const navigate = useNavigate();
    const taskPost = async(event) => {
        event.preventDefault();
        let title = event.target.title.value;
        let content = event.target.content.value;
        let requester = event.target.requester.value;
        let status = event.target.status.value;

        let obj = {title,content,requester,status}
        const response = await axios.post("http://localhost:8080/api/task", obj);
        const data = response.data;
        console.log(data);
        if(data){alert("등록성공"); navigate("/");}else{alert("등록실패")}

    }
    return(
        <form onSubmit={taskPost}>
            제목 : <input name="title"></input><br/>
            내용 : <input name="content"></input><br/>
            요청자명 : <input name="requester"></input><br/>
            상태 : <select name="status">
                <option>요청</option>
                <option>진행중</option>
                <option>완료</option>
                </select><br/>
            <button type="submit">등록하기</button>
        </form>
    )
}


export default function Create(){
    
    return(<>
        <WriteForm></WriteForm>
    </>)
}