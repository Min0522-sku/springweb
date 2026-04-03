import { Route, Routes } from "react-router-dom";
import Login from "./pages/member/Login";
import Header from "./components/Header";
import Write from "./pages/board/Write";
import Signup from "./pages/member/Signup";



export default function App(props){
    return(
        <div id="wrap">
            {/* 헤더 */}
            <Header/>
            <Routes>
                {/* 본문들 */}
                <Route path="/member/login" element={<Login></Login>} />
                <Route path="/member/signup" element={<Signup></Signup>}/>
                <Route path="/board/write" element={<Write></Write>} />
                
            </Routes>
            {/* 푸터 */}
        </div>
    )
}