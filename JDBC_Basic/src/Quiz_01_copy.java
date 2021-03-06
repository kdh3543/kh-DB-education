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
			System.out.println("OJDBC 드라이버를 발견하지 못함.");
			System.exit(0);
		}

		while(true) {
			System.out.println("=== 메뉴 관리 시스템 ===");
			System.out.println("1. 신규 메뉴 등록");
			System.out.println("2. 메뉴 목록 출력");
			System.out.println("3. 메뉴 정보 수정");
			System.out.println("4. 메뉴 정보 삭제");
			System.out.println("5. 시스템 종료");
			System.out.print(">> ");
			String menu = sc.nextLine();

			try {
				if(menu.equals("1")) {
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "kh";
					String password = "kh";
					System.out.print("신규 메뉴 이름: ");
					String name = sc.nextLine();
					System.out.print("가격: ");
					int price = Integer.parseInt(sc.nextLine());

					Connection con = DriverManager.getConnection(url, username, password); 
					Statement stat = con.createStatement();

					String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,"
							+ "'"+name+"',"+price+")";

					int result = stat.executeUpdate(sql);

					System.out.println("결과 : " + result);

					System.out.println("입력 성공!");
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

					String sql = "select * from cafe_menu orde1r by 1";
					ResultSet rs = stat.executeQuery(sql);

					while(rs.next()) {
						int id = rs.getInt("id"); 
						String input = rs.getString("name"); 
						int money = rs.getInt("price"); 
						System.out.println(id + " : " + input + " : " + money); 
					}
					
					System.out.print("수정할 id를 입력해주세요: ");
					int id = Integer.parseInt(sc.nextLine());
					
					con = DriverManager.getConnection(url, username, password); 
					stat = con.createStatement();
					
					System.out.print("새 메뉴명을 말씀해주세요: ");
					String name = sc.nextLine();
					System.out.print("새 가격을 말씀해주세요: ");
					int price = Integer.parseInt(sc.nextLine());
					
					sql = "update cafe_menu set price = "+price+", "
							+ "name = '"+name+"' where id = '"+id+"'";
					int result = stat.executeUpdate(sql);

					if(result > 0) {
						System.out.println("수정 성공!");
					}else {
						System.out.println("해당 ID를 찾을 수 없습니다.");
					}
					con.close();
				}else if(menu.equals("4")) {
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String username = "kh";
					String password = "kh";
					
					Connection con = DriverManager.getConnection(url, username, password); 
					Statement stat = con.createStatement();

					System.out.print("삭제할 id를 입력해주세요: ");
					int id = Integer.parseInt(sc.nextLine());
					
					String sql = "delete from cafe_menu where id = '"+id+"'";
					int result = stat.executeUpdate(sql);

					System.out.println("삭제 성공!");
					con.close();
				}else if(menu.equals("5")) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				}else {
					System.out.println("잘못 입력 하셨습니다.");
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("요청하신 기능을 수행하는 중 오류가 발생했습니다.");
				System.out.println("같은 오류가 반복될 시, 관리자에게 문의해주세요.");
				System.out.println("Email : admin@company.com");
				System.exit(0);
			}
		}

	}
}
