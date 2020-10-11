package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Gongji;

public class GongjiDao {
	
	public Connection connect() { //DB와 연결하는 메소드
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  // mysql 드라이버 로딩, 드라이버들이 읽히기만 하면 자동 객체가 생성되고 DriverManager에 등록된다.
			con = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/koposw19", "root", "root"); // 우분투 서버내의 mysql에 koposw19 데이터베이스를 선택,유저id root, password root로 접속
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con; //con을 리턴한다.
	}
	
	
	public static void conClose(Connection con) {  //connection 해제 메소드
	      try {
	         if (con != null)
	            con.close();
	      } catch (SQLException e) {
	      }
	   }
	
	public void create() {  // DB에 테이블을 만드는 메소드
		try {
		Connection con = connect();  //connection 메소드를 호출해서 DB와 연결
		Statement stmt = con.createStatement(); // Statement 객체 생성 sql 쿼리를 생성/실행하며, 반환된 결과를 가져오게 할 작업 영역을 제공
		stmt.execute("create table gongji(" +
				"id int not null primary key auto_increment," + 
				"title varchar (70)," + 
				"date date," + 
				"content text)" + 
				"DEFAULT CHARSET=utf8;"); //gongji 테이블을 만든다. id, 제목 날짜, 내용 필드가 들어간다.
		stmt.close(); //statement 닫기
		con.close();  //connection 닫기
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	
	
	public void insert(Gongji gongji) {  //DB테이블에 데이터를 입력하는 메소드
		int result = 0;
		try {
		Connection con = connect();
		PreparedStatement psmt = con.prepareStatement("insert into gongji values(?, ?, ?, ?);");
		psmt.setInt(1, gongji.getId());
		psmt.setString(2, gongji.getTitle());
		psmt.setString(3, gongji.getDate());
		psmt.setString(4, gongji.getContent());		
		result = psmt.executeUpdate(); // 몇 개가 입력되었는지 반영함.
		
		psmt.close(); // Preparedstatement 닫기
		con.close();  // connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Gongji> selectPage(int startNum, int cnt) {  //DB테이블에 있는 데이터를 시작행부터 cnt만큼 가져오는 메소드
		List<Gongji> list = new ArrayList<Gongji>(); //Gongji 타입의 list 생성
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from gongji order by id desc limit " + startNum + ", " + cnt + ";");
			while(rs.next()) {  // while문을 실행하면서 list에 값을 추가한다.
				Gongji gongji = new Gongji(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(gongji);
			}
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // 만들어진 list 리턴, 레퍼런스 타입이라서 주소를 밖에서 받을 수 있다.
	}
	
	public List<Gongji> selectAll() {  //DB테이블에 있는 모든 데이터를 내림차순으로 가져오는 메소드
		List<Gongji> list = new ArrayList<Gongji>(); //Gongji 타입의 list 생성
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from gongji order by id desc;");
			while(rs.next()) {  // while문을 실행하면서 list에 값을 추가한다.
				Gongji gongji = new Gongji(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(gongji);
			}
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // 만들어진 list 리턴, 레퍼런스 타입이라서 주소를 밖에서 받을 수 있다.
		}
	
	public Gongji selectOne(int id) { //DB테이블에 있는 공지사항 중 번호에 해당하는 한 개를 가져오는 메소드
		Gongji gongji = null; //Gongji 객체를 생성.
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from gongji where id = " + id);
			while(rs.next()) { //gongji에 DB에서 받아온 값을 차례대로 입력한다.
				gongji = new Gongji(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}		
			
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gongji; //gongji를 리턴한다.
	}
	
	
	public List<Gongji> searchAll(String content, String search) {  //DB테이블에 DB테이블에 있는 데이터 필터링해서 가져오는 메소드
		List<Gongji> list = new ArrayList<Gongji>(); //Gongji 타입의 list 생성
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from gongji where " + content + " like '%" + search +"%' order by id desc;");
			while(rs.next()) {  // while문을 실행하면서 list에 값을 추가한다.
				Gongji gongji = new Gongji(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(gongji);
			}
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // 만들어진 list 리턴, 레퍼런스 타입이라서 주소를 밖에서 받을 수 있다.
	}
	
	public List<Gongji> searchPage(String content, String search, int startNum, int cnt) {  //DB테이블에 있는 데이터 필터링 후 페이지 처리하는 메소드
		List<Gongji> list = new ArrayList<Gongji>(); //Gongji 타입의 list 생성
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from gongji where " + content + " like '%" + search +"%' order by id desc limit " + startNum + ", " + cnt + ";");
			while(rs.next()) {  // while문을 실행하면서 list에 값을 추가한다.
				Gongji gongji = new Gongji(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(gongji);
			}
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return list; // 만들어진 list 리턴, 레퍼런스 타입이라서 주소를 밖에서 받을 수 있다.
	}
	
	public int count() { //gongji 테이블에 데이터 개수를 세는 메소드
		int result = 0; //정수형 변수 result 생성
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) from gongji;");
			while(rs.next()) { //result에 쿼리결과를 저장한다.
				result = rs.getInt(1);
			}
			
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return result; //result를 리턴
	}
			
	public Gongji update(Gongji gongji) { //DB 테이블에 있는 값을 수정하는 메소드
		int result = 0;
		try {
			Connection con = connect();
			// 해당 번호 가진 공지를 수정한다. Gongji 객체에서 값을 가져와서 그 값으로 업데이트 실행
			PreparedStatement pstmt = con.prepareStatement("update gongji set title = ?, content = ? where id = ?;");
			pstmt.setString(1, gongji.getTitle());
			pstmt.setString(2, gongji.getContent());
			pstmt.setInt(3, gongji.getId());
			result = pstmt.executeUpdate(); // 몇 개가 입력되었는지 반영함.
			
			pstmt.close(); // Preparedstatement 닫기
			con.close(); // connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return gongji; //student 리턴
	} 
	

	public void delete(int id) {  //DB에 있는 데이터 중 해당 번호의 데이터를 삭제하는 메소드
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.execute("DELETE from gongji where id = " + id);
			
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void drop() {  //DB에 있는 테이블을 삭제하는 메소드
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.execute("DROP TABLE gongji;");
			
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
