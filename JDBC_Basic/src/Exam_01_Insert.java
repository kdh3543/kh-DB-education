import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Exam_01_Insert {
	public static void main(String[] args) {


		// 1. OJDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			
		}


		// 2. DBMS에 접속
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		// localhost ==> 내피시의 ip주소를 뜻함
		// 프로젝트 할 때에는 localhost가 아닌 조원의 ip 주소를 넣게됨
		String username = "kh";
		String password = "kh";

		Scanner sc = new Scanner(System.in);
		System.out.print("신규 메뉴 이름: ");
		String name = sc.nextLine();
		
		System.out.print("신규 메뉴 가격: ");
		int price = Integer.parseInt(sc.nextLine());
		
		try {
			Connection con = DriverManager.getConnection(url, username, password);
			
			//3. Query를 전달할 수 있는 인스턴스를 생성
			Statement stat = con.createStatement();
			//4. 실행할 Query문 작성 및 Query 실행
			String sql = "insert into cafe_menu values(cafe_menu_seq.nextval,"
					+ "'"+name+"',"+price+")";
			
			int result = stat.executeUpdate(sql);
			
			System.out.println("결과 : " + result);
			
			if(result > 0) {
				System.out.println("성공적으로 입력되었습니다.");
			}else {
				System.out.println("입력에 실패했습니다.");
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace(); // 에러가 발생하게 된 원인을 출력
			System.out.println("접속에 실패");
			System.exit(0);
		}

	}
}
