import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Quiz_02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		System.out.println("��¥�� �Է��ϼ��� (yyyy/mm/dd) : ");
	
		//�Է¹��� ��¥�� timestamp�� ����ϼ���
		//�Է¹��� ��¥�� dd�� mm�� yyyy�� �������� ����ϼ���.		
		String date = sc.nextLine();
	
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date d = sdf.parse(date);
		System.out.println("timestamp ��: " + d.getTime());	
		System.out.println("===================");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd�� MM�� yyyy��");
		String redate = sdf2.format(d.getTime());
		System.out.println("��м��� ��¥ ��: "+redate);
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
