package genericsdemo;

// 子类也可以直接设置具体类型,较常用
public class IGenericsInterfaceImplementsNoGenericsClass implements IGenericsInterface<String> {
	@Override
	public void print(String t) {
		System.out.println(t);
	}
	
}
