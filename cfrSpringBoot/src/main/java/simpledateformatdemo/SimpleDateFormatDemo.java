package simpledateformatdemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {
	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		Date newDate = new Date(date.getTime() + 1 * 24 * 60 * 60 * 1000L);
		System.out.println(newDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDateStr = sdf.format(newDate); // 遗留：没看懂这个例子，转过来转过去干嘛？是不是想实现时间相加减，直接在new Date时操作即可，当时想复杂了？
		System.out.println(newDateStr);
		Date newDate2 = sdf.parse(newDateStr);
		System.out.println(newDate2);
	}
}
