package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.GuestDto;

public class GuestDao {
	private BasicDataSource bds = new BasicDataSource();
	
	private static GuestDao instance = null;
	
	public static GuestDao getInstance() {
		if(instance == null) {
			instance = new GuestDao();
		}
		return instance;
	}
	
	private GuestDao() {
		bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("practice");
		bds.setPassword("practice");
		bds.setInitialSize(30);
	}
	
	private Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	
	public int insert(GuestDto dto) throws Exception{
		String sql = "insert into GuestBook values(GuestBook_seq.nextval, ?, ?, sysdate)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			
			return pstat.executeUpdate();
		}
	}
	
	public ArrayList<GuestDto> selectAll() throws Exception{
		String sql = "select * from GuestBook order by 1";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			
			ArrayList<GuestDto> result = new ArrayList<>();
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String writer = rs.getString("writer");
				String message = rs.getString("message");
				Date write_date = rs.getDate("write_date");
				
				GuestDto dto = new GuestDto(seq,writer,message,write_date);
				result.add(dto);

			}
			return result;
		}
	}
	
	public int delete(int seq) throws Exception{
		String sql = "delete from GuestBook where seq = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			
			return pstat.executeUpdate();
		}
	}
	
	public GuestDto selectBySeq(int pseq) throws Exception{
		String sql = "select * from GuestBook where seq = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, pseq);
			try(ResultSet rs = pstat.executeQuery();){
				GuestDto dto = null;
				if(rs.next()) {
					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String message = rs.getString("message");
					Date write_date = rs.getDate("write_date");
					
					dto = new GuestDto(seq,writer,message,write_date);
				}
				
				return dto;
			}
		}
		
	}
}
