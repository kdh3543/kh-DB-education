package practice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamp {

	public static void main(String[] args) throws Exception {

		long timestamp = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String date = sdf.format(timestamp);
		System.out.println(date);
		
		String str = "2014년 8월 28일";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 M월 dd일");
		
		Date d = sdf2.parse(str);
		System.out.println(d.getTime());
		
		String dateString = sdf.format(d.getTime());
		System.out.println(dateString);
	}
}
