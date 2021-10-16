import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Quiz_02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		System.out.println("날짜를 입력하세요 (yyyy/mm/dd) : ");
	
		//입력받은 날짜의 timestamp를 출력하세요
		//입력받은 날짜를 dd일 mm월 yyyy년 형식으로 출력하세요.		
		String date = sc.nextLine();
	
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date d = sdf.parse(date);
		System.out.println("timestamp 값: " + d.getTime());	
		System.out.println("===================");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd일 MM월 yyyy년");
		String redate = sdf2.format(d.getTime());
		System.out.println("재분석한 날짜 값: "+redate);
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
