import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) throws Exception{
		System.out.println("서버 연결 대기중입니다.");
		ServerSocket server = new ServerSocket(22000);
		Socket socket = server.accept();

		System.out.println(socket + "으로부터 연결되었습니다.");

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		while(true) {
			String msg = dis.readUTF();
			System.out.println("클라이언트로부터 온 메세지: " + msg);
			if(msg.equals("사과")) {
				dos.writeUTF("Apple");
			}else if(msg.equals("포도")) {
				dos.writeUTF("Grape");
			}else if(msg.equals("딸기")) {
				dos.writeUTF("Strawberry");
			}else {
				dos.writeUTF("None");
			}
			dos.flush();
		}
	}
}