import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		Socket client = new Socket("127.0.0.1", 22000);

		DataInputStream dis = new DataInputStream(client.getInputStream());
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		
		while(true) {
			
			dos.writeUTF("일요일 어디서 보는겁니까??");
			dos.flush();
			String dateString = dis.readUTF();
			System.out.println("결과 : " + dateString);
		}
	}
}
