
//1. 저장 메소드
function save(){
  //1. form 가져오기
   let form=$("#saveform")[0];
   let formdata=new FormData(form);
   console.log(formdata);
        $.ajax({
            url:"/room/write",
            method:"POST",
            data:formdata,
            contentType:false,  //첨부파일 전송(사진,영상 등등) 시 사용되는 속성   true (String) //false(byte)
            processData:false,  //첨부파일 전송(사진,영상 등등) 시 사용되는 속성
            success:function(data){
                    if(data){
                         alert("java 와 통신 성공");
                    }else{
                         alert("java 와 통신 실패");
                    }
            }
        });
}

 // 2. 다음 주소 -> 좌표
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div

            mapOption = {
                center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
                level: 5 // 지도의 확대 레벨
            };

        //지도를 미리 생성
        var map = new daum.maps.Map(mapContainer, mapOption);
        //주소-좌표 변환 객체를 생성
        var geocoder = new daum.maps.services.Geocoder();
        //마커를 미리 생성
        var marker = new daum.maps.Marker({
            position: new daum.maps.LatLng(37.537187, 127.005476),
            map: map
        });

//3.검색한 좌표에 마커표시 지도 생성
 function sample5_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var addr = data.address; // 최종 주소 변수

                    // 주소 정보를 해당 필드에 넣는다.
                    document.getElementById("sample5_address").value = addr;

                    // 주소로 상세 정보를 검색 [  geocoder : 주소 -> 좌표  ]
                    geocoder.addressSearch( data.address , function(results, status) {
                        // 정상적으로 검색이 완료됐으면
                        if (status === daum.maps.services.Status.OK) {

                            var result = results[0]; //첫번째 결과의 값을 활용

                            // 해당 주소에 대한 좌표를 받아서
                            var coords = new daum.maps.LatLng(result.y, result.x);

                                    $("#x").val(result.x);
                                    $("#y").val(result.y);

                            // 지도를 보여준다.
                            mapContainer.style.display = "block";
                            map.relayout();
                            // 지도 중심을 변경한다.
                            map.setCenter(coords);
                            // 마커를 결과값으로 받은 위치로 옮긴다.
                            marker.setPosition(coords)
                        }
                    });
                }
            }).open();
        }