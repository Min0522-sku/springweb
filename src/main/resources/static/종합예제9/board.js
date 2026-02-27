console.log("board.js exe");



const onFindAll = async () => {
    const tbody = document.querySelector("#boardTable tbody");
    let html = "";
    const response = await axios.get("/board");

    const data = response.data;
    for (let i = 0; i <= data.length-1; i++){
            const board = data[i];
            html += `
                <tr>
                    <td> ${board.bno} </td>
                    <td> ${board.bcontent} </td>
                    <td> ${board.bwriter} </td>
                    <td> ${board.bdate} </td>
                    <td>
                        <button onclick="onDelete( ${board.bno} )">삭제</button>
                        <button onclick="onUpdate( ${board.bno} )">수정</button>  
                    </td>
                </tr>
                `
    }
    tbody.innerHTML = html;
}
onFindAll();

const onWrite = async () =>{
    // DOM 객체 가져오기
    const bcontentInput = document.querySelector("#bcontent")
    const bwriterInput = document.querySelector("#bwriter")
    // 가져온 DOM 객체내 입력받은 값 꺼내기
    const bcontent = bcontentInput.value;
    const bwriter = bwriterInput.value;
    // 입력받은 값으로 객체 구성
    const obj = {"bcontent" : bcontent, "bwriter" : bwriter}
    // axios 이용하여 서버에 저장 요청
    const response = await axios.post("/board" , obj);
    const data = response.data;
    if(data == true){alert("등록성공"); bcontentInput.value = ''; bwriterInput.value = '';
        onFindAll();
    }
    else {alert("등록실패")}
}

const onUpdate = async ( bno ) => {


    const bcontent = prompt("수정할 내용")
    const obj = {"bcontent" : bcontent, "bno" : bno}
    const response = await axios.put(`/board`, obj);
    const data = response.data;
    if (data == true) {
        alert("수정성공")
        onFindAll();
    } else {
        alert("수정실패")
    }
}

const onDelete = async ( bno ) =>{

    const response = await axios.delete(`/board?bno=${bno}`);
    const data = response.data;
    if(data == true){
        alert("삭제성공")
        onFindAll();
    }else{alert("삭제실패")}
}

