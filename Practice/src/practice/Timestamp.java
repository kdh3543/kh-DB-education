package practice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Timestamp {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("날짜를 입력하세요(yyyy-MM-dd): ");
		String input = sc.nextLine();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(input);
		System.out.println(d.getTime());
		System.out.println("===================");
		String a = sdf.format(d.getTime());
		System.out.println(a);
	}
}
