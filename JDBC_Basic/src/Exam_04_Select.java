import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exam_04_Select {
	public static void main(String[] args) {
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		Connection con = DriverManager.getConnection(url, username, password);
		
		Statement stat = con.createStatement();
		
		String sql = "select * from cafe_menu order by 1";
		ResultSet rs = stat.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id"); // rs.getInt(1); 
			//--> 컬럼을 직접적으로 명시해도 되고 컬럼 넘버로 명시해도 됨
			String name = rs.getString("name"); // rs.getString(2);
			int price = rs.getInt("price"); // rs.getInt(3);
			System.out.println(id + " : " + name + " : " + price); 
		}
		
		con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
