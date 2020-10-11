package domain;

import java.util.Date;

public class Reserve {
	private int id;
	private String name;  //�꽦紐�
	private String resv_date;  //�삁�빟�씪
	private int room;  //�삁�빟諛� 1:VIP猷� 2:�씪諛섎８ 3:�빀由ъ쟻�씤猷�
	private String addr; //二쇱냼
	private String telnum; //�뿰�씫泥�
	private String in_name;  //�엯湲덉옄紐�
	private String comment;  //�궓湲곗떎 留�
	private String write_date; // �삁�빟�븳(�씠 湲��쓣 �벖) �궇吏�
	private int processing;  //�쁽�옱 吏꾪뻾 1:�삁�빟�셿猷� 2: �엯湲덉셿猷�(�삁�빟�솗�젙) 3: �솚遺덉슂泥� 4:...
		
	public Reserve() {
		super();
	}
	
	public Reserve(int id, String name, String resv_date, int room, String addr, String telnum, String in_name,
			String comment, String write_date, int processing) {
		super();
		this.id = id;
		this.name = name;
		this.resv_date = resv_date;
		this.room = room;
		this.addr = addr;
		this.telnum = telnum;
		this.in_name = in_name;
		this.comment = comment;
		this.write_date = write_date;
		this.processing = processing;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResv_date() {
		return resv_date;
	}
	public void setResv_date(String resv_date) {
		this.resv_date = resv_date;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getIn_name() {
		return in_name;
	}
	public void setIn_name(String in_name) {
		this.in_name = in_name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public int getProcessing() {
		return processing;
	}
	public void setProcessing(int processing) {
		this.processing = processing;
	}

	@Override
	public String toString() {
		return "Reserve [name=" + name + ", resv_date=" + resv_date + ", room=" + room + ", addr=" + addr + ", telnum="
				+ telnum + ", in_name=" + in_name + ", comment=" + comment + ", write_date=" + write_date
				+ ", processing=" + processing + "]";
	}
	
}
