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

import dao.GongjiDao;
import domain.Gongji;

public class GongjiService {
	GongjiDao gongjidao = new GongjiDao(); //GongjiDao의 객체 생성
	
	public void create() {   /*Gongji 테이블을 생성하는  gongjidao의 메소드 create 호출*/
		gongjidao.create();
	}	
		
	public void insert(Gongji gongji) {  /*Gongji 테이블에 데이터를 입력하는 gongjidao의 메소드 insert 호출*/
		List<Gongji> gList = selectAll();
		if (gList.size() <= 0) {  //DB에 데이터가 없으면 공지의 id값을 1로 정한다.
			gongji.setId(1);
		} else { //데이터가 있으면
			gongji.setId(gList.get(0).getId() + 1); //공지의 id값을 가장 큰 값에서 1을 더한 값으로 지정.
		}
		String date = today();
		gongji.setDate(date); //공지를 쓴 날짜를 date 값으로 저장
		gongjidao.insert(gongji);		
	}
	
	public String today() { //글 쓴  날짜를 리턴하는 메소드 today()
		Calendar cal = Calendar.getInstance(); // Calender 클래스의 객체cal을 선언하고, 메소드를 이용해서 현재날짜를 가져온다.
		SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd"); // SimpleDateFormat 클래스의 객채 sdt를 생성하고 날짜형식을 정한다.
		return sdt.format(cal.getTime()); //getTime 메소드로 현재 시간을 가져와서 sdt형식으로 변환
	}
	
	// R	
	public List<Gongji> selectAll() {  /*Gongji테이블에 있는 데이터를 모두 가져오는 gongjidao의 메소드 selectAll을 호출*/
		return gongjidao.selectAll();
	}
	
	public Gongji selectOne(int id) { /*Gongji테이블에 있는 데이터 하나를 가져오는 gongjidao의 메소드 selectOne 호출*/
		return gongjidao.selectOne(id);
	}
	
	public List<Gongji> selectPage(int startNum, int cnt) {  //DB테이블에 있는 데이터 중 startNum부터 cnt개를 가져오는  gongjidao의 메소드 selectPAge 호출	
		return gongjidao.selectPage(startNum, cnt);	 
	}
	
	public List<Gongji> searchAll(String condition, String search) {  //DB테이블에 있는 모든 데이터를 가져오는 메소드
		return gongjidao.searchAll(condition, search); // 만들어진 list 리턴, 레퍼런스 타입이라서 주소를 밖에서 받을 수 있다.
	}
	
	public List<Gongji> searchPage(String condition, String search, int startNum, int cnt) {	//검색 조건에 해당하는 결과를 리턴하는 메소드
		return gongjidao.searchPage(condition, search, startNum, cnt);
	}
		
	public int count() {   /*총 데이터 수를 세는 gongjidao의 메소드 count 호출*/
		return gongjidao.count();
	}
	
	
	//U
	public void update(Gongji gongji) { //DB 테이블에 있는 값을 수정하는  gongjidao의 메소드 update를 호출
		gongjidao.update(gongji); 
	} 
	
	//D
	public void delete(int id) {  //DB에 있는 데이터를 삭제하는  gongjidao의 메소드 delete를 호출
		gongjidao.delete(id);
	}
	
	public void drop() {   /* 테이블을 삭제하는 gongjidao의 메소드 drop을 호출*/
		gongjidao.drop();
	}
}
