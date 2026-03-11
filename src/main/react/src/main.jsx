import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
/* 기존 */
// createRoot(document.getElementById('root')).render(
//     <App />
// )



// // 챕터 4 예제
// import Exam1 from './chapter4/Exam.jsx'; //컴포넌트 불러오기
import Exam2 from './chapter5/Exam2.jsx';
createRoot(document.querySelector('#root')).render(<Exam2/>);






// // index.html(싱글페이지) 에서 root 라는  id 갖는 div 호출
// const root = document.querySelector('#root');
// // root 마크업에 렌더링(render)
// createRoot(root).render(<h1>안녕하세요!</h1>)
// // vs root.innerHTML = "<h1> 안녕하세요! </h1>";