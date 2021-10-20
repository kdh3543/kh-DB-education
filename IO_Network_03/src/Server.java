import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(22000);
		System.out.println("������ ������Դϴ�...");
		Socket socket = server.accept();
		
		System.out.println(socket.getInetAddress()+"���κ��� ����Ǿ����ϴ�.");
		
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		//1. HDD������ RAM���� ��������.
		
		File target = new File("D:\\2021_09_���������\\libs\\�� �ؽ�Ʈ ����.txt");
		System.out.println("���� ����: " + target.exists());
		System.out.println("���� ������ " + target.length());  // long ������ ����
		System.out.println("���� ���: " + target.getAbsolutePath());
		System.out.println("���� �̸�: "+ target.getName());
		
		// new File �ȿ��� ��� ���� ����
		// File ��ü �ȿ� ������ ������ ���°� �ƴ϶� ����(ũ��, ���� ��¥, ���� ��¥ ���)�� ������
		// File�� ��ü���� ������ ������
		
		FileInputStream fis = new FileInputStream(target);
		// ���� ���� ������ �������� ���� ��ü
		DataInputStream dis = new DataInputStream(fis);
		
		byte[] fileContents = new byte[(int)target.length()]; // �迭�� ����� ���� �� int�� ����
		// ������ ������ �����ϱ� ���� �����
		
		dis.readFully(fileContents);
		// filecontents�� ������ ��������� �޼���
		
		//2. Server RAM�� �ִ� ���� ������ Ŭ���̾�Ʈ RAM���� ����
		dos.writeInt(fileContents.length);
		
		dos.write(fileContents);
		dos.flush();
	}
}
