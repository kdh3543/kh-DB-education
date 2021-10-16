package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.MovieDTO;

public class MovieDAO {
	
	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "practice";
		String password = "practice";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
		
	}
	
	public int insert(String title, String desc, Date rel_date) throws Exception{
		String sql = "insert into movies values(movies_seq.nextval,?,?,?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, title);
			pstat.setString(2, desc);
			pstat.setDate(3, rel_date);
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int delete(int id) throws Exception{
		String sql = "delete from movies where id = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int update(MovieDTO dto) throws Exception{
		String sql = "update movies set title = ?, description = ?, rel_date = ? where id = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getDescription());
			pstat.setDate(3, dto.getRel_date());
			pstat.setInt(4, dto.getId());
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public ArrayList<MovieDTO> selectAll() throws Exception{
		String sql = "select * from movies order by 1";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			
			ArrayList<MovieDTO> result = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				Date rel_date = rs.getDate("rel_date");
				
				MovieDTO dto = new MovieDTO(title,desc,rel_date,id);
				
				result.add(dto);
			}
			
			return result;
		}
		
	}
	
	public MovieDTO selectId(int seq) throws Exception{
		String sql = "select * from movies where id = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try (ResultSet rs = pstat.executeQuery();){
				MovieDTO dto = null;
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String desc = rs.getString("description");
					Date rel_date = rs.getDate("rel_date");
					
					dto = new MovieDTO(title,desc,rel_date,id);
				}
				
				return dto;
			}
		}
	}
	
	public ArrayList<MovieDTO> selectTitle(String input) throws Exception{
		String sql = "select * from movies where title = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, input);
			try (ResultSet rs = pstat.executeQuery();){
				
				ArrayList<MovieDTO> result = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String desc = rs.getString("description");
					Date rel_date = rs.getDate("rel_date");
					
					MovieDTO dto = new MovieDTO(title,desc,rel_date,id);
					result.add(dto);
				}
				
				return result;
			}
		}
	}
}
