package chapter.four;

import java.lang.reflect.Field;

class Bookh {
	@SuppressWarnings("unused")
	private String title;
}
public class ReflectCallAttribute {
	public static void main(String[] args) throws Exception {
		Class<?> cls = Class.forName("chapter.four.Bookh");
		Object obj = cls.newInstance();
		Field titleField = cls.getDeclaredField("title");
		titleField.setAccessible(true);
		titleField.set(obj, "aaa");
		System.out.println(titleField.get(obj));
	}

}
