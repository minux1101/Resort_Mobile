<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="domain.*, service.*"%>
<%@ page import="java.util.*, java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8"); /*request로 받아온 파라미터의 인코딩을  utf-8형식으로*/
	RoomCountService rcs = new RoomCountService();
	RoomCount rc = new RoomCount();
	String bookName = request.getParameter("name");
	String date = request.getParameter("resv_date");
	rc.setDate(date);
	int roomNum = 0;
	if (request.getParameter("room").contains("스위트")) {
		roomNum = 1;
		rc.setrName("sweet");
	} else if (request.getParameter("room").contains("패밀리")) {
		roomNum = 2;
		rc.setrName("family");
	} else {
		roomNum = 3;
		rc.setrName("delux");
	}

	ReserveService reserveService = new ReserveService();
	String address = request.getParameter("addr");
	String phone = request.getParameter("telnum");
	String depositName = request.getParameter("in_name");
	String word = request.getParameter("comment");
	Reserve reserve = new Reserve();
	reserve.setName(bookName);
	reserve.setResv_date(date);
	reserve.setRoom(roomNum);
	reserve.setAddr(address);
	reserve.setTelnum(phone);
	reserve.setIn_name(depositName);
	reserve.setComment(word);
	//reserve.setProcessing(bookName);

	rcs.updateMinus(rc);
	reserveService.insert(reserve);
	%>
	<script>
	   swal("예약이 완료되었습니다.")
	   .then((value) => {window.location.href="./d_01_m.jsp";})	   
	</script>
</body>
</html>