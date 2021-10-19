package practice2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket client = new Socket("127.0.0.1",22000);// 127.0.0.1
		
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		
		dos.writeUTF("¾È³ç");
		System.out.println(dis.readUTF());
	}

}
