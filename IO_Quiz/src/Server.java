import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(20000);

		System.out.println("������ ����ϴ� ���Դϴ�...");
		Socket sock = server.accept();
		DataInputStream dis = new DataInputStream(sock.getInputStream());
		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� hh:mm:ss");
		int[] lotto = new int[45];
		for(int i=0;i<lotto.length;i++) {
			lotto[i] = i+1;
		}

		while(true) {
			String msg = dis.readUTF();
			if(msg.equals("time")) {
//				JOptionPane.showMessageDialog(null, msg); // �޼����� �ܼ��� �ƴ� �׷��� ���·� ���� �޴� ��(�ѹ��� ����)
//				System.out.println("Ŭ���̾�Ʈ�� ���� �޼���: " + msg);
				String dateString = sdf.format(System.currentTimeMillis());
				dos.writeUTF(dateString);
				dos.flush();
			}else if(msg.equals("lotto")) {
//				JOptionPane.showMessageDialog(null, msg); // �޼����� �ܼ��� �ƴ� �׷��� ���·� ���� �޴� ��(�ѹ��� ����)
//				System.out.println("Ŭ���̾�Ʈ�� ���� �޼���: " + msg);
				for(int i = 0; i <lotto.length*100;i++) {
					int x = (int)(Math.random()*45);
					int y = (int)(Math.random()*45);

					int tmp = lotto[x];
					lotto[x] = lotto[y];
					lotto[y] = tmp;
				}

				String result = "";

				for(int i = 0; i<6;i++) {
					result += lotto[i] + " ";
				}

				dos.writeUTF(result);
				dos.flush();
			}else if(msg.equals("typing")) {
				
			}
		}
	}

}
