package practice2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws Exception{
		System.out.println("���� ���� ��...");
		Scanner sc = new Scanner(System.in);
		ServerSocket server = new ServerSocket(22000);
		Socket socket = server.accept();
		System.out.println(socket.getInetAddress()+"�κ��� ����Ǿ����ϴ�.");
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		File f = new File("D:\\dest/����.jpg");
		FileInputStream fis = new FileInputStream(f);
		DataInputStream fdis = new DataInputStream(fis);
		byte[] directory = new byte[(int)f.length()];
		fdis.readFully(directory);
		
		dos.writeInt((int)f.length());
		dos.write(directory);
		dos.flush();
		 
	}

}
