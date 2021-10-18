import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import Utils.EncryptUtils;

public class Client {

	public static void main(String[] args) throws Exception{
		try(Scanner sc = new Scanner(System.in);
				Socket client = new Socket("127.0.0.1", 22000);
				DataInputStream dis = new DataInputStream(client.getInputStream());
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());){
			while(true) {
				System.out.println("=== ���� �ý��� ===");
				System.out.println("1. �α���");
				System.out.println("2. ȸ������");		
				System.out.println("3. ����");
				System.out.print(">> ");
				String menu = sc.nextLine();
				dos.writeUTF(menu);
				
				if(menu.equals("1")) {
					dis.readUTF();
					System.out.print("�α��� ID: ");
					String id = sc.nextLine();
					System.out.print("�α��� Password: ");
					String pw = sc.nextLine();
					dos.writeUTF(id);
					dos.writeUTF(pw);
					dos.flush();
					
					boolean exist = dis.readBoolean();
					if(exist) {
						System.out.println("�Է� ������ �ùٸ��� �ʽ��ϴ�.");
						continue;
					}
					System.out.println(dis.readUTF());
					
					String result = dis.readUTF();
					System.out.println(result);
				}else if(menu.equals("2")) {
					dis.readUTF();
					System.out.print("����� ID�� �Է��ϼ���: ");
					String id = sc.nextLine();
					
					System.out.print("����� PW�� �Է��ϼ���: ");
					String pw = sc.nextLine();
					
					System.out.print("�̸��� �Է��ϼ���: ");
					String name = sc.nextLine();
					dos.writeUTF(id);
					dos.writeUTF(pw);
					dos.writeUTF(name);
					dos.flush();
					
					boolean existId = dis.readBoolean();
					if(existId) {
						System.out.println("�̹� �����ϴ� Id�Դϴ�.");
						continue;
					}
					
					int result = dis.readInt();
					if(result>0) {
						System.out.println("�Է� �Ϸ�");
					}else {
						System.out.println("-1");
					}
					
				}else if(menu.equals("3")) {
					System.out.println("�ý��� ����");
					System.exit(0);
				}else {
					System.out.println("�޴��� �ٽ� Ȯ�����ּ���.");
				}
			}
		}
	}
}
