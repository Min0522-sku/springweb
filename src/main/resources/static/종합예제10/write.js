const 등록 = async ()=>{
    const writerInput = document.querySelector('.writerInput');
    const contentInput = document.querySelector('.contentInput');
    const titleInput = document.querySelector('.titleInput');

    const bwriter = writerInput.value;
    const bcontent = contentInput.value;
    const btitle = titleInput.value;

    const obj = {bwriter,bcontent,btitle};

    const response = await axios.post("/board", obj);
    const data = response.data;
    if (data == true){
        alert('등록성공');
        location.href="/종합예제10/index.html"
    }else {alert('등록 실패')}

}