<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스파 & 온천</title>
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
            <li><img src="c3.png" width="370" height="300"></li>
            <li><img src="spa2.jpg" width="370" height="300"></li> 
         </ul>
    </div>
</div>					
<br>
<table>
	<tr>
		<td style="text-align:center; font-size:2em;"><b>스파 & 온천</b></td>
	</tr>
	<tr>
		<td>
			국내 단 한 곳 밖에 없는 자연용출온천수를 데우거나 식히지 않는 42.4도의 자연 그대로 이용한 온천탕!<br><br>
			<HR><br>
			이용시간 : [주중] 10:00~19:00<br>
			&emsp;&emsp;&emsp;&emsp;&emsp;[주말/휴일] 09:00~20:00<br><br>
			<HR><br>
			이용요금 : [일반] 14,000(주중),20,000(주말),28,000(성수기)<br>
			&emsp;&emsp;&emsp;&emsp;&ensp; [소인] 9,000(주중), 14,000(주말), 19,000(성수기) <br><br>
			<hr>
		</td>		
	</tr>
	<tr>
		<td style="text-align:center;">
			<h3>* 월드 리조트 객실 이용시 무료로 이용할 수 있습니다. *</h3>
		</td>
		</tr>
</table>
</div>
</body>
</html>