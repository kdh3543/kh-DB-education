import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) throws Exception{
		System.out.println("���� ���� ������Դϴ�.");
		ServerSocket server = new ServerSocket(22000);
		Socket socket = server.accept();

		System.out.println(socket + "���κ��� ����Ǿ����ϴ�.");

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		while(true) {
			String msg = dis.readUTF();
			System.out.println("Ŭ���̾�Ʈ�κ��� �� �޼���: " + msg);
			if(msg.equals("���")) {
				dos.writeUTF("Apple");
			}else if(msg.equals("����")) {
				dos.writeUTF("Grape");
			}else if(msg.equals("����")) {
				dos.writeUTF("Strawberry");
			}else {
				dos.writeUTF("None");
			}
			dos.flush();
		}
	}
}