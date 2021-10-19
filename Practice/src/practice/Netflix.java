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
			System.out.println("=== Netflix ���� �ý��� ===");
			System.out.println("1. ��ȭ ���");
			System.out.println("2. ��ȭ ���");
			System.out.println("3. ��ȭ ����");
			System.out.println("4. ��ȭ ���� ����");
			System.out.println("5. ��ȭ �˻�");
			System.out.println("6. ����");
			System.out.print(">> ");
			String menu = sc.nextLine();

			if(menu.equals("1")) {
				System.out.print("����Ͻ÷��� ��ȭ�� ������ �������ּ���: ");
				String title = sc.nextLine();

				System.out.print("����Ͻ÷��� ��ȭ�� ������ �������ּ���: ");
				String desc = sc.nextLine();

				System.out.print("����Ͻ÷��� ��¥�� �������ּ���(yyyy/MM/dd): ");
				Date rel_date = UtilDate.utilDate(sc.nextLine(), "yyyy/MM/dd");

				NetflixDto dto = new NetflixDto(0,title,desc,rel_date);
				int result = dao.insert(dto);
				if(result > 0) {
					System.out.println("��� �Ϸ�");
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
				
				System.out.print("�����Ͻ� ��ȭ�� Id�� �Է����ּ���: ");
				int pid = Integer.parseInt(sc.nextLine());
				
				int result = dao.delete(pid);
				if(result > 0) {
					System.out.println("���� �Ϸ�");
				}
			}else if(menu.equals("4")) {
				ArrayList<NetflixDto> dto = dao.selectAll();

				for(NetflixDto m: dto) {
					System.out.println(m.getId() + " : " + m.getTitle() + " : " + 
							m.getDescription() + " : " + m.getRel_date());
				}
				
				System.out.print("�����Ͻ� ��ȭ�� Id�� �Է����ּ���: ");
				int pid = Integer.parseInt(sc.nextLine());
				
				System.out.print("�����Ͻ� ��ȭ ������ �Է����ּ���: ");
				String name = sc.nextLine();
				System.out.print("��ȭ ������ �ٽ� �ۼ����ּ���:");
				String redesc = sc.nextLine();
				System.out.print("�ٲ� ��¥�� �ٽ� �Է����ּ���(yyyy/MM/dd):");
				Date redate = UtilDate.utilDate(sc.nextLine(), "yyyy/MM/dd");
				
				NetflixDto redto = new NetflixDto(pid,name,redesc,redate);
				int result = dao.update(redto);
				if(result > 0) {
					System.out.println("���� �Ϸ�");
				}else {
					System.out.println("���� ����");
				}
				
			}else if(menu.equals("5")) {
				ArrayList<NetflixDto> dto = dao.selectAll();

				for(NetflixDto m: dto) {
					System.out.println(m.getId() + " : " + m.getTitle() + " : " + 
							m.getDescription() + " : " + m.getRel_date());
				}
				System.out.print("�˻��Ͻ� ��ȭ�� id�� �Է����ּ���: ");
				int pid = Integer.parseInt(sc.nextLine());
				NetflixDto nfd = dao.selectById(pid);
				
				System.out.println(nfd.getId() + " " + nfd.getTitle() + " " 
						+ nfd.getDescription() + " " + nfd.getRel_date());
			}else if(menu.equals("6")) {
				System.out.println("�ý����� �����մϴ�.");
				System.exit(0);
			}else {
				System.out.println("�߸� �ۼ��ϼ̽��ϴ�.");
			}
		}
	}

}
