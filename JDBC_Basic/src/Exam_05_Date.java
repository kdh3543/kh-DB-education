import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam_05_Date {
	public static void main(String[] args) {
		
		long timestamp = System.currentTimeMillis(); // ����ð� ���� long ������ ��ȯ
		System.out.println(timestamp); 
		// timestamp : 1970�� 1�� 1�Ϻ��� ������� �帥 �ð��� �ʰ����� ǥ��.
		// Millis(�и� ������)�̱� ������ 1/1000 �ʷ� �ð��� ����
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String date = sdf.format(timestamp);
		System.out.println(date);
		
		// --------------------------------------------------------------------
		
		String str = "2008�� 4�� 20��";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy�� M�� dd��");
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
