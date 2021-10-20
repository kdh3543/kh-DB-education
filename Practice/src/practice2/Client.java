package practice2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket client = new Socket("127.0.0.1", 22000);
		
		DataInputStream dis = new DataInputStream(client.getInputStream());
		
		byte[] directory = new byte[dis.readInt()];
		dis.read(directory);
		
		File f = new File("D:\\Ä¡Å²´À");
		DataOutputStream fdos = new DataOutputStream(new FileOutputStream(f));
		fdos.write(directory);
		fdos.flush();
	}

}
