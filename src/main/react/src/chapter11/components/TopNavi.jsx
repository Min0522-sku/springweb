import { Link, NavLink } from "react-router-dom";

const TopNavi = (props) => { // 상단 메뉴/바 = 헤더메뉴 컴포넌트
    // <a> : 클릭하면 브라우저 전체 새로고침(깜빡임) <a href="URL">
    // <Link> : 클릭하면 새로고침 없이 URL 변경(깜빡임 없음) <Link to="URL">

    return(
    <nav>
        <a href="/">Home</a>&nbsp;
        <NavLink to="/intro">인트로</NavLink>&nbsp;
        <NavLink to="/intro/router">Router관련Hook</NavLink>&nbsp;
        <Link to="/xyz">잘못된 URL</Link>&nbsp;
    </nav>
    )
}
export default TopNavi;