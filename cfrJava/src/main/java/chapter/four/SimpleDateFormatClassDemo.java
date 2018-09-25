package chapter.four;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatClassDemo {
	public static void main(String[] args) throws ParseException {
		Date date = new Date() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		System.out.println(str);
		
		String str2 = "2001-11-11 11:11:11.111";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date2 = sdf2.parse(str2);
		System.out.println(date2);
	}
}
