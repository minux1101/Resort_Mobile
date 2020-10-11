<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.*,service.*" %>
<%@ page import="java.util.ArrayList,java.util.List,java.text.SimpleDateFormat" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<boby>
	<%
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	String loginOK = null;
	loginOK = (String)session.getAttribute("login_ok");
	int id = Integer.parseInt(request.getParameter("key")); //글 번호를 인자로 받아서 정수로 변환
	BoardService bs = new BoardService();
	bs.updateViewcnt(id);
	Board board = bs.selectOne(id); //해당 글 번호의 공지 객체를 들고옴
	FileService fser = new FileService();
	File file = fser.selectOne(id); //해당 글 번호의 파일 객체를 들고옴
		
	%>
	 <br><br>
	<center>
	<form method="post" name="v">
	<table cellspacing=1 width=100% border=0 style="background-color:#FcFcFc;"> <!-- 들고온 공지의 값들을 테이블에서 출력한다. -->
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=15%>번호</td>
		<td colspan=3><%=id%></td>
	</tr>
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=15%>제목</td>
		<td colspan=3><%=board.getTitle()%></td>
	</tr>
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=15%>일자</td>
		<td colspan=3><%=board.getDate()%></td>
	</tr>
	<tr style="background-color:#F2F4F4;"> 
		<td style="text-align:center" width=15%>조회수</td>
		<td colspan=3><%=board.getViewcnt()%></td>
	</tr>
	<% 
  	if(file == null) { //해당 공지번호의 파일 객체가 없으면 빈칸으로 출력
	%>
   <tr style="background-color:#F2F4F4;">
   		<td style="text-align:center" width=15%>파일</td>
   		<td colspan=3></td>
   </tr>
   <tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>내용</td>
		<td colspan=3 style=overflow:scroll; height=400px><%=board.getContent()%></td>
	</tr>
	<%       
   } else {  //해당 공지번호의 파일 객체가 있으면 파일이름 출력하고, 다운로드 할 수 있게 한다.
	%>
   	<tr style="background-color:#F2F4F4;">
      <td style="text-align:center" width=15%>파일</td>
      <td colspan=3><a href="upload/<%=file.getFileName()%>" download><%=file.getFileName()%></a></td>
  	</tr>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>내용</td>
		<td colspan=3 style=overflow:scroll; height=300px><img src="upload\<%=file.getFileName()%>" width=75% height=350><%=board.getContent()%></td>
	</tr>
	
	<%      
   }
	%>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=15%>원글</td>
		<td colspan=3><input style="border:none; background-color:#FcFcFc;" type="text" name="rootId" id="rootId" value="<%=board.getRootid()%>"></td>
	</tr>
	<tr> 
		<td style="background-color:#F2F4F4; text-align:center" width=20%>댓글수준</td>
		<td style=border:0px; width=30%><%=board.getRelevel()%></td>
		<td style="background-color:#F2F4F4; border:0; width:30%">댓글내 순서</td>
		<td><input style="border:none; background-color:#FcFcFc; width:20%" type="text" value="<%=board.getRecnt()%>"></td>
	</tr>
	</table>
	<br>
	<%
     if(loginOK != null) {
       if(loginOK.equals("yes")) {
    %>
    <table width=900>
		<tr>
			<td width=700></td>
			<td><input type=button value="목록" Onclick="window.location='./e_02_m.jsp'">
			<input type=button value="수정" Onclick="window.location='./adm_board_update.jsp?key=<%=id%>'">
			<input type=button value="삭제" Onclick="window.location='./adm_board_delete.jsp?key=<%=id%>'">
			<input type=button value="댓글" Onclick="window.location='./adm_board_reinsert.jsp?key=<%=id%>'"></td>
		</tr>
	</table> 
	<% 
       }
     } 
	if (loginOK == null) { 
     %>
     <table width=100%>
		<tr>
			<td width=90%></td>
			<td><input type=button value="목록" Onclick="window.location='./e_02_m.jsp'"></td>
		</tr>
	</table> 
	<%
	}
	%>
	</div>
</body>
</html>