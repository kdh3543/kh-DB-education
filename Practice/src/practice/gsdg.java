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
		
		System.out.println("������ ����ϴ� �� �Դϴ�..");
		Socket sock = server.accept();
		System.out.println(sock.getInetAddress() + "�� ���� ����Ǿ����ϴ�.");
		
		InputStream is = sock.getInputStream();
		
		DataInputStream dis = new DataInputStream(is);
	
		OutputStream os = sock.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� hh:mm:ss");

		while(true) {
		
			String msg = dis.readUTF();
			
			System.out.println("Ŭ���̾�Ʈ�� ���� �޼��� : " + msg);

		}
	}
}

