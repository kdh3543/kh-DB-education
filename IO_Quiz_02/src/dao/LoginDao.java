package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.LoginDto;

public class LoginDao {
	
	private BasicDataSource bds = new BasicDataSource();
	
	private static LoginDao instance = null;
	
	public static LoginDao getInstance() {
		if(instance == null) {
			instance = new LoginDao();
		}
		return instance;
	}
	
	private LoginDao() {
		bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("practice");
		bds.setPassword("practice");
		bds.setInitialSize(30);
	}
	
	public Connection getConnection() throws Exception{				
		return bds.getConnection();
	}

	public int insert(LoginDto dto) throws Exception{
		String sql = "insert into members values(?, ?, ?, default)";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());

			int result = pstat.executeUpdate();
			return result;
		}
	}

	public boolean isIdExist(String id) throws Exception {
		String sql = "select * from members where id = ?";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}

	}

	public boolean select(String id, String pw) throws Exception{
		String sql = "select * from members where id = ? and pw = ?";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);

			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}

}
