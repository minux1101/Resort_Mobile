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

import domain.Board;
import domain.Reserve;
import domain.RoomCount;

public class RoomCountDao {
   // db connect
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
	   
   // db close
   public void DBClose(Connection conn) throws SQLException { 
      if(conn != null) {
         conn.close();
      }
   }
   //  督    table create
   public void create() throws SQLException, ClassNotFoundException {
      Statement stmt;
      Connection conn = null;
      conn = connect();
      String query = "CREATE TABLE RoomCount(" 
                               + " rName char(10)," 
                               + " num int," 
                               + " date date)"
                             +"DEFAULT CHARSET=utf8";
      stmt = conn.createStatement(); 
      stmt.execute(query); 
      stmt.close(); 
      conn.close();
   }
   //          督    select
   public RoomCount selectOne(String rName, String date) throws SQLException, ClassNotFoundException {
      ResultSet rs = null;
      Statement stmt = null;
      RoomCount rc = new RoomCount();
      Connection conn = null;
      conn = connect();
      stmt = conn.createStatement();
      String query = "select * from RoomCount where date = '" + date + "' and rName = '" + rName + "';";
      if(stmt.execute(query)) {
         rs = stmt.getResultSet();
      }
      while (rs.next()) {
         rc.setrName(rs.getString(1));
         rc.setNum(rs.getInt(2));
         rc.setDate(rs.getString(3));
      }
      stmt.close();
      rs.close();
      conn.close();
      return rc;
   }
   public List<RoomCount> selectAll(String name) throws SQLException, ClassNotFoundException {
      ResultSet rs = null;
      Statement stmt = null;
      Connection conn = null;
      conn = connect();
      List<RoomCount> rList = new ArrayList<RoomCount>();
      stmt = conn.createStatement();
      String query = "select * from RoomCount where rName = '" + name +"' and date >= curdate()";
      if(stmt.execute(query)) {
         rs = stmt.getResultSet();
      }
      while (rs.next()) {
         RoomCount rc = new RoomCount();
         rc.setrName(rs.getString(1));
         rc.setNum(rs.getInt(2));
         rc.setDate(rs.getString(3));
         rList.add(rc);
      }
      stmt.close();
      rs.close();
      conn.close();
      return rList;
   }
   
   //  督    insert
   public void insert(RoomCount rc) throws Exception {
           PreparedStatement pstmt = null;
           Connection conn = null;
         conn = connect();
           // primary key     치    歌  insert ignore into    韜 
           pstmt = conn.prepareStatement("insert into RoomCount(rName, num, date) values(?,?,?)");  
           pstmt.setString(1, rc.getrName()); 
           pstmt.setInt(2, rc.getNum()); 
           pstmt.setString(3, rc.getDate()); 
           pstmt.executeUpdate(); 
           pstmt.close();
           conn.close();
    } 
   
   public void update(RoomCount rc) throws Exception {
       PreparedStatement pstmt = null;
       Connection conn = null;
       conn = connect();
       // primary key     치    歌  insert ignore into    韜 
       pstmt = conn.prepareStatement("update RoomCount set num = ?, date = ? where rName = ? and date = ?;");  
       pstmt.setInt(1, rc.getNum()); 
       pstmt.setString(2, rc.getDate()); 
       pstmt.setString(3, rc.getrName()); 
       pstmt.setString(4, rc.getDate()); 
       
       pstmt.executeUpdate(); 
       
       pstmt.close();
       conn.close();
   } 
   
   //  督    update
   public void updateMinus(RoomCount rc) throws SQLException, ClassNotFoundException {
      Connection conn = null;
      conn = connect();
      Statement stmt = null;
      stmt = conn.createStatement();
      String query = "update RoomCount set num= num-1 where rName = '" + rc.getrName() + "' and date = '" + rc.getDate() + "'";
      stmt.executeUpdate(query);
      stmt.close();
      conn.close();
   }
   
   //  督    update
   public void updatePlus(RoomCount rc) throws SQLException, ClassNotFoundException {
      Connection conn = null;
      conn = connect();
      Statement stmt = null;
      stmt = conn.createStatement();
      String query = "update RoomCount set num= num+1 where rName = '" + rc.getrName() + "' and date = '" + rc.getDate() + "'";
      stmt.executeUpdate(query);
      stmt.close();
      conn.close();
   }
   
   
   public void delete() throws ClassNotFoundException {  // �쁽�옱�궇吏� 湲곗� �쟾�궇 �뜲�씠�꽣 �궘�젣
      try {
         Connection con = connect();
         Statement stmt = con.createStatement();
         stmt.executeQuery("delete from RoomCount where date < curdate();");
      
         stmt.close(); //statement �떕湲�
         con.close();  //connection �떕湲�
      }catch (SQLException e) {
         e.printStackTrace();
      }      
   }
}