package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import classes.Menu;

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
		
		Statement stat = con.createStatement();
		String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,"
				+ "'"+name+"',"+price+")";
		int result = stat.executeUpdate(sql);
		con.close();
		return result;
	}
	
	public int update(String name, int price, int id) throws Exception {
		Connection con = getConnection();
		Statement stat = con.createStatement();
		
		String sql = "update cafe_menu set price = "+price+", "
				+ "name = '"+name+"' where id = '"+id+"'";
		int result = stat.executeUpdate(sql);

		con.close();
		return result;
	}
	
	public int delete(int id) throws Exception {
		Connection con = getConnection();
		Statement stat = con.createStatement();
		
		String sql = "delete from cafe_menu where id = '"+id+"'";
		int result = stat.executeUpdate(sql);

		con.close();
		return result;
	}
	
	public ArrayList<Menu> selectAll() throws Exception{
		Connection con = getConnection(); 
		Statement stat = con.createStatement();
		
		String sql = "select * from cafe_menu order by 1";
		ResultSet rs = stat.executeQuery(sql);

		ArrayList<Menu> result = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt("id"); 
			String name = rs.getString("name"); 
			int price = rs.getInt("price"); 
			
			Menu m = new Menu(id,name,price);
			result.add(m);
		}
		
		con.close();
		return result;
	}
}
