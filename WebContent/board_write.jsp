<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="domain.*, service.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>  
<%@ page import="java.util.*" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글 쓰기</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	BoardService bser = new BoardService();  /*공지 서비스겍체 생성*/
	String directory = application.getRealPath("/upload/"); /*upload 폴더에 파일이 저장됨.*/
	int maxSize = 1024 * 1024 * 100; /*파일의 최대 크기를 100MB로 제한*/
	String encoding = "UTF-8"; 
	
	MultipartRequest multi = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
	
	String fileName = multi.getOriginalFileName("file1"); //file1을 인자로 받아온다. (사용자가 입력한 이름)
	String fileRealName = multi.getFilesystemName("file1"); //file1을 인자로 받아온다. (시스템에 저장될 이름)
	

	List <Board> list = bser.selectAll();
	
	String key = multi.getParameter("num"); /*공지 번호를 인자로 받아옴*/
	int n1 = 0;
	int rt = 0;
	int maxRecnt = 0;
	List<Integer> l = new ArrayList<Integer>();
	String tit = multi.getParameter("title"); //제목을 인자로 받아온다
	String cont = multi.getParameter("content"); //내용을 인자로 받아온다
	Board bd = new Board();
	bd.setTitle(tit);
	bd.setContent(cont); //받아온 인자 값을 Board 객체에 넣어준다.
	if (key.equals("insert")) { //받아온 key가 insert 일 때 
		if (multi.getParameter("root") != null) { //rootid가  null이 아닐 때 = 댓글일 때 board_reinsert.jsp에서 인자를 받아온다.
			rt = Integer.parseInt(multi.getParameter("root")); //원글 번호
			int rv = Integer.parseInt(multi.getParameter("relv")); //댓글 수준
			int rc = Integer.parseInt(multi.getParameter("recnt")); //댓글 우선순위
			bd.setRootid(rt); 
			bd.setRelevel(rv); //Board 객체 bd의 값으로 넣어준다.
			for (Board b : list) { //list의 크기만큼 for each문 수행
	            if(bd.getRootid() == b.getRootid()) { //입력할 데이터의 rootid와 같고
	               if (bd.getRelevel() == b.getRelevel()) { //relevel이 같으면
	                  l.add(b.getRecnt()); 
	                  maxRecnt = Collections.max(l);  
	                  rc = maxRecnt+1;  // 최대 recnt에 1을 더해서 입력할 데이터에 넣어준다.
	               } else {
	                  rc = rc; // relevel이 다르면 받아온 댓글 우선순위를 데이터에 넣어준다.
	               }
	            }
	         }
			bd.setRecnt(rc);
	         for (Board board : list) { //list의 크기만큼 for each문 수행
	        	 // 입력할 데이터의 댓글 수준이 0보다 크고, list에 있는 데이터 중에서 입력할 데이터와 원글 번호가 같고, 댓글 수준이 작고, 댓글 우선순위가 같은 것이 있으면 updateRecnt를 수행한다.
	            if (bd.getRelevel() > 0 && board.getRootid() == bd.getRootid() && board.getRelevel() < bd.getRelevel() && bd.getRecnt() == board.getRecnt()) {
	               bser.updateRecnt(bd.getRootid(), bd.getRecnt());
	            // 입력할 데이터의 댓글 수준이 0보다 크고, list에 있는 데이터 중에서 입력할 데이터와 원글 번호가 같고, 댓글 수준이 크고, 댓글 우선순위가 같은 것이 있을 때
	            } else if (bd.getRelevel() > 0 && board.getRootid() == bd.getRootid() && board.getRelevel() > bd.getRelevel() && bd.getRecnt() == board.getRecnt()) {
	            	if (bd.getRootid() == board.getRootid() && bd.getRelevel() < board.getRelevel()) { //입력할 데이터와 원글번호가 같고, 댓글 수준이 크면
	            		l.add(board.getRecnt());  
	            		maxRecnt = Collections.max(l);
		                bd.setRecnt(maxRecnt+1); //리스트에 있는 데이터의 댓글 우선순위의 최대값 + 1한 값을 입력할 데이터의 우선순위로 넣어준다.
	            	}
	            }
	         }
			bser.insert(bd); //insert 메소드로 데이터를 입력한다.
			out.print("<H2>" + bd.getTitle() + " (이)가 등록되었습니다</H2>");
		} else {  // rootid가 null 일때 = 신규 insert
			bser.insert(bd); //insert 메소드를 호출해서 데이터 입력
			out.print("<H2>" + bd.getTitle() + " (이)가 등록되었습니다</H2>");
		}
	} else { // 업데이트 일 때
		n1 = Integer.parseInt(multi.getParameter("num")); //공지 번호를 인자로 받아온다.
		bd.setId(n1);
		bser.update(bd); //공지 번호에 해당하는 데이터를 update 메소드를 호출해서 수정한다.
		out.print("<H2>" + bd.getTitle()+ " (이)가 수정되었습니다</H2>");
	}
	 
	if(multi.getOriginalFileName("file1") != null && multi.getFilesystemName("file1") !=null) { // 인자로 받아온 file1이 null이 아닐 때, file 테이블에 데이터를 insert한다.
		FileService fs = new FileService();
		fs.upload(fileName, fileRealName, bd.getId());
	}
%>
	
	<table width=300>
	<tr>
		<td width=250></td> <!-- 목록을 누르면 리스트로 돌아감.-->
		<td><input type=button value="목록" Onclick="window.location='./e_02_m.jsp'"></td>
	</tr>
	</table>
</body>
</html>