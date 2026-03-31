import { Routes, Route } from "react-router-dom";
import TopNavi from "./components/TopNavi";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import CommonLayout from "./components/CommonLayout";
import LayoutIndex from "./components/LayoutIndex";
import RouterHooks from "./components/RouterHooks";


export default function Exam(props){
    
    return (<>
        <TopNavi></TopNavi>
        <Routes> {/* 라우터들 설정 시작 */}
            <Route path="/" element={<Home/>}/>

            {/* 중첩 라우터 */}
            <Route path="/intro" element={<CommonLayout/>}>
                {/* 경로가 별도로 없을때 index 지정, intro */}
                <Route index element={<LayoutIndex/>}/>
                <Route path="router" element={<RouterHooks/>}/>
            </Route>

            <Route path="*" element={<NotFound/>}/>

        </Routes>
    </>);
}