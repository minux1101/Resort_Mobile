<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<div align=center style="width:100%;">
   <div class="map" style="width:100%;">
      <div class="kakaoMap" style="width:90%;">
         <div id="map" style="width:100%; height:300px;"></div>
         <!--실제 지도를 그리는 Javascript API를 불러오기, kakao map 이용-->
         <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a73e97284819f30fb4cd62f2a90d29c"></script>
         
         <script>
            var mapContainer = document.getElementById('map'), <!--지도를 표시할 div-->
               mapOption = {
                        center: new kakao.maps.LatLng(37.3860521, 127.1214038), <!--지도의 중심 좌표-->
                        level: 3  <!--지도의 확대 레벨-->
                     };
            var map = new kakao.maps.Map(mapContainer, mapOption); <!--지도를 생성-->
            var markerPosition = new kakao.maps.LatLng(37.3860521, 127.1214038); <!--마커가 표시될 위치-->

            var marker = new kakao.maps.Marker({ <!--마커를 생성-->
               position: markerPosition
            });

            marker.setMap(map); <!--마커가 지도 위에 표시 되도록 설정-->

         </script>
      </div>
   </div>
<br>주소 [13590] 경기도 성남시 분당구 황새울로 329번길 5<br>(서현동 272-6)
</div>
</body>
</html>