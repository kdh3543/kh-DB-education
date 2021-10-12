import java.util.ArrayList;
import java.util.Scanner;

import dao.CafeMenuDAO;
import dto.MenuDTO;

public class Quiz_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CafeMenuDAO dao = new CafeMenuDAO();

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
					System.out.print("신규 메뉴 이름: ");
					String name = sc.nextLine();
					System.out.print("가격: ");
					int price = Integer.parseInt(sc.nextLine());
					
					int result = dao.insert(name, price);
					
					if(result>0) {
						System.out.println("입력 완료");
					}else
						System.out.println("잘못 입력했습니다.");
					
				}
				else if(menu.equals("2")) {
					ArrayList<MenuDTO> list = dao.selectAll();
					
					for(MenuDTO m : list) {
						System.out.println(m.getId() + " : " + m.getName()
						 + " : " + m.getPrice()); 
					}
					
				}else if(menu.equals("3")) {
					ArrayList<MenuDTO> list = dao.selectAll();
					
					for(MenuDTO m : list) {
						System.out.println(m.getId() + " : " + m.getName()
						 + " : " + m.getPrice()); 
					}
					
					System.out.print("수정할 id를 입력해주세요: ");
					int id = Integer.parseInt(sc.nextLine());
					
					System.out.print("새 메뉴명을 말씀해주세요: ");
					String name = sc.nextLine();
					System.out.print("새 가격을 말씀해주세요: ");
					int price = Integer.parseInt(sc.nextLine());
					
					int result = dao.update(new MenuDTO(id,name,price));
					if(result > 0 ) {
						System.out.println("변경 성공");
					}
				
				}else if(menu.equals("4")) {
					ArrayList<MenuDTO> list = dao.selectAll();
					
					for(MenuDTO m : list) {
						System.out.println(m.getId() + " : " + m.getName()
						 + " : " + m.getPrice()); 
					}
					
					System.out.print("삭제할 id를 입력해주세요: ");
					int id = Integer.parseInt(sc.nextLine());
					
					int result = dao.delete(id);
					
					if(result>0) {
						System.out.println("삭제 완료");
					}
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
