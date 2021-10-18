package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.EncryptUtils;
import dto.LoginDto;

public class LoginDao {
	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "practice";
		String pw = "practice";
		Connection con = DriverManager.getConnection(url,id,pw);
		return con;
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
