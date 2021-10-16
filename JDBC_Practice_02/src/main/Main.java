package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import dao.MovieDAO;
import dto.MovieDTO;

public class Main {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MovieDAO dao = new MovieDAO();
		while(true) {
			System.out.println("=== Netflix 관리 시스템 ===");
			System.out.println("1. 영화 등록");
			System.out.println("2. 영화 목록");
			System.out.println("3. 영화 삭제");
			System.out.println("4. 영화 정보 수정");
			System.out.println("5. 영화 검색");
			System.out.println("6. 종료");
			System.out.print(">> ");
			String input = sc.nextLine();
			try {
				if(input.equals("1")) {
					System.out.print("등록하시려는 영화의 제목을 말씀해주세요: ");
					String title = sc.nextLine();
					System.out.print("영화에 대해 설명을 적어주세요(ex. 장르, 소재등등): ");
					String desc = sc.nextLine();
					System.out.print("등록 날짜를 말씀해주세요(yyyy년MM월dd일): ");
					Date rel_date = UtilDate.sqlDate(sc.nextLine(), "yyyy년MM월dd일");

					MovieDTO dto = new MovieDTO(title,desc,rel_date,0);

					int result = dao.insert(title, desc,rel_date);

					if(result>0) {
						System.out.println("등록 완료");
					}
				}else if(input.equals("2")) {

					ArrayList<MovieDTO> dto = dao.selectAll();

					for(MovieDTO m : dto) {
						System.out.println(m.getId() + " : " + m.getTitle() + " : "
								+ m.getDescription() + " : " + m.getRel_date());
					}
				}else if(input.equals("3")) {
					ArrayList<MovieDTO> dto = dao.selectAll();

					for(MovieDTO m : dto) {
						System.out.println(m.getId() + " : " + m.getTitle() + " : "
								+ m.getDescription() + " : " + m.getRel_date());
					}

					System.out.print("삭제하실 영화의 id를 말씀해주세요: ");
					int id = Integer.parseInt(sc.nextLine());

					int result = dao.delete(id);

					if(result > 0) {
						System.out.println("삭제 성공");
					}
				}else if(input.equals("4")) {

					ArrayList<MovieDTO> dto = dao.selectAll();

					for(MovieDTO m : dto) {
						System.out.println(m.getId() + " : " + m.getTitle() + " : "
								+ m.getDescription() + " : " + m.getRel_date());
					}

					System.out.print("수정하실 영화의 id를 말씀해주세요: ");
					int id = Integer.parseInt(sc.nextLine());
					System.out.print("수정하실 영화제목을 말씀해주세요: ");
					String title = sc.nextLine();
					System.out.print("영화에 대한 설명을 다시 작성해주세요: ");
					String desc = sc.nextLine();
					System.out.print("수정할 영화 출시 날짜를 말씀해주세요(yyyy년MM월dd일): ");
					Date rel_date = UtilDate.sqlDate(sc.nextLine(), "yyyy년MM월dd일");

					MovieDTO update = new MovieDTO(title,desc,rel_date,id);

					int result = dao.update(update);
					if(result > 0) {
						System.out.println("수정 완료");
					}
				}else if(input.equals("5")) {
					System.out.print("어떤 항목으로 검색하시겠습니까(id / 제목)? ");
					String search = sc.nextLine();

					if(search.equals("id")) {
						ArrayList<MovieDTO> dto = dao.selectAll();
						for(MovieDTO m : dto) {
							System.out.println(m.getId() );
						}

						System.out.print("찾으시는 영화의 id를 입력해주세요: ");
						int id = Integer.parseInt(sc.nextLine());

						MovieDTO list = dao.selectId(id);
						System.out.println(list.getId() + " : " + list.getTitle() + " : " 
								+ list.getDescription() + " : " + list.getRel_date());					
					}else if(search.equals("제목")) {

						ArrayList<MovieDTO> dto = dao.selectAll();
						for(MovieDTO m : dto) {
							System.out.println(m.getTitle() );
						}
						System.out.print("다음 영화 중 찾으시는 영화의 제목을 입력해주세요: ");
						String title = sc.nextLine();
						ArrayList<MovieDTO> list = dao.selectTitle(title);			
						for(MovieDTO m : list) {
							System.out.println(m.getId() + " : " + m.getTitle() + " : "
									+ m.getDescription() + " : " + m.getRel_date());
						}
					}else {
						System.out.println("해당 항목으로는 검색이 불가능합니다.");
					}
				}else if(input.equals("6")) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("오류가 발생했으니 관리자에게 문의 바랍니다. 관리자 번호: 010-5234-5324");
			}
		}
	}
}
