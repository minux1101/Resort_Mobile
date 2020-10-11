package domain;

import java.time.format.DateTimeFormatter;

public class RoomCount {
   private String rName;
   private int num;
   private String date;
   
   public String getrName() {
      return rName;
   }
   public void setrName(String rName) {
      this.rName = rName;
   }
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   public String getDate() {
      return date;
   }
   public void setDate(String date) {
      this.date = date;
   }
}