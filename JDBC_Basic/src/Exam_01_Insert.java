import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Exam_01_Insert {
	public static void main(String[] args) {


		// 1. OJDBC ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("OJDBC ����̹��� �߰����� ����.");
			System.exit(0);
		}


		// 2. DBMS�� ����
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		// localhost ==> ���ǽ��� ip�ּҸ� ����
		// ������Ʈ �� ������ localhost�� �ƴ� ������ ip �ּҸ� �ְԵ�
		String username = "kh";
		String password = "kh";

		try {
			Connection con = DriverManager.getConnection(url, username, password);
			
			//3. Query�� ������ �� �ִ� �ν��Ͻ��� ����
			Statement stat = con.createStatement();
			//4. ������ Query�� �ۼ� �� Query ����
			String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,"
					+ "'Cafe Mocha', 2500)";
				;
			int result = stat.executeUpdate(sql);
			
			System.out.println("��� : " + result);
			
			if(result > 0) {
				System.out.println("���������� �ԷµǾ����ϴ�.");
			}else {
				System.out.println("�Է¿� �����߽��ϴ�.");
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace(); // ������ �߻��ϰ� �� ������ ���
			System.out.println("���ӿ� ����");
			System.exit(0);
		}

	}
}