/* p117
   부모 컴포넌트로 부터 상태변경 함수를 props로 받아서
   a 클릭하면 상태를 front로 변경한다 
   *a 마크업 클릭하면 페이지 이동 차단: event.preventDefault();
*/
export default function FrontComp(props){
    return(<>
        <li>
            <a href="/" onClick={()=>{
                event.preventDefault();
                props.onSetMode('front')
            }}
            >프론트엔드</a>
        </li>
        <ul>
            <li>HTML5</li>
            <li>CSS3</li>
            <li>Javascript</li>
            <li>jQuery</li>
        </ul>
    </>)
}