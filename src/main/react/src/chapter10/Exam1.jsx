import { useState } from "react";

// 컴포넌트 2
const TopComp = ({MyData}) =>{
    // const 상수 vs let 변수
    // (매개변수) => {} 화살표 함수 vs function(매개변수){} 익명함수 vs function 함수명(매개변수){} 선언 함수
// {MyData}, 구조분해 : 상위 컴포넌트로부터 props 객체를 구조화 해서 변수로 받기(props 객체내 MyData 속성값 변수로 받기)
    // return : 함수의 반환값
    // 리액트JSX return : JSX 반환  , JSX 이란? HTML + JS 조합된 문법
    // JSX 주석? {/* 주석 */}
    // JAVA MAP , 배열/리스트.stram.map((반복변수)->{})
    // JS MAP, 배열/리스트.map((반복변수)=>{})
        // FOREACH 반복문(return 없음) vs MAP 반복문(return 있음)
        // JSX 문법에서 반복된 자료를 HTML로 구성하여 반환하는 구조 다수, MAP 활용 된다.

    return (<>
        {/* 주석 */}
        <ol>
            <li>프론트엔드</li>
            <ul>
                { MyData.front.map( (item, i) => <li key={i}> {item} </li>)}
            </ul>
            <li>백엔드</li>
            <ul>
                { MyData.back.map( (item, i) => <li key={i}> {item} </li>)}
            </ul>
        </ol>
    
    
    </>)

}

// 컴포넌트 1
export default function Exam10( props){
    
    //const [상태명, set상태명] = useState(초기값);
    const [MyData, setMyData] = useState({
        // useState 훅(hook : 갈고리-연결 상태/값/데이터 <-- 갈고리 --> 컴포넌트)
        // 즉] 상태(state)가 (*주소/참조)값 변경되면 컴포넌트 랜더링 된다.
        front : ['HTML5', 'CSS3', 'Javascript', 'jQuery'],
        back : ['Java', 'Oracle', 'JSP', 'SpringBoot'],
    });

    // 리터럴 : 키보드로부터 입력받은 상수 자료,   주소/참조 값 : 자료가 위치한 메모리 주소값;

    //렌더링 안되는 코드 : 동일한 주소값의 값 추가 햇으므로 자동새로고침 안됨
    const addFront = () => {
        MyData.front.push('React'); // .push : 배열내 값 추가 함수, 주소 변경 없이 주소내 값 추가
        setMyData(MyData); // set상태명(새로운주소값); // 상태는 값 변경 감지 하지 않고 상태의 주소값 변경 감지 (**얕은 비교)
    }
    //렌더링 되는 코드,  스프레드 연산자{...기존객체}, [...기존배열],       {...기존객체, 속성명 : 값}, [...기존배열, 값]
    const addBack = () => {
        const newBack = [...MyData.back, 'Node.js'];
        const newMyData = {...MyData, back : newBack}; 
        setMyData(newMyData);
    }
    return <>
        <h1>Chapter</h1>
        <h2>React-Shallow Comparison</h2>
        <TopComp MyData={MyData}/>
        <button type="button" onClick={addFront}>프론트추가</button>
        {/* onclick : HTML 코드, onClick : jsx 코드(가상/가짜 DOM) 
            리액트가 가상/가짜 DOM 사용하는 이유 : HTML이 객체지향이 아니라서 HTML를 객체지향으로 만든 구조 JSX
            DOM : document object model : html 마크업들을 객체지향
        */}
        <button type="button" onClick={addBack}>백엔드추가</button>
    </>
}