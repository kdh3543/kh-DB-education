import java.util.ArrayList;
import java.util.Scanner;

import dao.CafeMenuDAO;
import dto.MenuDTO;

public class Quiz_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CafeMenuDAO dao = new CafeMenuDAO();

		while(true) {
			System.out.println("=== �޴� ���� �ý��� ===");
			System.out.println("1. �ű� �޴� ���");
			System.out.println("2. �޴� ��� ���");
			System.out.println("3. �޴� ���� ����");
			System.out.println("4. �޴� ���� ����");
			System.out.println("5. �ý��� ����");
			System.out.print(">> ");
			String menu = sc.nextLine();

			try {
				if(menu.equals("1")) {
					System.out.print("�ű� �޴� �̸�: ");
					String name = sc.nextLine();
					System.out.print("����: ");
					int price = Integer.parseInt(sc.nextLine());
					
					int result = dao.insert(name, price);
					
					if(result>0) {
						System.out.println("�Է� �Ϸ�");
					}else
						System.out.println("�߸� �Է��߽��ϴ�.");
					
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
					
					System.out.print("������ id�� �Է����ּ���: ");
					int id = Integer.parseInt(sc.nextLine());
					
					System.out.print("�� �޴����� �������ּ���: ");
					String name = sc.nextLine();
					System.out.print("�� ������ �������ּ���: ");
					int price = Integer.parseInt(sc.nextLine());
					
					int result = dao.update(new MenuDTO(id,name,price));
					if(result > 0 ) {
						System.out.println("���� ����");
					}
				
				}else if(menu.equals("4")) {
					ArrayList<MenuDTO> list = dao.selectAll();
					
					for(MenuDTO m : list) {
						System.out.println(m.getId() + " : " + m.getName()
						 + " : " + m.getPrice()); 
					}
					
					System.out.print("������ id�� �Է����ּ���: ");
					int id = Integer.parseInt(sc.nextLine());
					
					int result = dao.delete(id);
					
					if(result>0) {
						System.out.println("���� �Ϸ�");
					}
				}else if(menu.equals("5")) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else {
					System.out.println("�߸� �Է� �ϼ̽��ϴ�.");
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("��û�Ͻ� ����� �����ϴ� �� ������ �߻��߽��ϴ�.");
				System.out.println("���� ������ �ݺ��� ��, �����ڿ��� �������ּ���.");
				System.out.println("Email : admin@company.com");
				System.exit(0);
			}
		}

	}
}
