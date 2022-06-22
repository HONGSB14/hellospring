function boardWrite(){
    let form=$("#writeForm")[0];
    let formData=new FormData(form);

    $.ajax({
        url:"/boardWrite",
        data:formData,
        method:"POST",
        contentType:false,
        processData:false,
        success:function(data){
            if(data==true){
                alert("글 작성 성공!");
                location.href="/page/boardList";
            }else{
                 alert("글 작성 실패");
            }
        }
    });
}