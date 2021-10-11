import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Exam_03_Update {
	public static void main(String[] args) {
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";
		Connection con = DriverManager.getConnection(url,username,password);
		
		Statement stat = con.createStatement();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("변경할 가격을 말씀해주세요: ");
		int price = Integer.parseInt(sc.nextLine());
		System.out.print("변경하실 메뉴를 말씀해주세요: ");
		String name = sc.nextLine();
		
		String sql = "update cafe_menu set price = "+price+" where name = '"+name+"'";
		int result = stat.executeUpdate(sql);
		
		if(result>0) {
			System.out.println("변경 성공");
		}else {
			System.out.println("변경 실패");
		}
		
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
