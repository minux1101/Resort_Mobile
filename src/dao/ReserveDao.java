package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import domain.Reserve;

public class ReserveDao {
   public Connection connect() { //DB�� �뿰寃고븯�뒗 硫붿냼�뱶
      Connection con = null;
      try {
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
      Statement stmt = con.createStatement(); // Statement 媛앹껜 �깮�꽦 sql 荑쇰━瑜� �깮�꽦/�떎�뻾�븯硫�, 諛섑솚�맂 寃곌낵瑜� 媛��졇�삤寃� �븷 �옉�뾽 �쁺�뿭�쓣 �젣怨�
      stmt.execute(
            "create table resort ( "+
            "name varchar(20), "+   //�꽦紐�   
            "resv_date date not null, "+  //�삁�빟�씪
            "room int not null, "+ //�삁�빟諛� 1:VIP猷� 2:�씪諛섎８ 3:�빀由ъ쟻�씤猷�
            "addr varchar(100), "+  //二쇱냼
            "telnum varchar(20), "+ //�뿰�씫泥�
            "in_name  varchar(20), "+ //�엯湲덉옄紐�
            "comment  text, "+ //�궓湲곗떎留�
            "write_date date, "+// �삁�빟�븳(�씠 湲��쓣 �벖) �궇吏�
            "processing int, "+//�쁽�옱 吏꾪뻾 1:�삁�빟�셿猷� 2: �엯湲덉셿猷�(�삁�빟�솗�젙) 3: �솚遺덉슂泥� 4:...
            "primary key (resv_date,room) )"+  // �삁�빟�씪怨� 猷몄쓣 �빀爾먯꽌 DB�쓽 �궎濡� �궗�슜
            "DEFAULT CHARSET=utf8"); 

      stmt.close(); //statement �떕湲�
      con.close();  //connection �떕湲�
      } catch (SQLException e) {
         e.printStackTrace();
      }            
   }   
   
   public void insert(Reserve reserve) {  //DB�뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �엯�젰�븯�뒗 硫붿냼�뱶
      int result = 0;
      try {
      Connection con = connect();
      PreparedStatement psmt = con.prepareStatement("insert into resort(name, resv_date, room, addr, telnum, in_name, comment, write_date, processing) values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
      
      psmt.setString(1, reserve.getName());
      psmt.setString(2, reserve.getResv_date());
      psmt.setInt(3, reserve.getRoom());
      psmt.setString(4, reserve.getAddr());
      psmt.setString(5, reserve.getTelnum());
      psmt.setString(6, reserve.getIn_name());
      psmt.setString(7, reserve.getComment());
      psmt.setString(8, reserve.getWrite_date());
      psmt.setInt(9, reserve.getProcessing());
      
      result = psmt.executeUpdate(); // 紐� 媛쒓� �엯�젰�릺�뿀�뒗吏� 諛섏쁺�븿.
      
      psmt.close(); // Preparedstatement �떕湲�
      con.close();  // connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public List<Reserve> selectAll() {  //DB�뀒�씠釉붿뿉 �엳�뒗 紐⑤뱺 �뜲�씠�꽣瑜� �궡由쇱감�닚�쑝濡� 媛��졇�삤�뒗 硫붿냼�뱶
      List<Reserve> list = new ArrayList<Reserve>(); //Reserve ���엯�쓽 list �깮�꽦
      try {
         Connection con = connect();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select * from resort where resv_date >= curdate();");
         
         while(rs.next()) {  // while臾몄쓣 �떎�뻾�븯硫댁꽌 list�뿉 媛믪쓣 異붽��븳�떎.
            Reserve reserve = new Reserve(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            list.add(reserve);
         }
         rs.close();  //result �떕湲�
         stmt.close(); //statement �떕湲�
         con.close();  //connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }      
      
      return list; // 留뚮뱾�뼱吏� list 由ы꽩, �젅�띁�윴�뒪 ���엯�씠�씪�꽌 二쇱냼瑜� 諛뽰뿉�꽌 諛쏆쓣 �닔 �엳�떎.
      }
   
   public List<Reserve> selectToday(LocalDate currentDate) {  //DB�뀒�씠釉붿뿉 �엳�뒗 紐⑤뱺 �뜲�씠�꽣瑜� �궡由쇱감�닚�쑝濡� 媛��졇�삤�뒗 硫붿냼�뱶
      List<Reserve> list = new ArrayList<Reserve>(); //Reserve ���엯�쓽 list �깮�꽦
      try {
         Connection con = connect();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select * from resort where resv_date >= curdate();");
         
         while(rs.next()) {  // while臾몄쓣 �떎�뻾�븯硫댁꽌 list�뿉 媛믪쓣 異붽��븳�떎.
            Reserve reserve = new Reserve(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            list.add(reserve);
         }
         rs.close();  //result �떕湲�
         stmt.close(); //statement �떕湲�
         con.close();  //connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }      
      
      return list; // 留뚮뱾�뼱吏� list 由ы꽩, �젅�띁�윴�뒪 ���엯�씠�씪�꽌 二쇱냼瑜� 諛뽰뿉�꽌 諛쏆쓣 �닔 �엳�떎.
      }
   
   public Reserve selectOne(String resv_date, int room) { //DB�뀒�씠釉붿뿉 �엳�뒗 怨듭��궗�빆 以� 踰덊샇�뿉 �빐�떦�븯�뒗 �븳 媛쒕�� 媛��졇�삤�뒗 硫붿냼�뱶
      Reserve reserve = new Reserve(); //Reserve 媛앹껜瑜� �깮�꽦.
      try {
         Connection con = connect();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select * from resort where resv_date = '" + resv_date + "' and room = " + room);
         
         while(rs.next()) { //reserve�뿉 DB�뿉�꽌 諛쏆븘�삩 媛믪쓣 李⑤���濡� �엯�젰�븳�떎.
            reserve.setId(rs.getInt(1));
            reserve.setName(rs.getString(2));
            reserve.setResv_date(rs.getString(3));
            reserve.setRoom(rs.getInt(4));
            reserve.setAddr(rs.getString(5));
            reserve.setTelnum(rs.getString(6));
            reserve.setIn_name(rs.getString(7));
            reserve.setComment(rs.getString(8));
         }      
         
         rs.close();  //result �떕湲�
         stmt.close(); //statement �떕湲�
         con.close();  //connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }
      
      return reserve; //gongji瑜� 由ы꽩�븳�떎.
   }
   
   public Reserve selectId(int id) { //DB�뀒�씠釉붿뿉 �엳�뒗 怨듭��궗�빆 以� 踰덊샇�뿉 �빐�떦�븯�뒗 �븳 媛쒕�� 媛��졇�삤�뒗 硫붿냼�뱶
      Reserve reserve = new Reserve(); //Reserve 媛앹껜瑜� �깮�꽦.
      try {
         Connection con = connect();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select * from resort where id = " + id);
         
         while(rs.next()) { //reserve�뿉 DB�뿉�꽌 諛쏆븘�삩 媛믪쓣 李⑤���濡� �엯�젰�븳�떎.
            reserve.setId(rs.getInt(1));
            reserve.setName(rs.getString(2));
            reserve.setResv_date(rs.getString(3));
            reserve.setRoom(rs.getInt(4));
            reserve.setAddr(rs.getString(5));
            reserve.setTelnum(rs.getString(6));
            reserve.setIn_name(rs.getString(7));
            reserve.setComment(rs.getString(8));
         }      
         
         rs.close();  //result �떕湲�
         stmt.close(); //statement �떕湲�
         con.close();  //connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }
      
      return reserve; //gongji瑜� 由ы꽩�븳�떎.
   }
  
   public List<Reserve> selectReserveRoomList(int room, String resv_date) { //DB�뀒�씠釉붿뿉 �엳�뒗 怨듭��궗�빆 以� 踰덊샇�뿉 �빐�떦�븯�뒗 �븳 媛쒕�� 媛��졇�삤�뒗 硫붿냼�뱶
      Reserve reserve = new Reserve(); //Reserve 媛앹껜瑜� �깮�꽦.
      List<Reserve> rList = new ArrayList<Reserve>();
      try {
         Connection con = connect();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("select name, resv_date, room from resort where room = " + room + " and resv_date = '" + resv_date + "'");
         
         while(rs.next()) { //reserve�뿉 DB�뿉�꽌 諛쏆븘�삩 媛믪쓣 李⑤���濡� �엯�젰�븳�떎.
            reserve = new Reserve();
            reserve.setName(rs.getString(1));
            reserve.setResv_date(rs.getString(2));
            reserve.setRoom(rs.getInt(3));
            rList.add(reserve);
         }      
         
         rs.close();  //result �떕湲�
         stmt.close(); //statement �떕湲�
         con.close();  //connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }
      
      return rList; //gongji瑜� 由ы꽩�븳�떎.
   }
   
   public int count(String resv_date, int room) { //vote �뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �꽭�뒗 硫붿냼�뱶
		int result = 0; //�젙�닔�삎 蹂��닔 result �깮�꽦
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) from resort where resv_date = '" + resv_date + "' and room = " + room +";");
			while(rs.next()) { //result�뿉 荑쇰━寃곌낵瑜� ���옣�븳�떎.
				result = rs.getInt(1);
			}
			
			rs.close();  //result �떕湲�
			stmt.close(); //statement �떕湲�
			con.close();  //connection �떕湲�
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return result; //result瑜� 由ы꽩
	}
   
   public void update(Reserve reserve) {  //DB�뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �엯�젰�븯�뒗 硫붿냼�뱶
	      int result = 0;
	      try {
	      Connection con = connect();
	      PreparedStatement psmt = con.prepareStatement("update resort set name = ?, resv_date = ?, room = ?, addr = ?, telnum = ?, in_name = ?, comment = ? where id = ?;");
	      
	      psmt.setString(1, reserve.getName());
	      psmt.setString(2, reserve.getResv_date());
	      psmt.setInt(3, reserve.getRoom());
	      psmt.setString(4, reserve.getAddr());
	      psmt.setString(5, reserve.getTelnum());
	      psmt.setString(6, reserve.getIn_name());
	      psmt.setString(7, reserve.getComment());
	      psmt.setInt(8, reserve.getId());
	      
	      result = psmt.executeUpdate(); // 紐� 媛쒓� �엯�젰�릺�뿀�뒗吏� 諛섏쁺�븿.
	      
	      psmt.close(); // Preparedstatement �떕湲�
	      con.close();  // connection �떕湲�
	      }catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }
   
   public void delete(String resv_date, int room) { //DB�뀒�씠釉붿뿉 �엳�뒗 怨듭��궗�빆 以� 踰덊샇�뿉 �빐�떦�븯�뒗 �븳 媛쒕�� 媛��졇�삤�뒗 硫붿냼�뱶
	      try {
	         Connection con = connect();
	         PreparedStatement psmt = con.prepareStatement("delete from resort where resv_date = ? and room = ?;");
	         psmt.setString(1, resv_date);
		     psmt.setInt(2, room);
		     int result = psmt.executeUpdate();
		     
		     psmt.close();
	         con.close(); 
	      }catch (SQLException e) {
	         e.printStackTrace();
	      }
	     
	   }
   

}