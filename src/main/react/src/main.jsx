import { createRoot } from 'react-dom/client'
import './index.css'
//import App from './App.jsx'
import { BrowserRouter } from 'react-router-dom';

//import {BrowserRouter} from "react-router-dom"


/* 기존 */
// createRoot(document.getElementById('root')).render(
//     <App />
// )
const root = document.querySelector('#root');


// // 챕터 4 예제
// import Exam1 from './chapter4/Exam.jsx'; //컴포넌트 불러오기
//챕터5
//import Exam2 from './chapter5/Exam2.jsx';
//챕터6
//import Exam3 from './chapter6/Exam3.jsx';
//챕터7
//import Exam1 from './chapter7/Exam1.jsx';
//챕터8
//import Exam from './chapter8/Exam.jsx';
//챕터9
//import Exam9 from './chapter9/Exam9.jsx';
//챕터10
//import Exam10 from './chapter10/Exam1.jsx';
//createRoot(document.querySelector('#root')).render(<Exam1/>);
//챕터 11
// import Exam from './chapter11/Exam.jsx';
// import {BrowserRouter} from "react-router-dom" // import 하기
// // 최초 랜더링 되는 컴포넌트에 BrowserRouter 감싼다
// createRoot(root).render(
//     <BrowserRouter>
//         <Exam/>
//     </BrowserRouter>
// )

//챕터 12
// import Exam12 from './chapter12/Exam12.jsx';
// createRoot(root).render(
//     <BrowserRouter>
//         <Exam12></Exam12>
//     </BrowserRouter>
// )

// pratice
import App from './pratice/App.jsx';
createRoot(root).render(
    <BrowserRouter>
        <App></App>
    </BrowserRouter>
)








// // index.html(싱글페이지) 에서 root 라는  id 갖는 div 호출
// const root = document.querySelector('#root');
// // root 마크업에 렌더링(render)
// createRoot(root).render(<h1>안녕하세요!</h1>)
// // vs root.innerHTML = "<h1> 안녕하세요! </h1>";