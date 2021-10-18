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
				System.out.println("=== 관리 시스템 ===");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");		
				System.out.println("3. 종료");
				System.out.print(">> ");
				String menu = sc.nextLine();
				dos.writeUTF(menu);
				
				if(menu.equals("1")) {
					dis.readUTF();
					System.out.print("로그인 ID: ");
					String id = sc.nextLine();
					System.out.print("로그인 Password: ");
					String pw = sc.nextLine();
					dos.writeUTF(id);
					dos.writeUTF(pw);
					dos.flush();
					
					boolean exist = dis.readBoolean();
					if(exist) {
						System.out.println("입력 정보가 올바르지 않습니다.");
						continue;
					}
					System.out.println(dis.readUTF());
					
					String result = dis.readUTF();
					System.out.println(result);
				}else if(menu.equals("2")) {
					dis.readUTF();
					System.out.print("사용할 ID를 입력하세요: ");
					String id = sc.nextLine();
					
					System.out.print("사용할 PW를 입력하세요: ");
					String pw = sc.nextLine();
					
					System.out.print("이름을 입력하세요: ");
					String name = sc.nextLine();
					dos.writeUTF(id);
					dos.writeUTF(pw);
					dos.writeUTF(name);
					dos.flush();
					
					boolean existId = dis.readBoolean();
					if(existId) {
						System.out.println("이미 존재하는 Id입니다.");
						continue;
					}
					
					int result = dis.readInt();
					if(result>0) {
						System.out.println("입력 완료");
					}else {
						System.out.println("-1");
					}
					
				}else if(menu.equals("3")) {
					System.out.println("시스템 종료");
					System.exit(0);
				}else {
					System.out.println("메뉴를 다시 확인해주세요.");
				}
			}
		}
	}
}
