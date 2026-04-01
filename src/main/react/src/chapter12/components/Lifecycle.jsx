import { useEffect, useState } from "react";


function MoveBox(props){
     // props란? 상위컴포넌트에서 해당 컴포넌트 호출시 전달해주는 속성명과속성값 들(여러개)을 객체로 받는 매개변수
    // props -> { initPosition : 50 } vs 구조분해 -> { initPosition }

    // useState란? 컴포넌트내 데이터(값) 상태 관리하는 함수 , 왜? 재렌더링(함수재호출) 하기 위해서
        // const [ 상태명 , set상태명 ] = useState( 초기값 ); 
    console.log('LifeCycle ==> 1. 컴포넌트 실행 (함수 호출)');

    const [position, setPosition] = useState(props.initPosition);
    const [leftCount, setLeftCount] = useState(1);
    // setLeftCount( 3 ); // 수정 , 현재 함수( MoveBox ) 재 실행이 된다. -> return 다시 반환된다 -> html 다시 그리기
    // vs
    // let rightCount = 2; // 변수 선언 
    // rightCount = 3; // 수정 ,현재 함수( MoveBox ) 재 실행이 안된다.

    // [0] CSS 문법을 객체지향으로 표현 가능 , 변수대입은 `${}` 백틱 사용하여 대입 가능
    const boxStyle = {
        backgroundColor: 'red', position: 'relative', textAlign: 'center',
        width: '100px', height: '100px', margin: '10px', lineHeight: '100px',
        left: `${position}px` 
    };

    const moveLeft = () => {
         // 함수선언부 , function 함수명(){}  vs  function(){}  vs  ()=>{}   
        setPosition( () => position -20);
        setLeftCount( () => leftCount +1);
    };

    const moveRight = () => {
        setPosition( () => position +20);
    };

    useEffect(function(){
        console.log('useEffect 실행 ==> 3. 컴포넌트 마운트'); // 최초 렌더링시 실행 // moveLeft 함수 실행
        return()=>{
            console.log('useEffect 실행 ==> 4. 컴포넌트 언마운트') // 재(다시) 랜더링 하면 기존 렌더링된 컴포넌트(화면/함수는) 지운다.
        }
        // 의존성배열이란? state(상태)변수 배열내 대입하여 해당하는 상태가 변경되면 useEffect 실행
    // }); // 의존성 배열 생략
    // }, []); // 의존성 배열에 빈배열 지정
    }, [leftCount]);

    console.log('return실행 ==> 2. 랜더링(return문)');
    return(
        <div>
            <h4>함수형 컴포넌트의 생명주기</h4>
            <div style={boxStyle}>{leftCount}</div>
            <input type="button" value="좌측이동" onClick={moveLeft}/>
            <input type="button" value="우측이동" onClick={moveRight}/>
        </div>
    );
}

export default function(props){
    return(<>
        <h2>React Hook - useEffect</h2>
        <MoveBox initPosition={50}/>
    </>)
}