import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Exam_02_Delete {
	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("OJDBC 드라이버를 발견하지 못함");
			System.exit(0);
		}

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";

		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 메뉴를 말씀해 주세요: ");
		String del = sc.nextLine();
		
		
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stat = con.createStatement();
			String sql ="delete from cafe_menu where name='"+del+"'";
			
			String sql2 = "delete from cafe_menu where id = ";
			System.out.print("삭제할 ID를 입력해주세요: ");
			int id = Integer.parseInt(sc.nextLine());
			int result = stat.executeUpdate(sql2);
			
			

			System.out.println("결과: " + result);

			if(result>0) {
				System.out.println("성공적으로 삭제되었습니다.");
			}else {
				System.out.println("삭제에 실패하였습니다.");
			}
			con.close();
		}catch(Exception e) {
			System.out.println("접속실패");
			System.exit(0);
		}
	}
}
