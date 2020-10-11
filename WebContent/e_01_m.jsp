<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.Gongji, service.GongjiService" %>
<%@ page import="java.util.ArrayList, java.util.List" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script>
function checking() {
	if (document.getElementById("search").value == "" || document.getElementById("search").value == null) {		
		alert("검색할 내용을 입력해주세요.");  /*검색어가  null값일 때 경고창이 나온다.*/
	} else {
		document.filter.action="./gongji_search.jsp";  /*검색어가 있으면gongji_search.jsp로 이동한다.*/
		document.filter.submit();  /*filter의 값을 넘긴다.*/
	}
}
</script>
</head>
<body>
	<div align=center>
	<br><br>
	<table cellspacing=1 width=100% border=0 style="background-color:#FcFcFc;">
	<tr style="background-color:#c4c4c4;"> 
		<td width=100><p align=center>번호</p></td>
		<td width=650><p align=center>제목</p></td>
		<td width=150><p align=center>등록일</p></td>
	</tr>
	<%
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	String loginOK = null;
	loginOK = (String)session.getAttribute("login_ok");
	String pageNum = request.getParameter("pageNum");  /*페이지 번호를 인자로 받아옴*/
	GongjiService gs = new GongjiService();  /*공지 서비스 객체 생성*/
	int cnt = 5;   /*페이지 당 공지의 개수*/
	List<Gongji> gongjiPage = new ArrayList<Gongji>(); //Gongji 타입의 list 생성
	int startNum = 0; /*가져올 데이터의 행 시작 값*/
	if (pageNum == null || pageNum == "") { /*페이지 번호가  null이거나 공백이면 페이지 번호를 1로 하고, 시작 값을 0으로 한다.*/
		pageNum = "1";
		startNum = 0;
	}
	int currentPage = Integer.parseInt(pageNum);
	if(pageNum != "1") { //페이지 번호가 1이 아닐때 들고올 데이터의 시작 행 값
		startNum = currentPage * cnt - cnt;
    }
	gongjiPage = gs.selectPage(startNum, cnt); // 리스트 gongiPage에 selectPage의 결과 저장	
	int pageCount = gs.count() / cnt + (gs.count() % cnt == 0?0:1); /*총 페이지 수*/
	int pageBlock = 10; /* <<부터 >>까지 들어갈 페이지 개수*/	      
	int startPage = ((int)(currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;   /* <<부터 >>까지 들어갈 페이지 중 처음 페이지*/
	int endPage = startPage + pageBlock - 1;  /* <<부터 >>까지 들어갈 페이지 중 마지막 페이지*/
	if (endPage > pageCount) { /* endPage가 총 페이지 개수 보다 커지면 총페이지개수로 저장*/
	    endPage = pageCount;
	 }
	                
	int nPage = startPage-pageBlock;  /* <<에 연결될 페이지 넘버*/
	if (nPage == -9) { /* <<에 연결될 페이지 넘버가 -9일때 (맨 처음 값) 1로 저장.*/
    	 nPage = 1;
	}
	  
	if(gongjiPage.size() > 0) {  /*list에 값이 있을 때*/
		for (Gongji gongji : gongjiPage) {  /*list가 끝날때까지 for문 수행*/
	%>
	<tr>
		<td><p align=center><%=gongji.getId()%></p></td>
		<td><p align=center><a href='./gongji_view.jsp?key=<%=gongji.getId()%>'><%=gongji.getTitle()%></a></p></td>
		<td><p align=center><%=gongji.getDate()%></p></td>
	</tr>
	<%
		}
	}
	%>
	</table>
	<br>
	<%
     if(loginOK != null) {
       if(loginOK.equals("yes")) {
    %>
    <table width=900>
		<tr>
			<td width=850></td>
			<td><input type=button value="신규" Onclick="window.location='./adm_gongji_insert.jsp'"></td>
		</tr>
	</table> 
	<% 
       }
     } 
     %>
	 <table>
         <tr>  <!-- << 에 1페이지를 연결해서 <<을 누르면 1페이지로 돌아감.-->
            <td style=text-align:center; width=20px;><a href="./e_01_m.jsp?pageNum=1">&lt;&lt;</a></td>
            <!-- < 에 연될될 페이지  -->
            <td style=text-align:center; width=20px;><a href="./e_01_m.jsp?pageNum=<%=nPage%>">&lt;</a></td>
    <%         
        for(int i = startPage; i <= endPage; i++){  /* i페이지에 연결될 url*/
    %>      <td style=text-align:center; width=20px;><a href="./e_01_m.jsp?pageNum=<%=i%>"><%= i %></a></td>
    <%
        }           
        if(endPage < pageCount){  /* > 에 연결될 페이지 << >> 사이에 마지막 페이지가 총 페이지 보다 작을 때  */
    %>
            <td style=text-align:center; width=20px;><a href="./e_01_m.jsp?pageNum=<%=startPage+pageBlock%>">&gt;</a></td>
    <%		
        } else {   /* > 에 연결될 페이지 << >> 사이에 마지막 페이지가 총 페이지 보다 작지 않을 때  */
    %>
    		<td style=text-align:center; width=20px;><a href="./e_01_m.jsp?pageNum=<%=pageCount%>">&gt;</a></td>    		
    <%		
        } 
    %>
         <!-- >> 에 마지막 페이지를 연결해서 >> 을 누르면 마지막 페이지로 돌아감.-->
        <td style=text-align:center; width=20px;><a href="./e_01_m.jsp?pageNum=<%=pageCount%>">&gt;&gt;</a></td>
        </tr>
        </table>
    <br>
    <form name="filter">
  	<select style="width:120px; height:1.6em;" name="sch">
      <option value="title" selected>제목</option>
      <option value="id">번호</option>
      <option value="content">내용</option>
      <option value="date">등록일</option>
    </select>
    <input type="text" name="search" id="search">
    <input type=button value="검색" Onclick="checking()">
	</form>
	</div>
</body>
</html>