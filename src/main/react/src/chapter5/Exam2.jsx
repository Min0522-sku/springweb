function FrontComp(props){
    return(<>
        {/*  JSX( return에서 사용되는 (xm)l 문법) 에서 주석처리 하는 방법 */ }
        <li>
            <a href="/" onClick={()=>{props.onMyEvent1();}}>프론트엔드</a>
        </li>
        <ul>
            <li>HTML5</li><li>CSS3</li> <li>JavaScript</li> <li>jQuery</li>
        </ul>
    </>)
}




function Exam2(){
    function 출력함수(){alert('출력된 메시지')}
    // 익명함수 : 이름없는 함수 (재사용 x/ 일회성 또는 이벤트 함수)
    // 화살표 함수 : 이름이 없고 => 화살표 표현식 사용하는 함수 ( 주로 변수에 저장하여 재사용 함)
    return(<>
        <h3>이벤트 처리 p.100</h3>
        <button onClick={출력함수}>함수 버튼</button>
        <button onClick={function(){alert('출력된 메세지2');}}>익명함수버튼</button>
        <button onClick={() => {alert('출력3')}}>화살표함수버튼</button>  

        <FrontComp onMyEvent1={()=>{alert('프론트엔드 클릭됨');}}></FrontComp>
    </>)
}
export default Exam2;

/*
    리액트 이벤트에서 주의할점
    1. onclick --> onClick , 합성이벤트 (리액트가 핸들러/연결 통해 이벤트 실행)
    2. onClick 에서 함수 실행하는 구조 아니고 *전달!* 하는 구조
        1] 선언, function 함수명(){}
        2] 호출, 함수명()
        3] 함수 : 여러개 계산식/코드(데이터x) 묶음, 변수 : 하나의 값 저장하는 공간
        **html : <button onclick="출력함수()">
        **react : <button onClick={출력함수}> , <button onClick={ () => {출력함수()} }

    이벤트 헨들러 함수가 실행되고 그 내부에서 필요한경우 상태를 업데이트를 하고 
    상태가 변경되면 리액트는 변경된 내용을 반영하기 위해 컴포넌트를 리렌더링한다
*/