import { Link } from "react-router-dom";

const NotFound = (props) => {
    return(
        <div>
            <h2>Not Found</h2>
            <p>
                페이지를 찾을수 없습니다. ㅜㅜ <br/>
                <Link to="/">Home</Link>
            </p>
        </div>
    )
}
export default NotFound;