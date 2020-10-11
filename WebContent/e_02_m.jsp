<%@page import="domain.*, service.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.ArrayList,java.util.List" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script>
function checking() {
	if (document.getElementById("search").value == "" || document.getElementById("search").value == null) {		
		alert("검색할 내용을 입력해주세요.");  /*검색어가  null값일 때 경고창이 나온다.*/
	} else {
		document.filter.action="./board_search.jsp";  /*검색어가 있으면 board_search.jsp로 이동한다.*/
		document.filter.submit();  /*filter의 값을 넘긴다.*/
	}
}
</script>
</head>
<body>
	<center>
	<br><br>
	<table cellspacing=1 width=100% border=0 style="background-color:#FcFcFc;">
	<tr style="background-color:#c4c4c4;"> 
		<td width=50><p align=center>번호</p></td>
		<td width=570><p align=center>제목</p></td>
		<td width=100><p align=center>파일</p></td>
		<td width=80><p align=center>조회수</p></td>
		<td width=100><p align=center>등록일</p></td>
	</tr>
	<%
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	String pageNum = request.getParameter("pageNum"); //pageNum을 인자로 받아옴
	BoardService bs = new BoardService(); //board 서비스 객체 생성
	int cnt = 5; // 한 페이지에 나올 글 개수
	List<Board> boardPage = new ArrayList<Board>(); //Board 타입의 list 생성
	int startNum = 0; //시작 행 값
	if (pageNum == null || pageNum == "") { //페이지 넘버가 없으면 시작페이지는 1, 시작 행은 0
		pageNum = "1";
		startNum = 0;
	}
	int currentPage = Integer.parseInt(pageNum); //현재 페이지 넘버
	if(pageNum != "1") {
	startNum = currentPage * cnt - cnt; //페이지가 1이 아닐 때 시작 행
	}
	boardPage = bs.selectPage(startNum, cnt);
	int pageCount = bs.count() / cnt + (bs.count() % cnt == 0?0:1); /*총 페이지 수*/
	int pageBlock = 10; /* <<부터 >>까지 들어갈 페이지 개수*/
	/* <<부터 >>까지 들어갈 페이지 중 처음 페이지*/
	int startPage = ((int)(currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1; 
	int endPage = startPage + pageBlock - 1;  /* <<부터 >>까지 들어갈 페이지 중 마지막 페이지*/
	if (endPage > pageCount) { /* endPage가 총 페이지 개수 보다 커지면 총페이지개수로 저장*/
		endPage = pageCount;
	}
			                
	int nPage = startPage-pageBlock;  /* <<에 연결될 페이지 넘버*/
	if (nPage == -9) { /* <<에 연결될 페이지 넘버가 -9일때 (맨 처음 값) 1로 저장.*/
		nPage = 1;
	}
			  
	if(boardPage.size() > 0) {  /*list에 값이 있을 때*/
		for (Board board : boardPage) {  /*list가 끝날때까지 for문 수행*/
			String hyphen = "";
			if (board.getRelevel() > 0) { //댓글일 경우 relevel 만큼 -을 붙여서 화살표를 만들고 [RE]를 붙여준다.
				for(int i = 0; i < board.getRelevel(); i++) {
					hyphen = hyphen + "-";
				}
				board.setTitle(hyphen + ">[Re]" + board.getTitle());
			}
	%>
	<tr>
		<td width=50><p align=center><%=board.getId()%></p></td>
		<%
		if (board.getDate().equals(bs.today()) ) {	//날짜가 오늘 날짜와 같으면 뒤에 [new]를 붙임
		%>
		<td width=570><a href='board_view.jsp?key=<%=board.getId()%>'><%=board.getTitle()%> [New]</a></td>
		<%
		}else {	
		%>
		<td width=570><a href='board_view.jsp?key=<%=board.getId()%>'><%=board.getTitle()%></a></td>
		<%
		}
		FileService fs = new FileService();
		File file = fs.selectOne(board.getId()); //file 객체에 db에서 불러온 객체를 집어 넣음.
		if(file == null) { //file이 null값일 때
		%>	
		<td width=100></td>  <!-- 빈 칸 --> 
		<%
		} else {  //file이 null이 아닐 때 이미지 출력
		%>		
			<td width=100><p align=center><img src="file.jpg"></td> 
		<%
		}
		%>
		<td width=80><p align=center><%=board.getViewcnt()%></p></td>
		<td width=100><p align=center><%=board.getDate()%></p></td>
	</tr>
	<%
		}
	}
	%>
	</table>
	<br>
	<table width=100%>
	<tr>
		<td width=90%></td>
		<td><input type=button value="신규" Onclick="window.location='board_insert.jsp'"></td>
	</tr>
	</table>
	 <table>
         <tr>  <!-- << 에 1페이지를 연결해서 <<을 누르면 1페이지로 돌아감.-->
            <td style=text-align:center; width=20px;><a href="./e_02_m.jsp?pageNum=1">&lt;&lt;</a></td>
            <!-- < 에 연될될 페이지  -->
            <td style=text-align:center; width=20px;><a href="./e_02_m.jsp?pageNum=<%=nPage%>">&lt;</a></td>
    <%         
        for(int i = startPage; i <= endPage; i++){  /* i페이지에 연결될 url*/
    %>      <td style=text-align:center; width=20px;><a href="./e_02_m.jsp?pageNum=<%=i%>"><%= i %></a></td>
    <%
        }           
        if(endPage < pageCount){  /* > 에 연결될 페이지 */
    %>
            <td style=text-align:center; width=20px;><a href="./e_02_m.jsp?pageNum=<%=startPage+pageBlock%>">&gt;</a></td>
    <%		
        } else {
    %>
    		<td style=text-align:center; width=20px;><a href="./e_02_m.jsp?pageNum=<%=pageCount%>">&gt;</a></td>    		
    <%		
        } 
    %>
         <!-- >> 에 마지막 페이지를 연결해서 >> 을 누르면 마지막 페이지로 돌아감.-->
        <td style=text-align:center; width=20px;><a href="./e_02_m.jsp?pageNum=<%=pageCount%>">&gt;&gt;</a></td>
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
</center>
</body>
</html>