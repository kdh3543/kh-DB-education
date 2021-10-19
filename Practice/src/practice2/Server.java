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
		
		System.out.println(socket.getInetAddress()+"로부터 연결이 되었습니다.");
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		System.out.println(dis.readUTF());
		System.out.print("메세지를 입력하세요: ");
		dos.writeUTF(sc.nextLine());
	}

}
