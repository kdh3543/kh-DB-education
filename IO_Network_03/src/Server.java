import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(22000);
		System.out.println("연결을 대기중입니다...");
		Socket socket = server.accept();
		
		System.out.println(socket.getInetAddress()+"으로부터 연결되었습니다.");
		
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		//1. HDD파일을 RAM으로 꺼내오기.
		
		File target = new File("D:\\2021_09_웹응용과정\\libs\\새 텍스트 문서.txt");
		System.out.println("파일 존재: " + target.exists());
		System.out.println("파일 사이즈 " + target.length());  // long 값으로 리턴
		System.out.println("파일 경로: " + target.getAbsolutePath());
		System.out.println("파일 이름: "+ target.getName());
		
		// new File 안에는 대상 파일 지정
		// File 객체 안에 파일의 내용이 들어가는게 아니라 정보(크기, 만든 날짜, 수정 날짜 등등)만 들어가있음
		// File은 객체안의 정보만 가져옴
		
		FileInputStream fis = new FileInputStream(target);
		// 파일 안의 내용을 가져오기 위한 객체
		DataInputStream dis = new DataInputStream(fis);
		
		byte[] fileContents = new byte[(int)target.length()]; // 배열의 사이즈를 정할 땐 int로 정함
		// 파일의 내용을 저장하기 위한 저장소
		
		dis.readFully(fileContents);
		// filecontents로 내용을 가져오라는 메서드
		
		//2. Server RAM에 있는 파일 내용을 클라이언트 RAM으로 전달
		dos.writeInt(fileContents.length);
		
		dos.write(fileContents);
		dos.flush();
	}
}
