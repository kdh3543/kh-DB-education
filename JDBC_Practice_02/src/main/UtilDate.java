package main;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class UtilDate {
	public static Date sqlDate(String str, String sql) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(sql);
		return new Date(sdf.parse(str).getTime());
	}
}
