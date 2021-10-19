import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam_05_Date {
	public static void main(String[] args) {
		
		long timestamp = System.currentTimeMillis(); // 현재시간 값을 long 형으로 변환
		System.out.println(timestamp); 
		// timestamp : 1970년 1월 1일부터 현재까지 흐른 시간을 초값으로 표현.
		// Millis(밀리 세컨드)이기 때문에 1/1000 초로 시간이 증가
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String date = sdf.format(timestamp);
		System.out.println(date);
		
		// --------------------------------------------------------------------
		
		String str = "2008년 4월 20일";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 M월 dd일");
		try {
			
			Date d = sdf2.parse(str);
			System.out.println(d);
			System.out.println(d.getTime());
			
			String dateString2 = sdf.format(d.getTime());
			System.out.println(dateString2);
		} catch (ParseException e) {
			e.printStackTrace();	
		}
	}
}
