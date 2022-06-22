lookup();

function lookup(){
    $.ajax({
        url:"/boardLookup",
        success:function(data){
            let html=$("#boardLookup").html();
            if(data){
                console.log(data);
               html +=
                       '<div class="col-md-12 py-5">'+
                            '<form id="updateForm">'+
                                '<div class="row py-3">'+
                                    '<div class="col-md-1">'+
                                        '작성자 :'+
                                   ' </div>'+
                                    '<div class="col-md-5">'+
                                       '<input  class="form-control" type="text" name="bwriter" value='+data.bwriter+'>'+
                                    '</div>'+
                                    '<div class="col-md-1">'+
                                        '비밀번호 :'+
                                   '</div>'+
                                    '<div class="col-md-5">'+
                                        '<input  class="form-control" type="password" id="bpwd" name="bpwd">'+
                                    '</div>'+
                                '</div>'+

                                '<div class="row py-3">'+
                                   ' <div class="col-md-1">'+
                                        '제목 :'+
                                    '</div>'+
                                    '<div class="col-md-11">'+
                                        '<input  class="form-control" type="text" name="btitle" value='+data.btitle+'>'+
                                    '</div>'+
                               '</div>'+

                                '<div class="row py-3">'+
                                    '<div class="col-md-1">'+
                                        '내용 :'+
                                    '</div>'+
                                    '<div class="col-md-11">'+
                                        '<textarea class="form-control" name="bcontent" rows="5" col="5">'+data.bcontent+'</textarea>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="row">'+
                                    '<div class="offset-8 col-md-2">'+
                                        '<button class="form-control" type="button" onclick="boardUpdate('+data.bno+')">수정하기</button>'+
                                    '</div>'+
                                    '<div class="col-md-2">'+
                                        '<button class="form-control" type="button" onclick="boardDelete('+data.bno+')">삭제하기</button>'+
                                    '</div>'+
                                '</div>'+
                            '</form>'+
                       ' </div>';
                       $("#boardLookup").html(html);
            }else{
                   alert("개별조회 실패 !");
            }
        }
    });
}

function boardUpdate(bno){
    let form=$("#updateForm")[0];
    let formData=new FormData(form);

    $.ajax({
        url:"/boardUpdate",
        data:formData,
        method:"PUT",
        contentType:false,
        processData:false,
        success:function(data){
        console.log(data);
            if(data){
                   alert("수정이 완료되었습니다.");
                   location.reload();
            }else{
                    alert("수정실패! 관리자에게 문의해주세요.");
            }
        }
    });
}


function boardDelete(bno){

    $.ajax({
        url:"/boardDelete",
        data:{"bno":bno},
        method:"delete",
        success:function(data){
            if(data){
                    alert("해당 게시글이 삭제되었습니다.");
                    location.href="/page/boardList"
            }else{
                  alert("해당 게시글 삭제 오류 발생");
            }
        }

    });
}