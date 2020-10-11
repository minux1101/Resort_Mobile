<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프라이빗 비치</title>
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
            <li><img src="c2.jpg" width="370" height="300"></li>
            <li><img src="beach2.jpg" width="370" height="300"></li>
            <li><img src="beach3.jpg" width="370" height="300"></li>         
         </ul>
    </div>
</div>					
<br>
<table>
	<tr>
		<td style="text-align:center; font-size:2em;"><b>프라이빗 비치</b></td>		
	</tr>
	<tr>
		<td>
			국내 최초로 오토캠핑장이 들어선 월드 리조트의 프라이빗 해변!<br><br>
			<hr><br>
			캠핑, 윈드서핑ㆍ모터보트 등 다양한 해상 레포츠를 즐길 수 있습니다.<br>
			정자ㆍ산책로ㆍ카페테리아 등 편의시설도 잘 갖춰져 있습니다.<br><br>
			<HR><br>
			해상 레포츠 이용 시간 : [하절기] 09:00 ~ 18:00<br><br>
			<hr><br>
			카페테리아 이용시간 : [주중] 7:00 ~ 23:00<br>
			</td>
	</tr>
	<tr>
		<td style="text-align:center;">&emsp; [주말/휴일] 00:00 ~ 24:00</td>		
	</tr>
</table>
</div>
</body>
</html>