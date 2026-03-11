import { useState } from "react"
import FrontComp from "./FrontComp";
import BackComp from "./BackComp";

export default function Exam1(){
    // 1) useState 선언 : const [ 변수명, set변수명(함수)] = useState(초기값);

    const [mode, setMode] = useState('both'); // ???

    // 2) 상태(값) 변경하는 함수 : set변수명(새로운값);
    const handleSetMode = (mode) => {setMode(mode);}

    // 3) 컴포넌트 저장용 변수
    let contents = '';
    // 4) 상태에 따라 컴포넌트 그리기, setMode가 실행되서 상태가 변경되면 화면을 다시 그리므로(리렌더링/함수(컴포넌트) 재호출)
    if( mode == 'front'){ // 4-1) mode 가 front 이면
        contents = <>
            <FrontComp onSetMode={ (mode) => { setMode(mode); } }></FrontComp>
        </>
    }else if(mode == 'back'){ // 4-2) mode 가 back 이면
        contents = <>
            <BackComp setMode = {setMode}></BackComp>
        </>
    }else{ // 4-3) mode가 front/back 아니면 both
        contents = <>
            <FrontComp onSetMode={ (mode) => { handleSetMode(mode); } }></FrontComp>
            <BackComp setMode = {handleSetMode}></BackComp>
        </>
    }
    // 5) 분기에 따른 결과물을 출력
    return(<>
        <h3> Chapter7 p.118</h3>
        <a href="/" onClick={()=>{setMode('both')}}>React-State</a>
        <ol>
            {contents}
        </ol>

    </>)
} 