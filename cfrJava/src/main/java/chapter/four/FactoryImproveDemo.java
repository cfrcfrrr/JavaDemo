package chapter.four;

interface IFruit {
	public void eat();
}
class Apple implements IFruit {

	@Override
	public void eat() {
		System.out.println("³ÔÆ»¹û");
	}
	
}
class Orange implements IFruit {

	@Override
	public void eat() {
		System.out.println("³ÔéÙ×Ó");
	}
	
}
class Factory {
	public static IFruit getInstance(String className) {
		IFruit f = null;
		try {
			f = (IFruit) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
public class FactoryImproveDemo {
	public static void main(String[] args) {
		IFruit fru = Factory.getInstance("chapter.four.Apple");
		fru.eat();
	}
}
