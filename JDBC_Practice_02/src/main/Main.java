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
			System.out.println("=== Netflix ���� �ý��� ===");
			System.out.println("1. ��ȭ ���");
			System.out.println("2. ��ȭ ���");
			System.out.println("3. ��ȭ ����");
			System.out.println("4. ��ȭ ���� ����");
			System.out.println("5. ��ȭ �˻�");
			System.out.println("6. ����");
			System.out.print(">> ");
			String input = sc.nextLine();
			try {
				if(input.equals("1")) {
					System.out.print("����Ͻ÷��� ��ȭ�� ������ �������ּ���: ");
					String title = sc.nextLine();
					System.out.print("��ȭ�� ���� ������ �����ּ���(ex. �帣, ������): ");
					String desc = sc.nextLine();
					System.out.print("��� ��¥�� �������ּ���(yyyy��MM��dd��): ");
					Date rel_date = UtilDate.sqlDate(sc.nextLine(), "yyyy��MM��dd��");

					MovieDTO dto = new MovieDTO(title,desc,rel_date,0);

					int result = dao.insert(title, desc,rel_date);

					if(result>0) {
						System.out.println("��� �Ϸ�");
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

					System.out.print("�����Ͻ� ��ȭ�� id�� �������ּ���: ");
					int id = Integer.parseInt(sc.nextLine());

					int result = dao.delete(id);

					if(result > 0) {
						System.out.println("���� ����");
					}
				}else if(input.equals("4")) {

					ArrayList<MovieDTO> dto = dao.selectAll();

					for(MovieDTO m : dto) {
						System.out.println(m.getId() + " : " + m.getTitle() + " : "
								+ m.getDescription() + " : " + m.getRel_date());
					}

					System.out.print("�����Ͻ� ��ȭ�� id�� �������ּ���: ");
					int id = Integer.parseInt(sc.nextLine());
					System.out.print("�����Ͻ� ��ȭ������ �������ּ���: ");
					String title = sc.nextLine();
					System.out.print("��ȭ�� ���� ������ �ٽ� �ۼ����ּ���: ");
					String desc = sc.nextLine();
					System.out.print("������ ��ȭ ��� ��¥�� �������ּ���(yyyy��MM��dd��): ");
					Date rel_date = UtilDate.sqlDate(sc.nextLine(), "yyyy��MM��dd��");

					MovieDTO update = new MovieDTO(title,desc,rel_date,id);

					int result = dao.update(update);
					if(result > 0) {
						System.out.println("���� �Ϸ�");
					}
				}else if(input.equals("5")) {
					System.out.print("� �׸����� �˻��Ͻðڽ��ϱ�(id / ����)? ");
					String search = sc.nextLine();

					if(search.equals("id")) {
						ArrayList<MovieDTO> dto = dao.selectAll();
						for(MovieDTO m : dto) {
							System.out.println(m.getId() );
						}

						System.out.print("ã���ô� ��ȭ�� id�� �Է����ּ���: ");
						int id = Integer.parseInt(sc.nextLine());

						MovieDTO list = dao.selectId(id);
						System.out.println(list.getId() + " : " + list.getTitle() + " : " 
								+ list.getDescription() + " : " + list.getRel_date());					
					}else if(search.equals("����")) {

						ArrayList<MovieDTO> dto = dao.selectAll();
						for(MovieDTO m : dto) {
							System.out.println(m.getTitle() );
						}
						System.out.print("���� ��ȭ �� ã���ô� ��ȭ�� ������ �Է����ּ���: ");
						String title = sc.nextLine();
						ArrayList<MovieDTO> list = dao.selectTitle(title);			
						for(MovieDTO m : list) {
							System.out.println(m.getId() + " : " + m.getTitle() + " : "
									+ m.getDescription() + " : " + m.getRel_date());
						}
					}else {
						System.out.println("�ش� �׸����δ� �˻��� �Ұ����մϴ�.");
					}
				}else if(input.equals("6")) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("������ �߻������� �����ڿ��� ���� �ٶ��ϴ�. ������ ��ȣ: 010-5234-5324");
			}
		}
	}
}
