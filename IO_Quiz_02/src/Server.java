import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Utils.EncryptUtils;
import dao.GuestDao;
import dao.LoginDao;
import dto.GuestDto;
import dto.LoginDto;

public class Server {

	public static void main(String[] args) throws Exception{
		LoginDao ldao = LoginDao.getInstance();
		GuestDao gdao = GuestDao.getInstance();
		System.out.println("연결 대기중...");
		try(ServerSocket server = new ServerSocket(22000);
				Socket socket = server.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());){
			while(true) {
				String msg = dis.readUTF();
				if(msg.equals("1")) {
					dos.writeUTF("로그인 하실 ID와 PW를 입력해주세요.");
					String id = dis.readUTF();
					String pw = EncryptUtils.getSHA512(dis.readUTF());
					
					boolean exist = !ldao.select(id,pw);
					dos.writeBoolean(exist);
					dos.flush();
					if(exist) {continue;}
					
					System.out.println("입력된 Id : " + id);
					System.out.println("입력된 Pw : " + pw);
					dos.writeUTF("로그인 되었습니다.");			
					dos.flush();
					
				}else if(msg.equals("2")) {
					dos.writeUTF("새로 가입하실 로그인 정보(ID, PW, 이름)를 등록해주세요.");
					String id = dis.readUTF();
					String pw = EncryptUtils.getSHA512(dis.readUTF());
					String name = dis.readUTF();
					
					//중복검사 결과 확인
					boolean existId = ldao.isIdExist(id);
					dos.writeBoolean(existId);
					dos.flush();
					
					if(existId) {continue;}
					
					LoginDto dto = new LoginDto(id,pw,name,null);
					
					int result = ldao.insert(dto);
					dos.writeInt(result);
					dos.flush();
				}else if(msg.equals("등록")) {
					String name = dis.readUTF();
					String message = dis.readUTF();
					GuestDto gdto = new GuestDto(0,name,message,null);
					
					int result = gdao.insert(gdto);
					dos.writeInt(result);
					dos.flush();
				}else if(msg.equals("보기")) {
					SimpleDateFormat sdf = new SimpleDateFormat();
					ArrayList<GuestDto> select = gdao.selectAll();
					
					dos.writeInt(select.size());
					dos.flush();
					
					for(GuestDto m : select) {
						dos.writeInt(m.getSeq());
						dos.writeUTF(m.getWriter());
						dos.writeUTF(m.getMessage());
						dos.writeUTF(m.getFormedDate());
						dos.flush();
					}
				}else if(msg.equals("삭제")) {
					int delseq = dis.readInt();
					int result = gdao.delete(delseq);
					dos.writeInt(result);
					dos.flush();
				}else if(msg.equals("검색")) {
					int searchseq = dis.readInt();
					GuestDto search = gdao.selectBySeq(searchseq);
					
					dos.writeInt(search.getSeq());
					dos.writeUTF(search.getWriter());
					dos.writeUTF(search.getMessage());
					dos.writeUTF(search.getFormedDate());
					dos.flush();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
