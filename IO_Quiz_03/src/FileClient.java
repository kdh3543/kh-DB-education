import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		try(Socket client = new Socket("222.109.252.199", 22000);
				DataInputStream dis = new DataInputStream(client.getInputStream());
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());){

			int size = dis.readInt();
			File[] list = new File[size];
			for(File f : list) {
				System.out.println("파일 이름: " +dis.readUTF());
			}

			System.out.print("해당 파일리스트 중 다운로드 할 파일 이름: ");
			String keyword = sc.nextLine();
			dos.writeUTF(keyword);
			dos.flush();

			int fileSize = dis.readInt();
			byte[] getFile = new byte[fileSize]; 
			dis.readFully(getFile);

			File f = new File("D:\\"+keyword);
			try(FileOutputStream fos = new FileOutputStream(f);
					DataOutputStream fdos = new DataOutputStream(fos);){

				fdos.write(getFile);
				fdos.flush();
			}
		}
	}
}
