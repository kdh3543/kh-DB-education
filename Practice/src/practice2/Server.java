package practice2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		ServerSocket server = new ServerSocket(22000);
		Socket socket = server.accept();
		
		System.out.println(socket.getInetAddress()+"�κ��� ������ �Ǿ����ϴ�.");
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		System.out.println(dis.readUTF());
		System.out.print("�޼����� �Է��ϼ���: ");
		dos.writeUTF(sc.nextLine());
	}

}
