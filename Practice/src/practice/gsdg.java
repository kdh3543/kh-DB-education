package practice;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
public class gsdg {
	public static void main(String[] args) throws Exception{

		ServerSocket server = new ServerSocket(22000);
		
		System.out.println("연결을 대기하는 중 입니다..");
		Socket sock = server.accept();
		System.out.println(sock.getInetAddress() + "로 부터 연결되었습니다.");
		
		InputStream is = sock.getInputStream();
		
		DataInputStream dis = new DataInputStream(is);
	
		OutputStream os = sock.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");

		while(true) {
		
			String msg = dis.readUTF();
			
			System.out.println("클라이언트가 보낸 메세지 : " + msg);

		}
	}
}

