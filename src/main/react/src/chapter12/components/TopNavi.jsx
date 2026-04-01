import { Link, NavLink } from "react-router-dom";

const TopNavi = (props) => { // 상단 메뉴/바 = 헤더메뉴 컴포넌트
    // <a> : 클릭하면 브라우저 전체 새로고침(깜빡임) <a href="URL">
    // <Link> : 클릭하면 새로고침 없이 URL 변경(깜빡임 없음) <Link to="URL">
    // jsx 와 js 구분 : 컴포넌트(함수)내 return(반환값) 뒤로 jsx 문법 그외 js
    // jsx 에서 html 없는 마크업들은 모두 컴포넌트이며 외부 호출시 import 한다.
    // <a> 새고로침(f5)포함 vs NavLink 새로고침(f5)미포함 

    return(
    <nav>
        <NavLink to="/">생명주기</NavLink>&nbsp;
        <NavLink to="/local">내부통신</NavLink>&nbsp;
        <NavLink to="/external">외부통신</NavLink>
    </nav>
    )
}
export default TopNavi;