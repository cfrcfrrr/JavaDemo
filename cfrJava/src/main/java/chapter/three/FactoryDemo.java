package chapter.three;
interface IFruit {
	public void eat();
}

class Apple implements IFruit {
	public void eat() {
		System.out.println("吃苹果");
	}
}
class Orange implements IFruit {
	public void eat() {
		System.out.println("吃橘子");
	}
	
}
class Factory {
	public static IFruit getInstance(String className) {
		if ("apple".equals(className)) {
			return new Apple();
		} else if ("orange".equals(className)){
			return new Orange();
		} else {
			return null;
		}
	}
}
public class FactoryDemo {
	public static void main(String[] args) {
		IFruit fru = Factory.getInstance("apple"); // �ַ��������ÿͻ�����
		fru.eat();
		IFruit fru2 = Factory.getInstance("orange");
		fru2.eat();
	}
}
