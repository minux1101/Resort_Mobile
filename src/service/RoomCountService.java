package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.RoomCountDao;
import domain.Reserve;
import domain.RoomCount;

public class RoomCountService {
   RoomCountDao rcd = new RoomCountDao();
   
   ReserveService rs = new ReserveService();
   
   // table create
   public void create() throws SQLException, ClassNotFoundException {
      rcd.create();
   }
   // select
   public RoomCount selectOne(String rName, String date) throws SQLException, ClassNotFoundException {
      return rcd.selectOne(rName, date);
   }
   // select
      public List<RoomCount> selectAll(String name) throws SQLException, ClassNotFoundException {
         return rcd.selectAll(name);
      }
   // insert
   public void insert(RoomCount rc) throws Exception {
        rcd.insert(rc);
    } 
   
   public void update(RoomCount rc) throws Exception {
	   rcd.update(rc);
   } 
   
   // update
   public void updateMinus(RoomCount rc) throws SQLException, ClassNotFoundException {
      rcd.updateMinus(rc);
   }
   // update
   public void updatePlus(RoomCount rc) throws SQLException, ClassNotFoundException {
      rcd.updatePlus(rc);
   }
   
   public void makeData() throws Exception {
      LocalDate currentDate = LocalDate.now();
      RoomCount rc1 = new RoomCount();
      RoomCount rc2 = new RoomCount();
      RoomCount rc3 = new RoomCount();
      rc1.setNum(1);
      rc1.setrName("sweet");
      rc1.setDate(currentDate.plusDays(30).toString());
      rc2.setNum(1);
      rc2.setrName("family");
      rc2.setDate(currentDate.plusDays(30).toString());
      rc3.setNum(1);
      rc3.setrName("delux");
      rc3.setDate(currentDate.plusDays(30).toString());
      rcd.insert(rc1);
      rcd.insert(rc2);
      rcd.insert(rc3);   
   }
   
   public void makeData1() throws Exception {
      LocalDate currentDate = LocalDate.now();
      for(int i=0; i < 30; i++) {
         RoomCount rc1 = new RoomCount();
         RoomCount rc2 = new RoomCount();
         RoomCount rc3 = new RoomCount();
         
         rc1.setNum(1 - rs.count(currentDate.plusDays(i).toString(), 1));
         rc1.setrName("sweet");
         rc1.setDate(currentDate.plusDays(i).toString());
         rc2.setNum(1 - rs.count(currentDate.plusDays(i).toString(), 2));
         rc2.setrName("family");
         rc2.setDate(currentDate.plusDays(i).toString());
         rc3.setNum(1 - rs.count(currentDate.plusDays(i).toString(), 3));
         rc3.setrName("delux");
         rc3.setDate(currentDate.plusDays(i).toString());
         rcd.insert(rc1);
         rcd.insert(rc2);
         rcd.insert(rc3);
      }
   }
   
   public void updateAll() throws Exception {
	      LocalDate currentDate = LocalDate.now();
	      for(int i=0; i < 30; i++) {
	         RoomCount rc1 = new RoomCount();
	         RoomCount rc2 = new RoomCount();
	         RoomCount rc3 = new RoomCount();
	         
	         rc1.setNum(1 - rs.count(currentDate.plusDays(i).toString(), 1));
	         rc1.setrName("sweet");
	         rc1.setDate(currentDate.plusDays(i).toString());
	         rc2.setNum(1 - rs.count(currentDate.plusDays(i).toString(), 2));
	         rc2.setrName("family");
	         rc2.setDate(currentDate.plusDays(i).toString());
	         rc3.setNum(1 - rs.count(currentDate.plusDays(i).toString(), 3));
	         rc3.setrName("delux");
	         rc3.setDate(currentDate.plusDays(i).toString());
	         rcd.update(rc1);
	         rcd.update(rc2);
	         rcd.update(rc3);
	      }
	   }
   
   
   public void delete() throws ClassNotFoundException {  //DB�뀒�씠釉붿뿉 �엳�뒗 紐⑤뱺 �뜲�씠�꽣瑜� �궡由쇱감�닚�쑝濡� 媛��졇�삤�뒗 硫붿냼�뱶
      rcd.delete();
   }
}