console.log("index.js exe")


const 전체조회 = async  () => {
    const tbody = document.querySelector('#boardTable')
    let html = '';
    const response = await axios.get("/board");
    const data = response.data;
    for(index = 0; index <= data.length-1; index++){
        const boardDto = data[index];
        html += `
                <tr>
                <td>${boardDto.bno}</td>
                <td><a href="/종합예제10/detail.html?bno=${boardDto.bno}">${boardDto.btitle}</a></td>
                <td>${boardDto.bwriter}</td>
                <td>${boardDto.createDate}</td>
                </tr>
             `
    }
    tbody.innerHTML = html;
}
전체조회();