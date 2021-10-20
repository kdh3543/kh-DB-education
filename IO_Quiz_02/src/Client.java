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
					if(!exist) {
						System.out.println(dis.readUTF());
						while(true) {
							System.out.println("=== 방명록 시스템 ===");
							System.out.println("1. 새 글 등록");
							System.out.println("2. 방명록 보기");
							System.out.println("3. 방명록 삭제");
							System.out.println("4. 방명록 검색(seq)");
							System.out.print(">> ");
							String subMenu = sc.nextLine();

							if(subMenu.equals("1")) {

								System.out.print("작성자: ");
								String name = sc.nextLine();

								System.out.print("메세지: ");
								String message = sc.nextLine();
								dos.writeUTF("등록");
								dos.writeUTF(name);
								dos.writeUTF(message);
								dos.flush();

								int messageresult = dis.readInt();

								if(messageresult>0) {
									System.out.println("등록 완료");	
								}else {
									System.out.println("등록 실패");
								}
							}else if(subMenu.equals("2")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd");
								dos.writeUTF("보기");
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
								dos.writeUTF("삭제");
								dos.flush();
								System.out.print("삭제할 seq: ");
								int delseq = Integer.parseInt(sc.nextLine());
								dos.writeInt(delseq);
								dos.flush();
								int result = dis.readInt();
								if(result>0) {
									System.out.println("삭제 완료");
								}
							}else if(subMenu.equals("4")) {
								dos.writeUTF("검색");
								dos.flush();
								System.out.print("검색할 seq 입력: ");
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
						System.out.println("입력 정보가 올바르지 않습니다.");
					}

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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
