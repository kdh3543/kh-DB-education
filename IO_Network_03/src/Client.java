import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception{
		Socket client = new Socket("58.233.236.208", 22000);
		
		DataInputStream dis = new DataInputStream(client.getInputStream());
		
		int size = dis.readInt();
		byte[] fileContents = new byte[size];
		dis.readFully(fileContents);
		
		File f = new File("D:\\dest/downloader");
		FileOutputStream fos = new FileOutputStream(f);
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.write(fileContents);
		dos.flush();
	}
}
