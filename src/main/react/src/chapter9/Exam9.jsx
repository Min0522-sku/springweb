import { useState } from "react";

function WriteForm(props){
    // event / e 객체 : 해당 이벤트가 발생했을때 그 이베트 정보를 담고 있는 객체
    return(<>
        <form onSubmit={(event) => {
            event.preventDefault(); // 기존 이벤트 제거 
            console.log("이벤트 객체 :", event);
            //let gubun = document.querySelector(); [리액트는 querySelector 방식 하지 않음]
            console.log("이벤트 발생한 주체:", event.target); // <form>
            console.log(event.target.gubun) // <from> --> 하위요소, .target.cless명
            let gubun = event.target.gubun.value; // <from> --> <select>
            let title = event.target.title.value; // <from> --> <input>
            // vs let title = document.querySelector('.title').value;

            props.writeAction(gubun, title); //부모 컴포넌트로 부터 받은 함수에 입력받은 구분과타이틀 대입하여 함수 실행
        }}>
            <select name="gubun">
                <option value="front"> 프론트엔드</option>
                <option value="back"> 백엔드</option>
            </select>
            <input type="text" name="title"></input>
            <input type="submit" value="추가"></input>
        </form>
    </>)
}


export default function Exam9( props){

    //상태변수 : 하나의 값 저장하고 변경되면 해당 컴포넌트 재호출
    const [message, setMessage]  = useState("폼값 전송 진행중")

    // 자식에게 전달함 함수
    const writeAction = (gubun, title) =>{
        if(gubun != '' && title != '' ){
            let frmValue = `검증 완료 폼값 : ${gubun}, ${title}`;
            setMessage(frmValue); // 등록 했을때 setxxx()실행 되면 해당 컴포넌트 재호출 됨
            // return 재실행
        }else{
            alert("빈 칸 있음");
        }
    }
    return(<>
        <h3>Chapter9 p135</h3>
        <WriteForm writeAction={ writeAction }></WriteForm>
        <div>{message}</div>
    </>)
}