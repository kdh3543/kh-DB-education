import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) throws Exception{

		Socket client = new Socket("127.0.0.1", 20000);

		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());

		

		while(true) {
			String msg = JOptionPane.showInputDialog("명령어를 입력하세요.");
			dos.writeUTF(msg);
			dos.flush();

			String dateString = dis.readUTF();
			System.out.println("결과 : " + dateString);
		}
	}

}
