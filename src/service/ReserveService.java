package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.ReserveDao;
import domain.Reserve;

public class ReserveService {   
   ReserveDao reservedao = new ReserveDao(); //ReserveDao�쓽 媛앹껜 �깮�꽦
   
   public void create() {  // DB�뿉 �뀒�씠釉붿쓣 留뚮뱶�뒗 硫붿냼�뱶
      reservedao.create();
   }   
   
   public void insert(Reserve reserve) {  //DB�뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �엯�젰�븯�뒗 硫붿냼�뱶
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
      reserve.setWrite_date(dformat.format(cal.getTime()));
      reservedao.insert(reserve);
   }
   
   public List<Reserve> selectAll() {  //DB�뀒�씠釉붿뿉 �엳�뒗 紐⑤뱺 �뜲�씠�꽣瑜� 媛��졇�삤�뒗 硫붿냼�뱶
      return reservedao.selectAll(); // 
   }
   
   public Reserve selectOne(String resv_date, int room) {
      return reservedao.selectOne(resv_date, room);
   }
   
   public Reserve selectId(int id) { 
	   return reservedao.selectId(id);
   }
   
   public List<Reserve> selectReserveRoomList(int room, String resv_date) {
      return reservedao.selectReserveRoomList(room, resv_date);
   }
   
   public List<Reserve> selectToday(LocalDate currentDate) { 
      return reservedao.selectToday(currentDate);
   }
   
   public int count(String resv_date, int room) { //vote �뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �꽭�뒗 硫붿냼�뱶
		return reservedao.count(resv_date, room); //result瑜� 由ы꽩
	}
   
   public void update(Reserve reserve) {
	   Calendar cal = Calendar.getInstance();
	   SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	   reserve.setWrite_date(dformat.format(cal.getTime()));
	   reservedao.update(reserve);
   }
   
   public void delete(String resv_date, int room) {
	    reservedao.delete(resv_date, room);
	   }
}