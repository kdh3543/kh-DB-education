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
		System.out.println("연결을 대기하는 중 입니다...");
		// 소켓 : 네트워크프로그램에서 통신을 수행하기 위한 논리적 단말기

		ServerSocket server = new ServerSocket(22000);
		Socket sock = server.accept();

		System.out.println(sock.getInetAddress()+" 로 부터 연결되었습니다.");

		InputStream is = sock.getInputStream(); 
		// 서버 쪽에서 데이터를 받아들이겠다는 다리를 놓은 것

		OutputStream os = sock.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);

		DataInputStream dis = new DataInputStream(is);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 hh:mm:ss");
		// 업그레이드
		while(true) {
			String msg = dis.readUTF();
//			JOptionPane.showMessageDialog(null, msg); // 메세지를 콘솔이 아닌 그래픽 형태로 전달 받는 것(한번만 가능)
//			System.out.println("클라이언트가 보낸 메세지: " + msg);
			
			if(msg.equals("time")) {
				long timestamp = System.currentTimeMillis();
				
				String date = sdf.format(timestamp);

				dos.writeUTF(date);
			}else {
				dos.writeUTF("못 보내시겠쥬~!?");
			}
		}
	}
}

