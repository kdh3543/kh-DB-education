package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.NetflixDto;

public class NetflixDao {
	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "practice";
		String pw = "practice";
		
		return DriverManager.getConnection(url, id, pw);
	}
	
	public int insert(NetflixDto dto) throws Exception{
		String sql = "insert into movies values(movies_seq.nextval, ?, ?, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getDescription());
			pstat.setDate(3, dto.getRel_date());
			
			return pstat.executeUpdate();
		}
	}
	
	public ArrayList<NetflixDto> selectAll() throws Exception{
		String sql = "select * from movies";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			
			ArrayList<NetflixDto> result = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Date rel_date = rs.getDate("rel_date");
				
				NetflixDto dto = new NetflixDto(id,title,description,rel_date);
				
				result.add(dto);
				
				
			}
			return result;
		}
	}
	
	public int delete(int id) throws Exception{
		String sql = "delete from movies where id = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			
			return pstat.executeUpdate();
		}
		
	}
	
	public int update(NetflixDto dto) throws Exception{
		String sql = "update movies set title = ?, description = ?, rel_date = ? where id =?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getDescription());
			pstat.setDate(3, dto.getRel_date());
			pstat.setInt(4, dto.getId());
			
			return pstat.executeUpdate();
		}
	}
	
	public NetflixDto selectById(int pid) throws Exception{
		String sql = "select * from movies where id = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, pid);
			
			try(ResultSet rs = pstat.executeQuery();){
				
				NetflixDto dto = null;
				if(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					Date rel_date = rs.getDate("rel_date");
					
					dto = new NetflixDto(id,title,description,rel_date);
				}
				return dto;
			}
		}
	}
	
	
}
