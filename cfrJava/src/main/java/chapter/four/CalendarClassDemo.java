package chapter.four;

import java.util.Calendar;

public class CalendarClassDemo {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		StringBuffer buf = new StringBuffer();
		buf.append(cal.get(Calendar.YEAR)).append("-");
		buf.append(cal.get(Calendar.MONTH) + 1).append("-"); // ���ص��·��Ǵ�0��ʼ��Ҫ������ʾ��Ҫ��1
		buf.append(cal.get(Calendar.DAY_OF_MONTH)).append(" ");
		buf.append(cal.get(Calendar.HOUR_OF_DAY)).append(":");
		buf.append(cal.get(Calendar.MINUTE)).append(":");
		buf.append(cal.get(Calendar.SECOND));
		System.out.println(buf);
	}
}
