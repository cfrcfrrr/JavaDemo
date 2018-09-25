package chapter.three.customexceptiondemo;

public class CustomExceptionDemoMain {
	public static void main(String[] args) {
		int num = 10;
		num = 20;
		try {
			if (num > 10) {
				throw new CustomExceptionDemoMyException("数值过大");
			}
		} catch (Exception e) {
			System.out.println("自己抛的也能捕获到");
			e.printStackTrace();
		}
	}
}
