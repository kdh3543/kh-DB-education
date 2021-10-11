import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Quiz_01_copy {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("OJDBC ����̹��� �߰����� ����.");
			System.exit(0);
		}

		while(true) {
			System.out.println("=== �޴� ���� �ý��� ===");
			System.out.println("1. �ű� �޴� ���");
			System.out.println("2. �޴� ��� ���");
			System.out.println("3. �޴� ���� ����");
			System.out.println("4. �޴� ���� ����");
			System.out.println("5. �ý��� ����");
			System.out.print(">> ");
			String menu = sc.nextLine();

			try {
				if(menu.equals("1")) {
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "kh";
					String password = "kh";
					System.out.print("�ű� �޴� �̸�: ");
					String name = sc.nextLine();
					System.out.print("����: ");
					int price = Integer.parseInt(sc.nextLine());

					Connection con = DriverManager.getConnection(url, username, password); 
					Statement stat = con.createStatement();

					String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,"
							+ "'"+name+"',"+price+")";

					int result = stat.executeUpdate(sql);

					System.out.println("��� : " + result);

					System.out.println("�Է� ����!");
					con.close();
				}
				else if(menu.equals("2")) {
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "kh";
					String password = "kh";
					
					Connection con = DriverManager.getConnection(url, username, password); 
					Statement stat = con.createStatement();

					String sql = "select * from cafe_menu order by 1";
					ResultSet rs = stat.executeQuery(sql);

					while(rs.next()) {
						int id = rs.getInt("id"); 
						String input = rs.getString("name"); 
						int money = rs.getInt("price"); 
						System.out.println(id + " : " + input + " : " + money); 
					}

					con.close();
				}else if(menu.equals("3")) {
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "kh";
					String password = "kh";
					
					Connection con = DriverManager.getConnection(url, username, password); 
					Statement stat = con.createStatement();

					String sql = "select * from cafe_menu order by 1";
					ResultSet rs = stat.executeQuery(sql);

					while(rs.next()) {
						int id = rs.getInt("id"); 
						String input = rs.getString("name"); 
						int money = rs.getInt("price"); 
						System.out.println(id + " : " + input + " : " + money); 
					}
					
					System.out.print("������ id�� �Է����ּ���: ");
					int id = Integer.parseInt(sc.nextLine());
					
					con = DriverManager.getConnection(url, username, password); 
					stat = con.createStatement();
					
					System.out.print("�� �޴����� �������ּ���: ");
					String name = sc.nextLine();
					System.out.print("�� ������ �������ּ���: ");
					int price = Integer.parseInt(sc.nextLine());
					
					sql = "update cafe_menu set price = "+price+", "
							+ "name = '"+name+"' where id = '"+id+"'";
					int result = stat.executeUpdate(sql);

					if(result > 0) {
						System.out.println("���� ����!");
					}else {
						System.out.println("�ش� ID�� ã�� �� �����ϴ�.");
					}
					con.close();
				}else if(menu.equals("4")) {
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "kh";
					String password = "kh";
					
					Connection con = DriverManager.getConnection(url, username, password); 
					Statement stat = con.createStatement();

					System.out.print("������ id�� �Է����ּ���: ");
					int id = Integer.parseInt(sc.nextLine());
					
					String sql = "delete from cafe_menu where id = '"+id+"'";
					int result = stat.executeUpdate(sql);

					System.out.println("���� ����!");
					con.close();
				}else if(menu.equals("5")) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else {
					System.out.println("�߸� �Է� �ϼ̽��ϴ�.");
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("��û�Ͻ� ����� �����ϴ� �� ������ �߻��߽��ϴ�.");
				System.out.println("���� ������ �ݺ��� ��, �����ڿ��� �������ּ���.");
				System.out.println("Email : admin@company.com");
				System.exit(0);
			}
		}

	}
}