package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

import dao.MessageDAO;
import dto.MessageDTO;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Messages Project - 방명록 시스템
		MessageDAO dao = new MessageDAO();
			
		while(true) {
			System.out.println("=== 방명록 시스템 ===");
			System.out.println("1. 방명록 남기기");
			System.out.println("2. 방명록 보기");
			System.out.println("3. 방명록 삭제");
			System.out.println("4. 방명록 수정");
			System.out.println("5. 방명록 검색"); // id로 검색 / 작성자 검색
			System.out.println("6. 종료");
			System.out.print(">> ");

			String key = sc.nextLine();
			try {
				if(key.equals("1")) {
					System.out.print("작성자의 이름을 말씀해주세요: ");
					String writer = sc.nextLine();

					System.out.print("남기실 메세지를 말씀해주세요: ");
					String message = sc.nextLine();
					
					System.out.print("방문 날짜 (yyyy/MM/dd) : ");
					Date input = DateUtils.StringToSQLDate(sc.nextLine(),"yyyy/MM/dd");
					
					MessageDTO dto = new MessageDTO(0,writer,message,input);
					
					int result = dao.insert(dto);
					if(result > 0) {
						System.out.println("입력 완료");
					}else {
						System.out.println("입력 실패");
					}
				}else if(key.equals("2")) {
					ArrayList<MessageDTO> dto = dao.select();
					for(MessageDTO m:dto) {
						System.out.println(m.getSeq()+" : "+m.getWriter()+" : "
								+m.getMessage() + " : " + m.getStringDate());
					}					
				}else if(key.equals("3")) {
					ArrayList<MessageDTO> dto = dao.select();
					for(MessageDTO m:dto) {
						System.out.println(m.getSeq()+" : "+m.getWriter()+" : "
								+m.getMessage() + " : " + m.getStringDate());
					}				
					System.out.print("삭제하실 seq를 입력해주세요: ");
					int seq = Integer.parseInt(sc.nextLine());
					
					int result = dao.delete(seq);
					if(result > 0) {
						System.out.println("삭제 완료");
					}else {
						System.out.println("삭제 실패");
					}					
				}else if(key.equals("4")) {
					ArrayList<MessageDTO> dto = dao.select();
					for(MessageDTO m:dto) {
						System.out.println(m.getSeq()+" : "+m.getWriter()+" : "
								+m.getMessage() + " : " + m.getStringDate());
					}
					System.out.print("수정하실 seq를 입력해주세요: ");
					int seq = Integer.parseInt(sc.nextLine());
					System.out.print("새 작성자 이름을 적어주세요: ");
					String writer = sc.nextLine();
					System.out.print("새 메세지를 적어주세요: ");
					String message = sc.nextLine();
					
					System.out.print("수정 날짜 (yyyy/MM/dd) : ");
					Date input = DateUtils.StringToSQLDate(sc.nextLine(),"yyyy/MM/dd");
					
					MessageDTO dto2 = new MessageDTO(seq, message, writer, input);
					
					int result = dao.update(dto2);
					if(result>0) {
						System.out.println("수정 완료");
					}else {
						System.out.println("수정 실패");
					}
					
				}else if(key.equals("5")) {
					System.out.print("어떤 카테고리로 검색하시겠습니까? ");
					String search = sc.nextLine();
					if(search.equals("id")) {
						System.out.print("검색할 id를 말씀해주세요: ");
						int id = Integer.parseInt(sc.nextLine());
						
						MessageDTO dto = dao.selectId(id);
						if(dto != null) {
							System.out.println(dto.getSeq()+" : "+dto.getWriter()+
									" : "+dto.getMessage());
						}else {
							System.out.println("해당 seq를 찾을 수 없습니다.");
						}											
					}else if(search.equals("writer")) {
						System.out.print("검색할 작성자를 말씀해주세요: ");
						String writer = sc.nextLine();
						
						ArrayList<MessageDTO> dto = dao.selectWriter(writer);
						for(MessageDTO m:dto) {
							System.out.println(m.getSeq()+" : "+m.getWriter()+" : "
									+m.getMessage());
						}
					}else {
						System.out.println("잘못된 카테고리 입니다.");
					}

				}else if(key.equals("6")) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("요청하신 기능이 오류가 발생했습니다.");
				System.out.println("문의가 필요하신 경우 관리자에게 메일 전달 부탁드립니다.");
				System.out.println("000000@email.net");
			}
		}
	}
}
