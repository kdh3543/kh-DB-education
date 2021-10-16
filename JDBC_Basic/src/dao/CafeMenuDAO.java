
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "insert into cafe_menu values(cafe_menu_seq.nextval," +
		 * "'"+name+"',"+price+")";
		 * int result = stat.executeUpdate(sql);
		 */

		String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,?,?,default)";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setString(1, name); // 1번 물음표에 name 값을 할당
			pstat.setInt(2, price); // 2번 물음표에 price 값을 할당

			int result = pstat.executeUpdate();
			return result;
		}

	}

	public int update(MenuDTO dto) throws Exception {

		/*
		 * Statement stat = con.createStatement();
		 * 
		 * String sql = "update cafe_menu set price = "+dto.getPrice()+", " +
		 * "name = '"+dto.getName()+"' where id = '"+dto.getId()+"'"; int result =
		 * stat.executeUpdate(sql);
		 * 
		 */

		String sql = "update cafe_menu set name = ?, price = ?, reg_date = ? where id = ?";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getName());
			pstat.setInt(2, dto.getPrice());
			pstat.setDate(3, dto.getReg_date());
			pstat.setInt(4, dto.getId());
			int result = pstat.executeUpdate();

			return result;
		}
	}

	public int delete(int id) throws Exception {

		/*
		 * Statement stat = con.createStatement();
		 * 
		 * String sql = "delete from cafe_menu where id = '"+id+"'"; int result =
		 * stat.executeUpdate(sql);
		 */

		String sql = "delete from cafe_menu where id = ?";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, id);
			int result = pstat.executeUpdate();

			return result;
		}
	}

	public ArrayList<MenuDTO> selectAll() throws Exception{

		//Statement stat = con.createStatement();

		String sql = "select * from cafe_menu order by 1";
		try(Connection con = getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			ArrayList<MenuDTO> result = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id"); 
				String name = rs.getString("name"); 
				int price = rs.getInt("price"); 
				Date reg_date = rs.getDate("reg_date");

				MenuDTO m = new MenuDTO(id,name,price,reg_date);
				result.add(m);
			}

			return result;
		}
	}
}
