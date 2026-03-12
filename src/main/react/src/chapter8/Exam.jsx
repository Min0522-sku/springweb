// 이미지 불러오는 방법 2
import spring from '../assets/spring.jpg';
import "./index.css" // 현재 컴포넌트에 전통 css 파일 호출
export default function Exam(props){
    const iWidth = {maxWidth: '100px'} // 인라인 css 방식은 객체 형태
    // 조심할점 : max-width --> maxWidth 하이픈 대신에 카멜표현식 사용
    // 정적파일 : public 이하 경로만 지정
    // 즉 /public/img/ .img -> /img/ .img
    
    const myStyle = {
        color : "white",
        backgroundColor : "DodgerBlue",
        padding : "10px", fontFamily: "Verdana"
    };

    return(<>
        <h3>Chapter8 p127</h3>
        <h4>스타일과 이미지 적용법</h4>
        <ol>
            <li style={{color:"red"}}>프론트엔드</li>
            <ul>
                <li><img src="/img/레제.jpg" style={ iWidth }></img></li>
                <li><img src={spring} style={iWidth}></img></li>
                <li><img src='https://placehold.co/600x400' style={iWidth}></img></li>
            </ul>
            <li className='backEnd'>백엔드</li>
            <ul>
                <li id='backEndSub'>Java</li>
                <li class="warnings">Oracle</li>
                <li style={myStyle}>JSP</li>
            </ul>
        </ol>
    </>)
}