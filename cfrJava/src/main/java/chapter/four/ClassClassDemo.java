package chapter.four;

import java.util.Date;

public class ClassClassDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		// 实例化方法一：通过Object类的getClass()方法
		Date date = new Date();
		Class<?> cls = date.getClass();
		System.out.println(cls);
		// 方法二：通过"类.class"
		Class<?> cls2 = Date.class;
		System.out.println(cls2);
		// 方法三：通过Class类的forName方法
		Class<?> cls3 = Class.forName("java.util.Date");
		System.out.println(cls3);
	}

}
