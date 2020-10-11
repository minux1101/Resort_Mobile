package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.BoardDao;
import domain.Board;

public class BoardService {
	BoardDao boarddao = new BoardDao(); //Board의 객체 생성
	
	public void create() {   /*Board 테이블을 생성하는  boarddao의 메소드 create 호출*/
		boarddao.create();
	}	
		
	public void insert(Board board) {  /*Board 테이블에 데이터를 입력하는 boarddao의 메소드 insert 호출*/
		List<Board> bList = selectAll();
		if (bList.size() <= 0) {  //DB에 데이터가 없으면 공지의 id값을 1로 정한다.
			board.setId(1);
		} else {  //데이터가 있으면 공지의 id값을 가장 큰 값에서 1을 더한 값으로 지정.
			board.setId(bList.get(bList.size()-1).getId() + 1);
		}
		if (board.getRelevel() == 0) { //원글이 0이면 rootid를 id와 같은 값으로 한다.
			board.setRootid(board.getId());
		}
		String date = today(); //공지를 쓴 날짜를 date 값으로 저장
		board.setDate(date);  //공지를 쓴 날짜를 date 값으로 저장
		boarddao.insert(board);		
	}
	
	public String today() { //글 쓴  날짜를 리턴하는 메소드 today()
		Calendar cal = Calendar.getInstance(); // Calender 클래스의 객체cal을 선언하고, 메소드를 이용해서 현재날짜를 가져온다.
		SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd"); // SimpleDateFormat 클래스의 객채 sdt를 생성하고 날짜형식을 정한다.
		return sdt.format(cal.getTime()); //getTime 메소드로 현재 시간을 가져와서 sdt형식으로 변환
	}
	
	// R	
	public List<Board> selectAll() { /*Board테이블에 있는 데이터를 모두 가져오는 boarddao의 메소드 selectAll을 호출*/   
		return boarddao.selectAll();
	}
	
	public Board selectOne(int id) { /*Board테이블에 있는 데이터 하나를 가져오는boarddao의 메소드 selectOne 호출*/
		return boarddao.selectOne(id);
	}
	
	public List<Board> selectPage(int startNum, int cnt) { //DB테이블에 있는 데이터 중 startNum부터 cnt개를 가져오는  boarddao의 메소드 selectPAge 호출	
		return boarddao.selectPage(startNum, cnt);
	}
	
	public List<Board> searchAll(String condition, String search) {  //DB테이블에 있는 모든 데이터를 가져오는 메소드
		return boarddao.searchAll(condition, search); // 만들어진 list 리턴, 레퍼런스 타입이라서 주소를 밖에서 받을 수 있다.
	}
	
	public List<Board> search(String condition, String search, int startNum, int cnt) {	//검색 조건에 해당하는 결과를 리턴하는 메소드
		return boarddao.search(condition, search, startNum, cnt);
	}
	
	public int count() {   /*총 데이터 수를 세는 boarddao의 메소드 count 호출*/
		return boarddao.count();
	}
	
	//U
	public void update(Board gongji) { //DB 테이블에 있는 값을 수정하는  boarddao의 메소드 update를 호출
		boarddao.update(gongji); 
	} 
	
	public void updateRecnt (int rootid, int recnt) { //recnt를 업데이트 하는 메소드
		boarddao.updateRecnt(rootid, recnt);
	}
	
	public int updateViewcnt(int id) { //조회 수를 계산하는 메소드
		return boarddao.updateViewcnt(id);
	 }
	
	//D
	public void delete(int id) {  //DB에 있는 데이터를 삭제하는  boarddao의 메소드 delete를 호출
		boarddao.delete(id);
	}
	
	public void drop() {   /* 테이블을 삭제하는 boarddao의 메소드 drop을 호출*/
		boarddao.drop();
	}
}
