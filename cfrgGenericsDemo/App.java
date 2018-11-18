package genericsdemo;

import java.util.ArrayList;
import java.util.List;

// 泛型解决向下转型的安全隐患
public class App {
	public static void main(String[] args) {
//		一、泛型类
		GenericsClass<String> genericsClassString = new GenericsClass<>();
		genericsClassString.setX("set T as String");
		System.out.println(genericsClassString.getX());
		
//		GenericsClass<int> genericsClassInt = new GenericsClass<>(); // 编译错误，不能是基础类型
		GenericsClass<Integer> genericsClassInteger = new GenericsClass<>();
		genericsClassInteger.setX(1);
		System.out.println(genericsClassInteger.getX());
		
//		没有设置泛型具体类型时，将自动使用Object类描述泛型，这么设计是为了兼容旧代码，不推荐使用
		GenericsClass genericsClassDefault = new GenericsClass<>();
		genericsClassDefault.setX("default is Object");
		System.out.println(genericsClassDefault.getX());
		
//		二、泛型方法
		genericsGet(genericsClassString);
		genericsGet(genericsClassInteger);
		genericsSet("aaa");
		System.out.println(genericsReturn("aaa"));
		
		
		
//		泛型方法和Object对比
		List<String> list = new ArrayList<>();
		list.add("genericsMethodA");
		list.add("genericsMethodB");
		list.add("genericsMethodC");
		genericsMethod(list);
//		objectMethod(list); // 编译报错
		
//		泛型上限、下限
		MySuperClass mySuperClass = new MySuperClass("mySuperClass");
		MyClass myClass = new MyClass("myClass");
		MySubClass mySubClass = new MySubClass("mySubClass");
		GenericsClass<MyClass> genericsClassMyClass = new GenericsClass<>();
		genericsClassMyClass.setX(myClass);
		GenericsClass<MySuperClass> genericsClassMySuperClass = new GenericsClass<>();
		genericsClassMySuperClass.setX(mySuperClass);
		GenericsClass<MySubClass> genericsClassMySubClass = new GenericsClass<>();
		genericsClassMySubClass.setX(mySubClass);
		genericsExtends(genericsClassMyClass);
		genericsExtends(genericsClassMySubClass);
//		genericsExtends(genericsClassMySuperClass); // 编译报错
		
		genericsSuper(genericsClassMyClass);
		genericsSuper(genericsClassMySuperClass);
//		genericsSuper(genericsClassMySubClass); // 编译报错
		
//		三、泛型接口
		IGenericsInterfaceImplementsGenericsClass<Integer> iGenericsInterfaceImplementsGenericsClassInteger = new IGenericsInterfaceImplementsGenericsClass<>();
		iGenericsInterfaceImplementsGenericsClassInteger.print(3);
		IGenericsInterfaceImplementsNoGenericsClass iGenericsInterfaceImplementsNoGenericsClass = new IGenericsInterfaceImplementsNoGenericsClass();
		iGenericsInterfaceImplementsNoGenericsClass.print("interface implements no generics class");		
	}
	
//	使用？,则可在声明方法时不具体设置类型，但使用?则方法中只能获取，不能设置
	public static void genericsGet(GenericsClass<?> genericsClass) {
		System.out.println("genericsGet");
		System.out.println(genericsClass.getX());
	}
	
	public static <T> void genericsSet(T t) {
		System.out.println("genericsSet");
		GenericsClass<T> genericsClass = new GenericsClass<>();
		genericsClass.setX(t);
		System.out.println(genericsClass.getX());
	}
		
	public static <T> T genericsReturn(T t) {
		System.out.println("genericsReturn");
		return t;
	}


//	可以使用泛型接收
	public static void genericsMethod(List<?> list) {
		System.out.println(list.size());
	}

//	List<Object>与List<String>、List<Integer>等无继承关系，不能这么使用
	public static void objectMethod(List<Object> list) {
		System.out.println(list.size());
	}
	
//	设置泛型上限，如此例中可设置MyClass或其子类
	public static void genericsExtends(GenericsClass<? extends MyClass> genericsClass) {
		System.out.println(genericsClass.getX());
	}
	
//	  设置泛型下限，此此例中可设置MyClass或其父类
	public static void genericsSuper(GenericsClass<? super MyClass> genericsClass) {
		System.out.println(genericsClass.getX());
	}
}
