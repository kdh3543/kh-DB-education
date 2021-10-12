package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MenuDTO;

public class CafeMenuDAO {

	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	
	public int insert(String name, int price) throws Exception{
		Connection con = getConnection();
		
		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "insert into cafe_menu values(cafe_menu_seq.nextval," +
		 * "'"+name+"',"+price+")";
		 * int result = stat.executeUpdate(sql);
		 */
		
		String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,?,?)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, name); // 1번 물음표에 name 값을 할당
		pstat.setInt(2, price); // 2번 물음표에 price 값을 할당
		
		int result = pstat.executeUpdate();
		
		con.close();
		return result;
	}
	
	public int update(MenuDTO dto) throws Exception {
		Connection con = getConnection();
		/*
		 * Statement stat = con.createStatement();
		 * 
		 * String sql = "update cafe_menu set price = "+dto.getPrice()+", " +
		 * "name = '"+dto.getName()+"' where id = '"+dto.getId()+"'"; 
		 * int result = stat.executeUpdate(sql);
		 * 
		 */
		
		String sql = "update cafe_menu set price = ?, name = ? where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, dto.getPrice());
		pstat.setString(2, dto.getName());
		pstat.setInt(3, dto.getId());
		int result = pstat.executeUpdate();
		
		con.close();
		return result;
	}
	
	public int delete(int id) throws Exception {
		Connection con = getConnection();
		/*
		 * Statement stat = con.createStatement();
		 * 
		 * String sql = "delete from cafe_menu where id = '"+id+"'"; int result =
		 * stat.executeUpdate(sql);
		 */

		String sql = "delete from cafe_menu where id = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(0, id);
		int result = pstat.executeUpdate();
		
		con.close();
		return result;
	}
	
	public ArrayList<MenuDTO> selectAll() throws Exception{
		Connection con = getConnection(); 
		//Statement stat = con.createStatement();
		
		String sql = "select * from cafe_menu order by 1";
		
		PreparedStatement pstat = con.prepareStatement(sql);
		
		ResultSet rs = pstat.executeQuery();

		ArrayList<MenuDTO> result = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt("id"); 
			String name = rs.getString("name"); 
			int price = rs.getInt("price"); 
			
			MenuDTO m = new MenuDTO(id,name,price);
			result.add(m);
		}
		
		con.close();
		return result;
	}
}
