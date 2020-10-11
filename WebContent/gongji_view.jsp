<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.Gongji, service.GongjiService" %>
<%@ page import="java.util.ArrayList, java.util.List, java.text.SimpleDateFormat" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	String loginOK = null;
	loginOK = (String)session.getAttribute("login_ok");
	int id = Integer.parseInt(request.getParameter("key")); /*key값을 인자로 받아와서 정수로 변환해서 id 저장.*/
	GongjiService gs = new GongjiService();  /*공지 서비스 객체 생성*/
	Gongji gongji = gs.selectOne(id);  /*공지 객체 생성해서 selectOne 메소드의 결과를 저장*/
	%>	
	<br><br>
	<div align=center>
	<table cellspacing=1 width=100% border=0 style="background-color:#FcFcFc;">
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=150>번호</td>  
		<td><%=id%></td>  <!-- 공지의 번호를 출력 -->
	</tr>
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=150>제목</td>
		<td><%=gongji.getTitle()%></td>  <!-- 공지의 제목을 출력 -->
	</tr>
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=150>일자</td>
		<td><%=gongji.getDate()%></td>   <!-- 공지의 날짜를 출력 -->
	</tr>
	<tr> 
		<td width=150 style="background-color:#F2F4F4; text-align:center">내용</td>
		<td style=overflow:scroll; height=300px><%=gongji.getContent()%></td> <!-- 공지의 내용 출력 -->
	</tr>
	</table>
	<br>
	<%
     if(loginOK != null) {
       if(loginOK.equals("yes")) {
    %>
    <table width=900>
		<tr>
			<td width=750></td>
			<td><input type=button value="목록" Onclick="window.location='./e_01.jsp'"> 
			<input type=button value="수정" Onclick="window.location='adm_gongji_update.jsp?key=<%=id%>'">
			<input type=button value="삭제" Onclick="window.location='adm_gongji_delete.jsp?key=<%=id%>'"></td>
		</tr>
	</table> 
	<% 
       }
     }
	if(loginOK == null) {
     %>
	<table width=100%>
	<tr>
		<td width=90%></td>  <!-- 목록 버튼을 누르면 e_01.jsp로 이동 -->
		<td><input type=button value="목록" Onclick="window.location='./e_01_m.jsp'"></td>
	</tr>  <!-- 수정 버튼을 누르면 해당 공지의 gongji_update.jsp로 이동 -->
	</table>
	<% 
	}
	%>
	</div>
</body>
</html>