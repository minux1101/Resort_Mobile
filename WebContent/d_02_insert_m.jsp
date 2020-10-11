<%@page import="java.time.LocalDate, service.*, domain.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
   String title = null;
    if(request.getParameter("title") != null) {
      title = request.getParameter("title");
    }
    String date = null;
    if(request.getParameter("date") != null) {
      date = request.getParameter("date");
    }
    LocalDate currentDate = LocalDate.now();
    
%>
<head>
<meta charset="utf-8">
<title>예약 하기</title>
<script>

function checking() {
   if (document.getElementById("name").value == "" || document.getElementById("name").value == null
      || document.getElementById("resv_date").value == "" || document.getElementById("resv_date").value == null
      || document.getElementById("room").value == "" || document.getElementById("room").value == null
      || document.getElementById("telnum").value == "" || document.getElementById("telnum").value == null) {      
      swal("필수 내용을 입력해주세요.");  /*이름, 예약날짜, 예약방, 전화번호가 null값일 때 경고창이 나온다.*/
   } else {
      document.reservation.action="d_02_write_m.jsp";  /*내용을 입력했으면 d_02_write.jsp로 이동*/
      document.reservation.submit();  /*reservation의 값을 넘긴다.*/
   }
}
</script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<br>
<center>
<h2>예약 페이지 입니다.</h2>
</center>
<form method="post" name="reservation">
   <table align=center>
      <tr>
         <td width=100px height=30px>성명 *</td>
         <td><input style="height:20px" type="text" name ="name" id="name" onkeyup="this.value=this.value.replace(/[^가-힣a-zA-Z0-9]/g,'');"></td>
      </tr>
      <tr>
         <td width=100px height=30px>예약 일자 *</td>
         <% if (title != null) { %>
               <td><input type="text" name ="resv_date" id="resv_date" value="<%=date %>" readonly></td>
         <% } else {%>
         <td><input type='date' min="<%=currentDate%>" max="<%=currentDate.plusDays(30)%>" name ="resv_date" id="resv_date"></td>
         <% } %>
      </tr>
      <tr>
         <td width=100px height=30px>예약방 *</td>
         <% if (title != null) { %>
               <td><input type="text" name="room" id="room" value="<%=title %>" readonly></td>
         <% } else {%>
         <td><select style="width:120px; height:25px;" name="room" id="room">
                  <option value="스위트">스위트</option>
                  <option value="패밀리">패밀리</option>
                  <option value="디럭스">디럭스</option>      
                </select></td>
          <% } %>
      </tr>      
      <tr>
         <td width=100px height=30px>주소</td>
         <td><input style="height:20px" type="text" name ="addr" id="addr"></td>
      </tr>
      <tr>
         <td width=100px height=30px>전화번호 *</td>
         <td><input style="height:20px" type="text" name ="telnum" id="telnum" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
      </tr>
      <tr>
         <td width=100px height=30px>입금자명</td>
         <td><input style="height:20px" type="text" name ="in_name" id="in_name" onkeyup="this.value=this.value.replace(/[^가-힣a-zA-Z0-9]/g,'');"></td>
      </tr>
      <tr>
         <td width=100px height=30px>남기실 말</td>
         <td><input style="height:20px" type="text" name ="comment" id="comment"></td>
      </tr>
      <tr> <!-- 전송을 누르면 checking 함수를 호출 수행한다. -->
         <td height=30px></td>
         <td><input style="margin-left:90px" type=button value="예약" Onclick="checking()">
         	 <input type=button value="취소" Onclick="window.location='d_02_m.jsp'"></td>
      </tr>   
   </table>
</form>

</body>
</html>