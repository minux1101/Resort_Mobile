package dao;

import java.sql.*;
import java.util.*;

import domain.Board;
import domain.File;

public class FileDao {

	public Connection connect() {
	Connection con = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 드라이버 로딩, 드라이버들이 읽히기만 하면 자동 객체가 생성되고 DriverManager에 등록된다.
		con = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/koposw19", "root", "root"); // 우분투 서버내의 mysql에 koposw19 데이터베이스를 선택,유저id root, password root로 접속 
	} catch(Exception e) {
		e.printStackTrace();
	}
	return con;	
	}
	
	public static void conClose(Connection con) {  //connection 해제 메소드
	      try {
	         if (con != null)
	            con.close();
	      } catch (SQLException e) {
	      }
	   }
	
	public void create() { // DB에 테이블을 만드는 메소드
		try {
			Connection con = connect(); //connection 메소드를 호출해서 DB와 연결
			Statement stmt = con.createStatement(); // Statement 객체 생성 sql 쿼리를 생성/실행하며, 반환된 결과를 가져오게 할 작업 영역을 제공
			stmt.execute("create table file(" +
						 "id int not null auto_increment primary key," +
						"fileName varchar(200)," +
						"fileRealName varchar(200)," +
						"rootid int not null," +
						"FOREIGN KEY(rootid) REFERENCES board(id)" + 
						"on update cascade " + 
						"on delete cascade" +
						") DEFAULT CHARSET=utf8;"); //file테이블을 만든다. 번호, 사용자가 입력한 파일 이름과 파일의 실제 이름, 공지 번호가 필요하다.
			
			stmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int upload(String fileName, String fileRealName, int rootid) { //file을 insert하는 메소드
		int result = 0; 
		try {
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement("insert into file(fileName, fileRealName, rootid) values(?, ?, ?)");
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			pstmt.setInt(3,  rootid);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public File selectOne(int rootid) { //DB테이블에 있는 file 데이터 중 번호에 해당하는 한 개를 가져오는 메소드
		File file = null; //file 객체를 생성.
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from file where rootid = " + rootid);
			while(rs.next()) { //file에 DB에서 받아온 값을 차례대로 입력한다.
				file = new File(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}		
			
			rs.close();  //result 닫기
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return file; //file을 리턴한다.
	}
	
	public void delete(int id) {  //DB에 있는 데이터를 삭제하는 메소드
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.execute("DELETE from file where id = " + id);
			
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
			stmt.execute("DROP TABLE file;");
			
			stmt.close(); //statement 닫기
			con.close();  //connection 닫기
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}