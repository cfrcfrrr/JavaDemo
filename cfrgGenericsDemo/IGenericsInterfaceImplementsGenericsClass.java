package genericsdemo;

// 子类可以继续设置泛型
public class IGenericsInterfaceImplementsGenericsClass<T> implements IGenericsInterface<T> {
	@Override
	public void print(T t) {
		System.out.println(t);
	}
}
