import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function Signup(props){
    const navigate = useNavigate();
    const signup = async(e)=>{
        e.preventDefault(); // form 마크업의 기본 이벤트 제거 
        // 1) 입력받은 값 가져오기
        const mid = e.target.mid.value;
        const mpwd = e.target.mpwd.value;
        const mname = e.target.mname.value;
        // 2) 객체 구성 : 전송할 내용
        const obj = {mid, mpwd, mname}
        // 3) axios 동기 통신
        const response = await axios.post("http://localhost:8080/api/member2/signup", obj)
        const data = response.data;
        if(data){
            alert("회원가입 성공");
            navigate("/member/login") 
        }else{alert("회원가입 실패")}
    }

    return(<>
        <div>
            <h2>회원가입 페이지</h2>
            <form onSubmit={signup}> { /* 통신함수 연결  */}
                아이디 : <input name="mid" placeholder="아이디 입력"/> <br/>
                비밀번호 : <input name="mpwd" placeholder="비밀번호 입력"/> <br/>
                닉네임 : <input name="mname" placeholder="닉네임 입력"/> <br/>
                <button type="submit">회원가입</button>
                {/* submit 현재 form 안에 있는 마크업들 전송 이벤트 */}
            </form>
        </div>
        
    </>)
}