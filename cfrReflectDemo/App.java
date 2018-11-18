package reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
//		一、实例化Class类对象
		// 1、Object类，public final Class<?> gtClass();
		MyClass myClass = new MyClass();
		// 遗留：为什么这里类的类型是Class<?>，这个？有什么意义？		
		Class<?> myClazz1 = myClass.getClass();
		// 2、类.class
		Class<?> myClazz2 = MyClass.class;
		// 3、Class类：forName()
		Class<?> myClazz3 = Class.forName("reflectdemo.MyClass");
		
//		二、反射实例化对象，Class类：public T newInstance()
		// 遗留：没看懂，这个有什么用，好像也不是单例模式？		
		MyClass myClass1 = MyClassFactory.getInstance("reflectdemo.MySubClassA");
		MyClass myClass2 = MyClassFactory.getInstance("reflectdemo.MySubClassB");
		
//		三、反射调用构造方法
		//	取得所有构造: Class类 public Constructor<?> [] getConstructors()
		//	取得指定参数顺序的构造：Class类 public Constructor<T> getConStructor(Class<?> ... parameterTypes)
		//	实例化：Constructor类 public T newInstance(Object ... initargs)
		//	遗留：为什么getConstructor方法定义的返回值是Constructor<T>，但使用时接收用Constructor<?>
		Constructor<?> constructor = myClazz1.getConstructor(String.class, double.class);
		Object object = constructor.newInstance("aaa", 11.1);
		
//		四、反射调用普通方法
		//	取得所有方法：Class类 public Method [] getMethods()
		//	取得指定方法：Class类 public Method getMethod(String name, Class<?> ... parameterTypes)
		// 	调用方法：Method类 public Object invoke(Object obj, Object ... args)
		
		Method getNameMethod = myClazz1.getMethod("getName");
		Method setNameMethod = myClazz1.getMethod("setName", String.class);
		System.out.println(getNameMethod.invoke(object));
		setNameMethod.invoke(object, "bbb");
		System.out.println(getNameMethod.invoke(object));
		
//		五、反射调用成员
		//	取得所有成员：Class类 public Field [] getDeclaredFields()
		//	取得指定成员：Class类 public Field getDeclaredField(String name)
		//	取得属性内容：Field类 public Object get(Object obj)
		//	取得属性内容：Field类 public void set(Object obj, Object value)
		// 	设置封装： AccessibleObject类（Field父类） public void setAccessible(boolean flag)，设置为true表示取消封装
		Field field = myClazz1.getDeclaredField("name");
		//	遗留问题：field.setAccessible(true);这样设置会有什么风险吗？		
		field.setAccessible(true);
		field.set(object, "ccc");
		System.out.println(field.get(object));
	}

}
