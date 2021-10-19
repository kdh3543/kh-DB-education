package practice;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import dao.NetflixDao;
import dto.NetflixDto;

public class Netflix {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		NetflixDao dao = new NetflixDao();
		while(true) {
			System.out.println("=== Netflix 관리 시스템 ===");
			System.out.println("1. 영화 등록");
			System.out.println("2. 영화 목록");
			System.out.println("3. 영화 삭제");
			System.out.println("4. 영화 정보 수정");
			System.out.println("5. 영화 검색");
			System.out.println("6. 종료");
			System.out.print(">> ");
			String menu = sc.nextLine();

			if(menu.equals("1")) {
				System.out.print("등록하시려는 영화의 제목을 말씀해주세요: ");
				String title = sc.nextLine();

				System.out.print("등록하시려는 영화의 설명을 말씀해주세요: ");
				String desc = sc.nextLine();

				System.out.print("등록하시려는 날짜를 말씀해주세요(yyyy/MM/dd): ");
				Date rel_date = UtilDate.utilDate(sc.nextLine(), "yyyy/MM/dd");

				NetflixDto dto = new NetflixDto(0,title,desc,rel_date);
				int result = dao.insert(dto);
				if(result > 0) {
					System.out.println("등록 완료");
				}
			}else if(menu.equals("2")) {
				ArrayList<NetflixDto> dto = dao.selectAll();

				for(NetflixDto m: dto) {
					System.out.println(m.getId() + " : " + m.getTitle() + " : " + 
							m.getDescription() + " : " + m.getRel_date());
				}
			}else if(menu.equals("3")) {
				ArrayList<NetflixDto> dto = dao.selectAll();

				for(NetflixDto m: dto) {
					System.out.println(m.getId() + " : " + m.getTitle() + " : " + 
							m.getDescription() + " : " + m.getRel_date());
				}
				
				System.out.print("삭제하실 영화의 Id를 입력해주세요: ");
				int pid = Integer.parseInt(sc.nextLine());
				
				int result = dao.delete(pid);
				if(result > 0) {
					System.out.println("삭제 완료");
				}
			}else if(menu.equals("4")) {
				ArrayList<NetflixDto> dto = dao.selectAll();

				for(NetflixDto m: dto) {
					System.out.println(m.getId() + " : " + m.getTitle() + " : " + 
							m.getDescription() + " : " + m.getRel_date());
				}
				
				System.out.print("수정하실 영화의 Id를 입력해주세요: ");
				int pid = Integer.parseInt(sc.nextLine());
				
				System.out.print("수정하실 영화 제목을 입력해주세요: ");
				String name = sc.nextLine();
				System.out.print("영화 설명을 다시 작성해주세요:");
				String redesc = sc.nextLine();
				System.out.print("바뀐 날짜를 다시 입력해주세요(yyyy/MM/dd):");
				Date redate = UtilDate.utilDate(sc.nextLine(), "yyyy/MM/dd");
				
				NetflixDto redto = new NetflixDto(pid,name,redesc,redate);
				int result = dao.update(redto);
				if(result > 0) {
					System.out.println("수정 완료");
				}else {
					System.out.println("수정 실패");
				}
				
			}else if(menu.equals("5")) {
				ArrayList<NetflixDto> dto = dao.selectAll();

				for(NetflixDto m: dto) {
					System.out.println(m.getId() + " : " + m.getTitle() + " : " + 
							m.getDescription() + " : " + m.getRel_date());
				}
				System.out.print("검색하실 영화의 id를 입력해주세요: ");
				int pid = Integer.parseInt(sc.nextLine());
				NetflixDto nfd = dao.selectById(pid);
				
				System.out.println(nfd.getId() + " " + nfd.getTitle() + " " 
						+ nfd.getDescription() + " " + nfd.getRel_date());
			}else if(menu.equals("6")) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}else {
				System.out.println("잘못 작성하셨습니다.");
			}
		}
	}

}
