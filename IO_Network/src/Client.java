import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws Exception{

		Socket Client = new Socket("127.0.0.1",22000);

		OutputStream os = Client.getOutputStream(); 
		// Ŭ���̾�Ʈ �ʿ��� �����͸� �����ڴٴ� �ٸ��� ���� ��

		InputStream is = Client.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		DataOutputStream dos = new DataOutputStream(os); 
		// ���׷��̵� --> OutputStream�� �ٸ� ������ ������ �Ҿ��ϱ� ������ DataOutputStream���� ���׷��̵� ��Ŵ
		while(true) {
			dos.writeUTF(JOptionPane.showInputDialog("�޼����� �Է��ϼ���.")); // �޼����� �ܼ��� �ƴ� �׷������� ����
			dos.flush();

			String msg = dis.readUTF();
			System.out.println("�����κ����� �޼��� : " + msg);
		}
	}
}
