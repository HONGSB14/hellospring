boardList();

function boardList(){

    $.ajax({
        url:"/boardList",
        method:"GET",
        success:function(data){
            let html=$("#boardList").html();
            if(data){
                for(let i =0; i<data.length; i++){
                    html +=   '<tr>'+
                                            '<th>'+data[i].bno+'</th><th><a href="/page/boardView/'+data[i].bno+'">'+data[i].btitle+'</a></th><th>'+data[i].bwriter+'</th>'+
                                     '</tr>';
                }
            }else{
                   html += '<tr>게시물 출력 오류</tr>';
            }
            html=$("#boardList").html(html);
        }
    });
}