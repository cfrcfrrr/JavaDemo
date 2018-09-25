package chapter.four;

import java.lang.reflect.Method;

class Bookg {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}

public class ReflectCallMethodDemo {

	public static void main(String[] args) throws Exception {
		String fieldName = "title";
		Class<?> cls = Class.forName("chapter.four.Bookg");
		Object obj = cls.newInstance();
		Method setMet = cls.getMethod("set" + initcap(fieldName), String.class);
		Method getMet = cls.getMethod("get" + initcap(fieldName));
		setMet.invoke(obj, "aaa");
		System.out.println(getMet.invoke(obj));
	}
	public static String initcap(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}

