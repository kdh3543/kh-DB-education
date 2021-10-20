import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

	public static void main(String[] args) throws Exception{
		try(ServerSocket server = new ServerSocket(30000);){
			System.out.println("���� ��� ��...");
			while(true) {
				try(Socket socket = server.accept();
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						DataInputStream dis = new DataInputStream(socket.getInputStream());){

					System.out.println(socket.getInetAddress()+"�κ��� ����Ǿ����ϴ�.");

					File f = new File("D:\\dest");
					File[] list = f.listFiles();
					dos.writeInt(list.length);

					for(File m:list) {
						dos.writeUTF(m.getName());
						dos.flush();
					}

					String input = dis.readUTF();
					System.out.println("Ŭ���̾�Ʈ�� ���� ���޹��� ���� �̸�: "+input);

					File loadFile = new File("D:\\dest/"+input+"");
					try(FileInputStream fis = new FileInputStream(loadFile);
							DataInputStream ramDis = new DataInputStream(fis);){

						byte[] direct = new byte[(int)loadFile.length()];

						ramDis.readFully(direct);

						dos.writeInt(direct.length);
						dos.write(direct);
						dos.flush();
					}
				}catch(Exception e) {
					System.out.println("�����ڰ� ������ ���� ƨ����ϴ�.");
				}
			}
		}
	}
}
