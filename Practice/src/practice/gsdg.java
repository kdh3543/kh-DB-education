package practice;
import java.util.Date;
import java.text.SimpleDateFormat;
public class gsdg {
	public static void main(String[] args) throws Exception{

		long a = System.currentTimeMillis();
		System.out.println(a);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		System.out.println(sdf.format(a));
		
		String str = "2008년 04월 20일";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date d = sdf2.parse(str);
		System.out.println(d);
		System.out.println(d.getTime());
		
		String b = sdf2.format(d.getTime());
		System.out.println(b);
		
	}
}

