import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exam_02_Delete {
	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("OJDBC ����̹��� �߰����� ����");
			System.exit(0);
		}

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "kh";
		String password = "kh";

		try {
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stat = con.createStatement();
			String sql ="delete from cafe_menu where price = 2500";
			int result = stat.executeUpdate(sql);

			System.out.println("���: " + result);

			if(result>0) {
				System.out.println("���������� �����Ǿ����ϴ�.");
			}else {
				System.out.println("������ �����Ͽ����ϴ�.");
			}
			con.close();
		}catch(Exception e) {
			System.out.println("���ӽ���");
			System.exit(0);
		}
	}
}