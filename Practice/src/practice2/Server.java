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
		System.out.println("서버 연결 중...");
		Scanner sc = new Scanner(System.in);
		ServerSocket server = new ServerSocket(22000);
		Socket socket = server.accept();
		System.out.println(socket.getInetAddress()+"로부터 연결되었습니다.");
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		File f = new File("D:\\dest/피자.jpg");
		FileInputStream fis = new FileInputStream(f);
		DataInputStream fdis = new DataInputStream(fis);
		byte[] directory = new byte[(int)f.length()];
		fdis.readFully(directory);
		
		dos.writeInt((int)f.length());
		dos.write(directory);
		dos.flush();
		 
	}

}
