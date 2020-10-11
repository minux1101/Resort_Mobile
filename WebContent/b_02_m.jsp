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
<br> 
</div>
<h1>교통수단 안내</h1>
<h3>지하철</h3>
<ul> 
   <li>분당선 서현역 4번 출구(AK프라자 2번 출구)에서 성남교육지원청 방향으로 도보 3분</li>
</ul>
<br>
<h3>버스</h3>
<ul>
   <li>광역 3500 이용시</li>
      <ul type="-">
         <li id="list">- 서현역·분당우체국 정류장(분당구청후문 방향) 하차. 분당구청 방향으로 직진 후 성남교육지원청 방향으로 우회전하여 도보 2분</li>
      </ul>
   <li>광역 3500, 마을 3-1, 32, 72, 74, 602 이용 시</li>
      <ul>
         <li id="list">- 서현역·분당우체국 정류장(이매촌 한신.AK프라자 방향) 하차. 분당구청 방향으로 직진 후 성남교육지원청 방향으로 우회전하여 도보 2분</li>
      </ul>
</ul>

</body>
</html>