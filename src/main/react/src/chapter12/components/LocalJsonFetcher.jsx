import axios from "axios";
import { useEffect, useState } from "react";


const GlobalTop = (props) => {
    console.log('1. 컴포넌트 실행');
    const [myList, setMyList] = useState([]); // 빈배열 갖는 상태변수

    // AXIOS 사용하기
    // async function axios1(){}
    const axios1 = async() =>{ // axios 통신하는 함수 , async 동기화 
        const response = await axios.get('./json/myData.json'); // await 동기화된 axios 통신
        // response 에는 HTTP 응답 관련 정보들이 객체로 들어있음
        // 그중에 response.data는 실질적인 결과물(내용물/body) 있음
        const result = response.data; // 통신 결과내 .data 가 실질적인 결과물(내용물/body) 
        setMyList(result); //axios 통신결과를 상태변수에 대입 <랜더링>

    }

    useEffect( () => {
        console.log('3. useEffect 실행')
        axios1();
    }, []);

    //  교재 방식
    // useEffect( () => {
    //     console.log('3. useEffect 실행');
    //     fetch('./json/myData.json')
    //     .then((result)=>{
    //         return result.json();
    //     })
    //     .then((json) => {
    //         console.log(json);
    //         setMyList(json);
    //     });
    // }, []);
    
     // [3] 현재 상태(myList => json => axios ) 정보를 반복하여 html 구성 함수.
    // 리스트/배열변수명.map( (반복변수) => { return (<> JSX </>) } )  // 주로 HTML 구성할 때 사용한다.
    // myList = [{"num":1, "id":"yu", "name":"유비", "cell":"(02) 235-1111"},{"num":2, "id":"kwan", "name":"관우", "cell":"(051) 235-2222"},{"num":3, "id":"jang", "name":"장비", "cell":"(031) 235-3333"}]

    let listTag = myList.map((data)=>{
         // 첫번째 반복 data = {"num":1, "id":"yu", "name":"유비", "cell":"(02) 235-1111"}
        // 두번째 반복 data = {"num":2, "id":"kwan", "name":"관우", "cell":"(051) 235-2222"}
        // 세번째 반복 data = {"num":3, "id":"jang", "name":"장비", "cell":"(031) 235-3333"}

        // onClick = { 함수선언또는함수명 }
        return(
            <li key={data.id}>
                <a href={data.id} data-id={data.num} onClick={(e)=>{
                    e.preventDefault(); // a마크업 관련된 기본 기능 제거 ( 깜빡거리는 기능 제거 ) 
                    props.myLinkClick(e.target.dataset.id); // 부모로 부터 전달받은 함수(myListClick) 실행 
                    {/* e.target 이벤트를 발생시킨 마크업 */}
                }}>{data.id}</a>
            </li>
        );
    });
    // 변수예측 listTag = <li> <a > yu </a> </li> <li> <a > kwan </a> </li> <li> <a > jang </a> </li>
    console.log("2. return 실행(rendering)")
    return(
        <nav>
            <ul>
                {listTag}
            </ul>
        </nav>
    );
}

// 클릭된 정보를 그리는 컴포넌트 
const ContentBody = (props)=>{
    return(
        <div>
            <h2>{props.myResult.name}</h2>
            <ul>
                <li>num : {props.myResult.num}</li>
                <li>id : {props.myResult.id}</li>
                <li>cell : {props.myResult.cell}</li>
                <li>description : {props.myResult.description}</li>
            </ul>
        </div>
    )
}




export default function LocalJsonFetcher(props){

    const [myResult, setMyResult] = useState({}); // 상태변수, 배열이 아닌 객체
    console.log( myResult ); // 확인 
    return(<>
        <h3>내부 서버 통신</h3>
        <GlobalTop myLinkClick = { async (num) =>{
            //  async ( ) => { } vs  async function(){ } vs async function함수명(){}    
            console.log('클릭', num);

            // fectch 대신에 axios
            const response = await axios.get(`./json/dto${num}.json`);
            const result = response.data;
            setMyResult(result);
        }}></GlobalTop>

        <ContentBody myResult={myResult}></ContentBody>
    </>)
}