import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Utils.EncryptUtils;
import dao.LoginDao;
import dto.LoginDto;

public class Server {

	public static void main(String[] args) throws Exception{
		LoginDao dao = new LoginDao();
		System.out.println("���� �����...");
		try(ServerSocket server = new ServerSocket(22000);
				Socket socket = server.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());){
			while(true) {
				String msg = dis.readUTF();
				if(msg.equals("1")) {
					dos.writeUTF("�α��� �Ͻ� ID�� PW�� �Է����ּ���.");
					String id = dis.readUTF();
					String pw = EncryptUtils.getSHA512(dis.readUTF());
					
					boolean exist = !dao.select(id,pw);
					dos.writeBoolean(exist);
					dos.flush();
					if(exist) {continue;}
					
					System.out.println("�Էµ� Id : " + id);
					System.out.println("�Էµ� Pw : " + pw);
					dos.writeUTF("�α��� �Ǿ����ϴ�.");			
					dos.flush();
				}else if(msg.equals("2")) {
					dos.writeUTF("���� �����Ͻ� �α��� ����(ID, PW, �̸�)�� ������ּ���.");
					String id = dis.readUTF();
					String pw = EncryptUtils.getSHA512(dis.readUTF());
					String name = dis.readUTF();
					
					//�ߺ��˻� ��� Ȯ��
					boolean existId = dao.isIdExist(id);
					dos.writeBoolean(existId);
					dos.flush();
					
					if(existId) {continue;}
					
					LoginDto dto = new LoginDto(id,pw,name,null);
					
					int result = dao.insert(dto);
					dos.writeInt(result);
					dos.flush();
				}
			}

		}
	}
}
