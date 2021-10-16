import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Server {
	public static void main(String[] args) throws Exception{
		System.out.println("������ ����ϴ� �� �Դϴ�...");
		// ���� : ��Ʈ��ũ���α׷����� ����� �����ϱ� ���� ���� �ܸ���

		ServerSocket server = new ServerSocket(22000);
		Socket sock = server.accept();

		System.out.println(sock.getInetAddress()+" �� ���� ����Ǿ����ϴ�.");

		InputStream is = sock.getInputStream(); 
		// ���� �ʿ��� �����͸� �޾Ƶ��̰ڴٴ� �ٸ��� ���� ��

		OutputStream os = sock.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		DataInputStream dis = new DataInputStream(is);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		// ���׷��̵�
		while(true) {
			String msg = dis.readUTF();
//			JOptionPane.showMessageDialog(null, msg); // �޼����� �ܼ��� �ƴ� �׷��� ���·� ���� �޴� ��(�ѹ��� ����)
//			System.out.println("Ŭ���̾�Ʈ�� ���� �޼���: " + msg);
			
			if(msg.equals("time")) {
				long timestamp = System.currentTimeMillis();
				
				String date = sdf.format(timestamp);

				dos.writeUTF(date);
			}else {
				dos.writeUTF("�� �����ð���~!?");
			}
		}
	}
}

