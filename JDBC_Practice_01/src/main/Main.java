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
		//Messages Project - ���� �ý���
		MessageDAO dao = new MessageDAO();
			
		while(true) {
			System.out.println("=== ���� �ý��� ===");
			System.out.println("1. ���� �����");
			System.out.println("2. ���� ����");
			System.out.println("3. ���� ����");
			System.out.println("4. ���� ����");
			System.out.println("5. ���� �˻�"); // id�� �˻� / �ۼ��� �˻�
			System.out.println("6. ����");
			System.out.print(">> ");

			String key = sc.nextLine();
			try {
				if(key.equals("1")) {
					System.out.print("�ۼ����� �̸��� �������ּ���: ");
					String writer = sc.nextLine();

					System.out.print("����� �޼����� �������ּ���: ");
					String message = sc.nextLine();
					
					System.out.print("�湮 ��¥ (yyyy/MM/dd) : ");
					Date input = DateUtils.StringToSQLDate(sc.nextLine(),"yyyy/MM/dd");
					
					MessageDTO dto = new MessageDTO(0,writer,message,input);
					
					int result = dao.insert(dto);
					if(result > 0) {
						System.out.println("�Է� �Ϸ�");
					}else {
						System.out.println("�Է� ����");
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
					System.out.print("�����Ͻ� seq�� �Է����ּ���: ");
					int seq = Integer.parseInt(sc.nextLine());
					
					int result = dao.delete(seq);
					if(result > 0) {
						System.out.println("���� �Ϸ�");
					}else {
						System.out.println("���� ����");
					}					
				}else if(key.equals("4")) {
					ArrayList<MessageDTO> dto = dao.select();
					for(MessageDTO m:dto) {
						System.out.println(m.getSeq()+" : "+m.getWriter()+" : "
								+m.getMessage() + " : " + m.getStringDate());
					}
					System.out.print("�����Ͻ� seq�� �Է����ּ���: ");
					int seq = Integer.parseInt(sc.nextLine());
					System.out.print("�� �ۼ��� �̸��� �����ּ���: ");
					String writer = sc.nextLine();
					System.out.print("�� �޼����� �����ּ���: ");
					String message = sc.nextLine();
					
					System.out.print("���� ��¥ (yyyy/MM/dd) : ");
					Date input = DateUtils.StringToSQLDate(sc.nextLine(),"yyyy/MM/dd");
					
					MessageDTO dto2 = new MessageDTO(seq, message, writer, input);
					
					int result = dao.update(dto2);
					if(result>0) {
						System.out.println("���� �Ϸ�");
					}else {
						System.out.println("���� ����");
					}
					
				}else if(key.equals("5")) {
					System.out.print("� ī�װ��� �˻��Ͻðڽ��ϱ�? ");
					String search = sc.nextLine();
					if(search.equals("id")) {
						System.out.print("�˻��� id�� �������ּ���: ");
						int id = Integer.parseInt(sc.nextLine());
						
						MessageDTO dto = dao.selectId(id);
						if(dto != null) {
							System.out.println(dto.getSeq()+" : "+dto.getWriter()+
									" : "+dto.getMessage());
						}else {
							System.out.println("�ش� seq�� ã�� �� �����ϴ�.");
						}											
					}else if(search.equals("writer")) {
						System.out.print("�˻��� �ۼ��ڸ� �������ּ���: ");
						String writer = sc.nextLine();
						
						ArrayList<MessageDTO> dto = dao.selectWriter(writer);
						for(MessageDTO m:dto) {
							System.out.println(m.getSeq()+" : "+m.getWriter()+" : "
									+m.getMessage());
						}
					}else {
						System.out.println("�߸��� ī�װ� �Դϴ�.");
					}

				}else if(key.equals("6")) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("��û�Ͻ� ����� ������ �߻��߽��ϴ�.");
				System.out.println("���ǰ� �ʿ��Ͻ� ��� �����ڿ��� ���� ���� ��Ź�帳�ϴ�.");
				System.out.println("000000@email.net");
			}
		}
	}
}
