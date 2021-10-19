package practice;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CafeDao;
import dto.CafeDto;

public class Practice {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		CafeDao dao = new CafeDao();
		while(true) {

			System.out.println("=== 메뉴 관리 시스템 ===");
			System.out.println("1. 신규 메뉴 등록");
			System.out.println("2. 메뉴 목록 출력");
			System.out.println("3. 메뉴 정보 수정");
			System.out.println("4. 메뉴 정보 삭제");
			System.out.println("5. 메뉴 검색");
			System.out.println("6. 시스템 종료");
			System.out.print(">>");
			String pick = sc.nextLine();

			if(pick.equals("1")) {
				System.out.print("등록하실 메뉴의 메뉴 명을 말씀해주세요: ");
				String menu = sc.nextLine();
				System.out.print("메뉴의 가격을 말씀해주세요: ");
				int price = Integer.parseInt(sc.nextLine());
				
				CafeDto dto = new CafeDto(0,menu,price,null);
				
				int result = dao.insert(dto);
				if(result > 0) {
					System.out.println("등록 완료");
				}else {
					System.out.println("등록 실패");
				}

			}else if(pick.equals("2")) {
				ArrayList<CafeDto> dto = dao.selectAll();

				for(CafeDto m : dto) {
					System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice()
					+ " " + m.getReg_date());
				}
				
			}else if(pick.equals("3")) {
				ArrayList<CafeDto> dto = dao.selectAll();

				for(CafeDto m : dto) {
					System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice()
					+ " " + m.getReg_date());
				}

				System.out.print("수정하실 메뉴의 id를 말씀해주세요: ");
				int id = Integer.parseInt(sc.nextLine());

				System.out.print("수정하실 메뉴명을 말씀해주세요: ");
				String menu = sc.nextLine();
				System.out.print("메뉴의 새 가격을 말씀해주세요:");
				int price = Integer.parseInt(sc.nextLine());
				
				CafeDto dto1 = new CafeDto(id,menu,price,null);
				
				int result = dao.update(dto1);
				
				if(result>0) {
					System.out.println("수정 완료");
				}else {
					System.out.println("수정 실패");
				}
			}else if(pick.equals("4")) {
				ArrayList<CafeDto> dto = dao.selectAll();

				for(CafeDto m : dto) {
					System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice()
					+ " " + m.getReg_date());
				}
				
				System.out.print("삭제하실 메뉴의 id를 말씀해주세요: ");
				int id = Integer.parseInt(sc.nextLine());
				
				int result = dao.delete(id);
				
				if(result>0) {
					System.out.println("삭제 완료");
				}else {
					System.out.println("삭제 실패");
				}

			}else if(pick.equals("5")) {
				ArrayList<CafeDto> dto = dao.selectAll();

				for(CafeDto m : dto) {
					System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice() 
					+ " " + m.getReg_date());
				}
				
				System.out.print("검색하실 카테고리를 말씀해주세요(id/menu): ");
				String search = sc.nextLine();
				
				if(search.equals("id")) {
					System.out.print("검색하실 메뉴의 id를 말씀해주세요: ");
					int id = Integer.parseInt(sc.nextLine());
					CafeDto result = dao.selectById(id);
					
					System.out.println(result.getId() + " " + result.getMenu() + " " + result.getPrice()
					+ " " + result.getReg_date());
				}else if(search.equals("menu")) {
					System.out.print("검색하실 메뉴명을 말씀해주세요: ");
					String menu = sc.nextLine();
					ArrayList<CafeDto> result = dao.selectByMenu(menu);
					
					for(CafeDto m : result) {
						System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice()
						+ " " + m.getReg_date());
					}
				}else{
					System.out.println("선택하신 카테고리는 존재하지 않습니다.");
				}
				
			}else if(pick.equals("5")) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

}
