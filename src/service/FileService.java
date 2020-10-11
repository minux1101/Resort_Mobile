package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.FileDao;
import domain.File;

public class FileService {
	
	FileDao filedao = new FileDao();
	
	public void create() { // DB에 테이블을 만드는 메소드
		filedao.create();
	}
	
	public void upload(String fileName, String fileRealName, int rootid) { //DB에 파일 데이터를 하나 입력하는 메소드
		filedao.upload(fileName, fileRealName, rootid);
	}
	
	public File selectOne(int rootid) {   //DB테이블에 있는 파일데이터들 중 번호에 해당하는 한 개를 가져오는 메소드
		return filedao.selectOne(rootid);  
	}
	
	public void delete(int id) {  //DB에 있는 데이터를 삭제하는 메소드
		filedao.delete(id);
	}
	
	public void drop() {  //DB에 있는 테이블을 삭제하는 메소드
		filedao.drop();
	}
	
}
