package chapter.four;

import java.util.Date;

public class ClassClassDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		// ʵ��������һ��ͨ��Object���getClass()����
		Date date = new Date();
		Class<?> cls = date.getClass();
		System.out.println(cls);
		// ��������ͨ��"��.class"
		Class<?> cls2 = Date.class;
		System.out.println(cls2);
		// ��������ͨ��Class���forName����
		Class<?> cls3 = Class.forName("java.util.Date");
		System.out.println(cls3);
	}

}
