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

			System.out.println("=== �޴� ���� �ý��� ===");
			System.out.println("1. �ű� �޴� ���");
			System.out.println("2. �޴� ��� ���");
			System.out.println("3. �޴� ���� ����");
			System.out.println("4. �޴� ���� ����");
			System.out.println("5. �޴� �˻�");
			System.out.println("6. �ý��� ����");
			System.out.print(">>");
			String pick = sc.nextLine();

			if(pick.equals("1")) {
				System.out.print("����Ͻ� �޴��� �޴� ���� �������ּ���: ");
				String menu = sc.nextLine();
				System.out.print("�޴��� ������ �������ּ���: ");
				int price = Integer.parseInt(sc.nextLine());
				
				CafeDto dto = new CafeDto(0,menu,price,null);
				
				int result = dao.insert(dto);
				if(result > 0) {
					System.out.println("��� �Ϸ�");
				}else {
					System.out.println("��� ����");
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

				System.out.print("�����Ͻ� �޴��� id�� �������ּ���: ");
				int id = Integer.parseInt(sc.nextLine());

				System.out.print("�����Ͻ� �޴����� �������ּ���: ");
				String menu = sc.nextLine();
				System.out.print("�޴��� �� ������ �������ּ���:");
				int price = Integer.parseInt(sc.nextLine());
				
				CafeDto dto1 = new CafeDto(id,menu,price,null);
				
				int result = dao.update(dto1);
				
				if(result>0) {
					System.out.println("���� �Ϸ�");
				}else {
					System.out.println("���� ����");
				}
			}else if(pick.equals("4")) {
				ArrayList<CafeDto> dto = dao.selectAll();

				for(CafeDto m : dto) {
					System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice()
					+ " " + m.getReg_date());
				}
				
				System.out.print("�����Ͻ� �޴��� id�� �������ּ���: ");
				int id = Integer.parseInt(sc.nextLine());
				
				int result = dao.delete(id);
				
				if(result>0) {
					System.out.println("���� �Ϸ�");
				}else {
					System.out.println("���� ����");
				}

			}else if(pick.equals("5")) {
				ArrayList<CafeDto> dto = dao.selectAll();

				for(CafeDto m : dto) {
					System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice() 
					+ " " + m.getReg_date());
				}
				
				System.out.print("�˻��Ͻ� ī�װ��� �������ּ���(id/menu): ");
				String search = sc.nextLine();
				
				if(search.equals("id")) {
					System.out.print("�˻��Ͻ� �޴��� id�� �������ּ���: ");
					int id = Integer.parseInt(sc.nextLine());
					CafeDto result = dao.selectById(id);
					
					System.out.println(result.getId() + " " + result.getMenu() + " " + result.getPrice()
					+ " " + result.getReg_date());
				}else if(search.equals("menu")) {
					System.out.print("�˻��Ͻ� �޴����� �������ּ���: ");
					String menu = sc.nextLine();
					ArrayList<CafeDto> result = dao.selectByMenu(menu);
					
					for(CafeDto m : result) {
						System.out.println(m.getId() + " " + m.getMenu()+ " " + m.getPrice()
						+ " " + m.getReg_date());
					}
				}else{
					System.out.println("�����Ͻ� ī�װ��� �������� �ʽ��ϴ�.");
				}
				
			}else if(pick.equals("5")) {
				System.out.println("�ý����� �����մϴ�.");
				System.exit(0);
			}else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}

}
