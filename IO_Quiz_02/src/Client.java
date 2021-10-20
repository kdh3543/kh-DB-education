import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import dao.GuestDao;
import dto.GuestDto;

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
					if(!exist) {
						System.out.println(dis.readUTF());
						while(true) {
							System.out.println("=== ���� �ý��� ===");
							System.out.println("1. �� �� ���");
							System.out.println("2. ���� ����");
							System.out.println("3. ���� ����");
							System.out.println("4. ���� �˻�(seq)");
							System.out.print(">> ");
							String subMenu = sc.nextLine();

							if(subMenu.equals("1")) {

								System.out.print("�ۼ���: ");
								String name = sc.nextLine();

								System.out.print("�޼���: ");
								String message = sc.nextLine();
								dos.writeUTF("���");
								dos.writeUTF(name);
								dos.writeUTF(message);
								dos.flush();

								int messageresult = dis.readInt();

								if(messageresult>0) {
									System.out.println("��� �Ϸ�");	
								}else {
									System.out.println("��� ����");
								}
							}else if(subMenu.equals("2")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd");
								dos.writeUTF("����");
								dos.flush();

								int size = dis.readInt();
								for(int i = 0; i < size; i++) {
									int seq = dis.readInt();
									String writer = dis.readUTF();
									String message = dis.readUTF();
									String date = dis.readUTF();

									System.out.println(seq + " " + writer + " " + message
											+ " " + date);
								}
							}else if(subMenu.equals("3")) {
								dos.writeUTF("����");
								dos.flush();
								System.out.print("������ seq: ");
								int delseq = Integer.parseInt(sc.nextLine());
								dos.writeInt(delseq);
								dos.flush();
								int result = dis.readInt();
								if(result>0) {
									System.out.println("���� �Ϸ�");
								}
							}else if(subMenu.equals("4")) {
								dos.writeUTF("�˻�");
								dos.flush();
								System.out.print("�˻��� seq �Է�: ");
								int searchseq = Integer.parseInt(sc.nextLine());
								dos.writeInt(searchseq);
								dos.flush();
								
								int seq = dis.readInt();
								String writer = dis.readUTF();
								String message = dis.readUTF();
								String date = dis.readUTF();
								System.out.println(seq + " " + writer + " " + message + " " + date);
							}else {
								break;
							}
						}
					}else {
						System.out.println("�Է� ������ �ùٸ��� �ʽ��ϴ�.");
					}

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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
