// 컴포넌트 만드는 법
// 첫글자가 대문자로 시작하는  .jsx 파일 확장자로 생성
// function 컴포넌트명(){}
// 컴포넌트내 {return(<> jsx문법 </>)}
// 파일내 내보내기 할 컴포넌트 1개, export default 컴포넌트명

function FrontComp(props){ // props는 함수/컴포넌트의 매개변수이며 프롭스 객체
    console.log(props);
    const liRows = [];
    for(let i = 0; i < props.propData1.length; i++){
        liRows.push(
            <li key ={i}>{props.propData1[i]}</li>
        );
    }
    return(<>
        <li>{props.frTitle}</li>
        <ul>
            {liRows}
        </ul>
    </>)
}
const BackComp = ({propData2, baTitle}) =>{
    const liRows = [];
    let keyCnt = 0;
    for(let row of propData2){
        liRows.push(
            <li key={keyCnt++}>{row}</li>
        );
    }
    return(<>
        <li>{baTitle}</li>
        <ul>
            {liRows}
        </ul>
    </>)
}
// 구조 분해 없이 여러개 속성을 한꺼번에 받기
function MyComponent(props){
    return(<>
        
        <p>
            {props.p1}, {props.p2}, {props.p3}, {props.p4}
        </p>
    </>)
}
// 구조분해 사용하여 필요한 속성만 받기
function MyComponent2({p1, p3}){
    return(<>
        <p>
            {p1}, {p3}
        </p>

    </>)
}

function Exam1(){
    const frontData = ['HTML5', 'CSS3', 'JavaScript', 'jQuery']
    const backData = ['Java', 'Oracle', 'JSP', 'Spring Boot']
    return(<>
        <h3>프롭스 예제 p.87</h3>
        <FrontComp propData1={frontData} frTitle="프론트엔드"></FrontComp>
        <BackComp propData2={backData} baTitle={"백엔드"}></BackComp>
        
        <h2>props 객체 사용 p.90</h2>
        <MyComponent p1 ={"HTML"} p2="CSS" p3={'JavaScrpt'} p4 ={"jQuery"}></MyComponent>
        <MyComponent2 p1 ={"HTML"} p2="CSS" p3={'JavaScrpt'} p4 ={"jQuery"}></MyComponent2>
    </>)
}
export default Exam1;