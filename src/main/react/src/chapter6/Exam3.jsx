import BackComp from "./BackComp";
import FrontComp from "./FrontComp";

export default function Exam3(){
    return(<>
        <h3>Chapter6 p.110</h3>
        <ol>
            <FrontComp></FrontComp>
            <BackComp onMyEvent2={(msg)=>{
                alert(msg);
            }}></BackComp>
        </ol>
    </>)
}