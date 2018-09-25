package chapter.four;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Bookf {
	private double price;
	private String title;

	public Bookf(String title,double price) {
		this.title = title;
		this.price = price;
	}
	public String toString() {
		return this.title + "-" + this.price;
	}
}

public class ReflectCallParaConstructor {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> cls = Class.forName("chapter.four.Bookf");
		Constructor<?> con = cls.getConstructor(String.class,double.class);
		Object obj = con.newInstance("aaa", 11.1);
		System.out.println(obj);
	}

}
