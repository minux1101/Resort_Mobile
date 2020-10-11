<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.*,service.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@ page import="java.io.*" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> <!-- 글 편집기를 사용할 수 있게 만들어준다. -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="/resources/js/summernote-ko-KR.js"></script>
<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});

function checking() {  /*제목이 공백이나 null값이면 경고창이 나온다*/
	if (document.getElementById("title").value == "" || document.getElementById("title").value == null) {
		alert("빈 칸을 채워주세요");
	} else {
		document.inst.action="./board_write.jsp"; /*제목이 공백이나 null값이 아니면 board_write.jsp로 이동*/
		document.inst.submit();  /*inst의 값을 넘긴다.*/
	}
}
</script>
</head>
<body>
	<%
		BoardService serv = new BoardService();
		String day = serv.today(); //Board 서비스의 객체 생성 후 today 메소드 호출, 오늘 날짜를 구한다.
	%>
	<br><br>
	<div align=center>
	<form method="post" name="inst" enctype="multipart/form-data">  <!-- inst 폼에 입력한 값들이 board_write.jsp로 넘어간다 -->
	<table cellspacing=1 width=100% border=1 style="background-color:#FFFFFF;">
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>번호</td>
		<td>신규(<input style="border:none; width:34px" type="text" name="num" id="num" value="insert" readonly>)</td>
	</tr>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>제목</td>
		<td><input style="width:85%;" type="text" name="title" id="title"></td>
	</tr>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>일자</td>
		<td><%=day%></td>
	</tr>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>파일</td>
		<td><input type="file" name="file1"></td>
	</tr>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>내용</td>		
		<td height=300px>
		<textarea id="summernote" style="height:200px;overflow-y:scroll;resize:none;"  type="text" name="content"></textarea></td>
	</tr>
	</table>	
	<br>
	<table width=100%>
	<tr>
		<td width=75%></td>
		<td><input type=button value="취소" Onclick="window.location='e_02_m.jsp'"> 
		<input type=button value="쓰기" Onclick="checking()"></td>
	</tr>
	</table>
	</center>
</body>
</html>