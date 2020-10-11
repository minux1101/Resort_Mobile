<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소금산 출렁다리</title>
<style type="text/css">   
   /* banner */
   .slide {position: relative; width: 370px; height: 300px; margin:0 auto; padding:0; overflow: hidden;}
   .slide ul {position: absolute; margin: 0px; padding:0; list-style: none; }
   .slide ul li {float: left; width: 370px; height: 300px; margin:0; padding:0;}

   h2{
   position:absolute;
   top:200px;
   left:150px;
   width:100%}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script language="JavaScript">
   $(document).ready(function() {
      var $slide = $(".slide").find("ul");

      var $slideWidth = $slide.children().outerWidth();//이미지의 폭
      var $slideHeight = $slide.children().outerHeight(); // 높이
      var $length = $slide.children().length;//이미지의 갯수
      var rollingId;

      //정해진 초마다 함수 실행
      rollingId = setInterval(function() { rollingStart(); }, 2500);//다음 이미지로 롤링 애니메이션 할 시간차
    
      function rollingStart() {
         $slide.css("width", $slideWidth * $length + "px");
         $slide.css("height", $slideHeight + "px");
         //alert(bannerHeight);
         //배너의 좌측 위치를 옮겨 준다.
         $slide.animate({left: - $slideWidth + "px"}, 1800, function() { //숫자는 롤링 진행되는 시간이다.
            //첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
            $(this).append("<li>" + $(this).find("li:first").html() + "</li>");
            //뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
            $(this).find("li:first").remove();
            //다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
            $(this).css("left", 0);
            //이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
         });
      }
   }); 
</script>
</head>
<body>
<div align=center>
<br>
<div>	
	<div class="slide">      
 		<ul>                
            <li><img src="mountain.jpg" width="370" height="300"></li>
            <li><img src="mountain3.jpg" width="370" height="300"></li>
            <li><img src="mountain6.jpg" width="370" height="300" ></li>           
         </ul>
    </div>
</div>					
<br>
<table>
	<tr>
		<td style="text-align:center; font-size:2em;"><b>소금산 출렁다리</b><br></td>		
	</tr>
	<tr>
		<td>
			<br>
			길이 200m, 높이 100m, 폭 1.5m로 산악 보도교 중 국내 최장, 최고의 규모의 소금산 출렁다리<br>
			출렁다리가 있는 소금산 암벽 봉우리 스카이워크 전망대에서는 섬강의 빼어난 풍광을 감상할 수 있습니다.<br>
			출렁다리 진입로는 데크로 조성해 어른, 아이 누구나 즐길 수 있도록 배려했습니다.<br>
			출렁다리와 더불어 주변에 레일바이크와 뮤지엄산 등 문화를 향유하는 시간을 보내기에 좋습니다.<br><br>
			<HR><br>
			이용시간 : [하절기(5월~10월)] 09:00 ~ 18:00<br>
			&emsp;&emsp;&emsp;&emsp;&emsp;[동절기(11월~4월)] 09:00~17:00<br><br>
			<hr></td>
	</tr>
	<tr>
		<td><br>입 장 료 : 3,000원 </td>		
	</tr>
</table>
</div>
</body>
</html>