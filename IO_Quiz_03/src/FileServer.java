import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

	public static void main(String[] args) throws Exception{
		try(ServerSocket server = new ServerSocket(30000);){
			System.out.println("연결 대기 중...");
			while(true) {
				try(Socket socket = server.accept();
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						DataInputStream dis = new DataInputStream(socket.getInputStream());){

					System.out.println(socket.getInetAddress()+"로부터 연결되었습니다.");

					File f = new File("D:\\dest");
					File[] list = f.listFiles();
					dos.writeInt(list.length);

					for(File m:list) {
						dos.writeUTF(m.getName());
						dos.flush();
					}

					String input = dis.readUTF();
					System.out.println("클라이언트로 부터 전달받은 파일 이름: "+input);

					File loadFile = new File("D:\\dest/"+input+"");
					try(FileInputStream fis = new FileInputStream(loadFile);
							DataInputStream ramDis = new DataInputStream(fis);){

						byte[] direct = new byte[(int)loadFile.length()];

						ramDis.readFully(direct);

						dos.writeInt(direct.length);
						dos.write(direct);
						dos.flush();
					}
				}catch(Exception e) {
					System.out.println("접속자가 에러가 나서 튕겼습니다.");
				}
			}
		}
	}
}
