
//js에서 쿼리 스트링의 값 가져오는 방법
//new URLSearchParams(location.search).get(변수명);

const bno = new URLSearchParams(location.search).get("bno");
console.log(bno);

const 개별조회 = async ()=>{
    const boardBox = document.querySelector('#boardBox');
    let html = '';
    const response = await axios.get(`/board/detail?bno=${bno}`);
    const data = response.data;

    html =`
        제목 : <div> ${data.btitle}</div>
        작성자/작성일 : <div> ${data.bwriter} / ${data.createDate} </div>
        내용 : <div>${data.bcontent}</div>
        <button onclick= 개별수정(${data.bno})>수정</button>
        <button onclick=삭제(${data.bno})>삭제</button>
    `;
    boardBox.innerHTML = html;
}
const 댓글조회 = async  () => {
    const tbody = document.querySelector('#commentBox')
    let html = '';
    const response = await axios.get(`/comment?bno=${bno}`);
    const data = response.data;
    for(index = 0; index <= data.length-1; index++){
        const commentDto = data[index];
        html += `
                <div style="margin: 20px 0px;">
                <div><span>${commentDto.cwriter}</span><span>${commentDto.createDate}</span><span> 
                <button onclick="댓글수정(${commentDto.cno})">수정</button> 
                <button onclick="댓글삭제(${commentDto.cno})">삭제</button></span>
                </div>
                <div> ${commentDto.ccontent} </div>
                </div>
             `
    }
    tbody.innerHTML = html;
}
개별조회();
댓글조회();
// 개별삭제 함수 정의
const 삭제 = async ( bno ) => {
    // 1) 현재 게시물 삭제하기 위해 현재게시물번호 확인 ( bno 는 매개변수 또는 쿼리스트링 )
    // 2) axios 이용하여 서버에게 게시물 삭제 요청 결과받기
    const response = await axios.delete( `/board?bno=${ bno }` );
    const data = response.data;
    // 3) 결과
    if( data == true ){alert( '삭제성공' ); location.href="/종합예제10/index.html";}
    else{ alert('삭제실패'); }
} // f end

// 개별수정 함수 정의
const 개별수정 = async ( bno ) => {
    // 1] 현재 게시물 수정하기 위해 현재게시물번호 확인 ( bno )
    // 2] 수정할 내용 입력받기 , 테스트용
    const btitle = prompt( '수정할 제목 입력하세요.' );
    const bcontent = prompt( '수정할 내용 입력하세요.');
    const bwriter = prompt( '수정할 작성자 입력하세요.');
    // 3] axios 이용한 서버에게 수정 요청 응받 받기
    const obj = { bno , btitle , bcontent , bwriter }; // 수정할 내용들(객체)
    const response = await axios.put( '/board' , obj );
    const data = response.data;
    // 4] 결과
    if( data == true ){
        alert('수정성공');
        location.reload(); // 현재 페이지 새로고침[F5기능]
    }else{ alert('수정실패');}
}

const 댓글등록 = async ()=>{
    const cwriterInput = document.querySelector('.cwriterInput');
    const ccontentInput = document.querySelector('.ccontentInput');
    const cwriter = cwriterInput.value;
    const ccontent = ccontentInput.value;

    const obj = {bno,cwriter,ccontent};

    const response = await axios.post("/comment", obj);
    const data = response.data;
    if (data == true){
        alert('등록성공');
        location.reload();
    }else {alert('등록 실패')}
}
const 댓글삭제 = async ( cno ) => {

    const response = await axios.delete( `/comment?cno=${ cno }` );
    const data = response.data;
    if( data == true ){alert( '삭제성공' ); location.reload()}
    else{ alert('삭제실패'); }
}

const 댓글수정 = async ( cno ) => {
    const ccontent = prompt( '수정할 내용 입력하세요.');
    const cwriter = prompt( '수정할 작성자 입력하세요.');

    const obj = { cno ,  ccontent , cwriter, bno }; // 수정할 내용들(객체)
    const response = await axios.put( '/comment' , obj );
    const data = response.data;
    // 4] 결과
    if( data == true ){
        alert('수정성공');
        댓글조회();
    }else{ alert('수정실패');}
}