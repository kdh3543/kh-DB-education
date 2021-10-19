package practice;

import java.text.SimpleDateFormat;
import java.sql.Date;

public class UtilDate {
	public static Date utilDate(String input, String sql) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(sql);
		return new Date(sdf.parse(input).getTime());
	}
}
