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
		// 클라이언트 쪽에서 데이터를 보내겠다는 다리를 놓은 것

		InputStream is = Client.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		DataOutputStream dos = new DataOutputStream(os); 
		// 업그레이드 --> OutputStream은 다리 역할은 하지만 불안하기 때문에 DataOutputStream으로 업그레이드 시킴
		while(true) {
			dos.writeUTF(JOptionPane.showInputDialog("메세지를 입력하세요.")); // 메세지를 콘솔이 아닌 그래픽으로 전달
			dos.flush();

			String msg = dis.readUTF();
			System.out.println("서버로부터의 메세지 : " + msg);
		}
	}
}
