package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.LoginDao;
import domain.Login;


public class LoginService {
	LoginDao logindao = new LoginDao();
	
	public Connection connect() { //
		return logindao.connect(); //
	}
	
	public void create() {  // 
		logindao.create();
	}
		   
	public void insert(Login login) {  //
		logindao.insert(login);
	}
	
	public Login selectOne(String id) {
		return logindao.selectOne(id);
	}
	
	public void update(Login login) {  
		logindao.update(login);
	}
}
