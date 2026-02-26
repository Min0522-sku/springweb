console.log("task.js exe");

// 타입: js 는 동적 타입이므로
//1. 기본자료
console.log(3); console.log(true); console.log(3.14); console.log("안녕");
//배열
console.log([3,true,3.14,"안녕"]);
//객체/json
console.log({"name": "유재석", "age" : 40});
//함수
function func1(){}

// 다양한 함수 형식
//function func2(){}//선언적 함수
//function(){} // 익명함수
//() => {} //화살표(람다표션식) 함수
// 화살표 함수는 익명이므로 변수에 저장한다.
const func3 = () => {}



//
/*
    AXIOS
        정의: 대표적으로 비동기/동기 통신 함수
            AXIOS(REACT) VS JS(FETCH) VS JQUERY(AJAX) 등
        목적: JS환경에서 서버와의 통신
        설치: HTML에  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script> CDN 코드 추가




 */

//axios.HTTP메소드명("통신할주소") vs 탈란드api
axios.get("http://localhost:8080/day03/task");

//프론트 서버와 배겡ㄴ드 서버가 같다면 도메인 생략
// .then( (response) => {response.data});
axios
    .delete("/day03/task?name=유재석")
    .then( (response) => {console.log(response.data);});
axios
    .delete("/day03/task?name=유재석")
    .then( (response) => {console.log(response);});
//
axios
    .post("/day03/task?age=40")
    .then( (response) => { console.log( response.data);})
    .catch( (error) => {console.log(error);});
// axios.HTTP메소드명("통신할주소", body)
const obj = {"name" : "유재석", "age" : 40}
axios.put("/day03/task", obj)
    .then( (r) => { console.log(r.data);})
    .catch( (e) => {console.log(e);});

// *비동기 통신 : 요청이 여러개 들어왔을때 먼저 처리된 응답 부터 실행
// 즉] 먼저 처리된 로직 부터 실행됨. 순서 보장 안함

// *동기 통신 : 여러개 요청 했을때 먼저 요청한 로직이 응답 하는 순서대로 실행
// 즉] 먼저 요청한 로직이 먼저 응답 실행. 순서 보장

// async 동기화 키워드
const func5 = async ( ) => {
    // 1. 예외처리
    try{
        // 2. axios 앞에 await 키워드 이용한 동기화
        // 3. axios 결과를 변수/상수에 대입 받는다
        const response =
            await axios.get("/day03/task/axios?name=강호동")
        // 4. 결과를 확인
        console.log(response.data)
    }
    catch (e){console.log(e);}
}
func5();