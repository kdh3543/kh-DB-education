package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.CafeDto;

public class CafeDao {
	public Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";

		Connection con = DriverManager.getConnection(url,username,password);

		return con;

	}

	public int insert(CafeDto dto) throws Exception{

		String sql = "insert into cafe values(cafe_seq.nextval,?,?, sysdate)";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getMenu());
			pstat.setInt(2, dto.getPrice());
		
			int result = pstat.executeUpdate();

			return result;
		}
	}

	public int update(CafeDto dto) throws Exception{
		String sql = "update cafe set menu = ?, price = ? where id = ?";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getMenu());
			pstat.setInt(2, dto.getPrice());
			pstat.setInt(3, dto.getId());

			int result = pstat.executeUpdate();
			return result;
		}
	}

	public int delete(int id) throws Exception{
		String sql = "delete from cafe where id = ?";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);

			int result = pstat.executeUpdate();
			return result;
		}
	}

	public ArrayList<CafeDto> selectAll() throws Exception{
		String sql = "select * from cafe";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			ArrayList<CafeDto> result = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String menu = rs.getString("menu");
				int price = rs.getInt("price");
				Date reg_date = rs.getDate("reg_date");

				CafeDto dto = new CafeDto(id,menu,price,reg_date);
				result.add(dto);
			}

			return result;

		}
	}

	public CafeDto selectById(int pid) throws Exception{
		String sql = "select * from cafe where id=?";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, pid);
			try(ResultSet rs = pstat.executeQuery();){
				CafeDto dto = null;
				if(rs.next()) {
					int id = rs.getInt("id");
					String menu = rs.getString("menu");
					int price = rs.getInt("price");
					Date reg_date = rs.getDate("reg_date");

					dto = new CafeDto(id,menu,price,reg_date);

				}
				return dto;
			}
		}
	}

	public ArrayList<CafeDto> selectByMenu(String pmenu) throws Exception{
		String sql = "select * from Cafe where menu = ?";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, pmenu);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<CafeDto> result = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("id");
					String menu = rs.getString("menu");
					int price = rs.getInt("price");
					Date reg_date = rs.getDate("reg_date");
					
					result.add(new CafeDto(id, menu, price, reg_date));
				}

				return result;

			}
		}
	}
}

















