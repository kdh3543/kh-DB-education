package Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {
	public static Date StringToSQLDate(String str, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return new Date(sdf.parse(str).getTime());
	}

}
