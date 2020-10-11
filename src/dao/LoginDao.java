package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Login;
import domain.Reserve;

public class LoginDao {
	public Connection connect() { //DB�� �뿰寃고븯�뒗 硫붿냼�뱶
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  // mysql �뱶�씪�씠踰� 濡쒕뵫, �뱶�씪�씠踰꾨뱾�씠 �씫�엳湲곕쭔 �븯硫� �옄�룞 媛앹껜媛� �깮�꽦�릺怨� DriverManager�뿉 �벑濡앸맂�떎.
		    con = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/koposw19", "root", "root"); // �슦遺꾪닾 �꽌踰꾨궡�쓽 mysql�뿉 koposw19 �뜲�씠�꽣踰좎씠�뒪瑜� �꽑�깮,�쑀��id root, password root濡� �젒�냽
		         
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con; //con�쓣 由ы꽩�븳�떎.
	}
		   
	public static void conClose(Connection con) {  //connection �빐�젣 硫붿냼�뱶
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
		}
	}
		   
	public void create() {  // DB�뿉 �뀒�씠釉붿쓣 留뚮뱶�뒗 硫붿냼�뱶
		try {
			Connection con = connect();  //connection 硫붿냼�뱶瑜� �샇異쒗빐�꽌 DB�� �뿰寃�
			Statement stmt = con.createStatement(); 
			stmt.execute("CREATE TABLE adminfo(" 
                      	+ " id varchar(20) primary key not null, " 
                      	+ " pass varchar(50),"
                      	+ " time varchar(40))" 
                      	+ " DEFAULT CHARSET=utf8");
			stmt.close(); //statement �떕湲�
			con.close();  //connection �떕湲�
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
		   
	public void insert(Login login) {  //DB�뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �엯�젰�븯�뒗 硫붿냼�뱶
		int result = 0;
		try {
			Connection con = connect();
			PreparedStatement pstmt = null;
			String sql = "";
			sql = "insert into adminfo values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login.getId());
			pstmt.setString(2, login.getPass());
			pstmt.setString(3, login.getTime());
			pstmt.execute();
			pstmt.close();
		     
			pstmt.close(); // Statement �떕湲�
			con.close();  // connection �떕湲�
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		   
	public Login selectOne(String id) { //DB�뀒�씠釉붿뿉 �엳�뒗 怨듭��궗�빆 以� 踰덊샇�뿉 �빐�떦�븯�뒗 �븳 媛쒕�� 媛��졇�삤�뒗 硫붿냼�뱶
		Login login = new Login(); //Reserve 媛앹껜瑜� �깮�꽦.
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from adminfo where id = '" + id + "'");
			         
			while(rs.next()) { //reserve�뿉 DB�뿉�꽌 諛쏆븘�삩 媛믪쓣 李⑤���濡� �엯�젰�븳�떎.
				login.setId(rs.getString(1));
				login.setPass(rs.getString(2));
				login.setTime(rs.getString(3));
			}      
			         
			rs.close();  //result �떕湲�
			stmt.close(); //statement �떕湲�
			con.close();  //connection �떕湲�
		}catch (SQLException e) {
			e.printStackTrace();
		}
			      
			return login; //
		}
	
	public void update(Login login) {  
		int result = 0;
		try {
			Connection con = connect();
			PreparedStatement pstmt = null;
			String sql = "";
			sql = "update adminfo set time = ? where id = ? and pass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, login.getId());
			pstmt.setString(3, login.getPass());
			pstmt.setString(1, login.getTime());
			pstmt.executeUpdate();
			pstmt.close();
		     
			pstmt.close(); // Statement �떕湲�
			con.close();  // connection �떕湲�
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

