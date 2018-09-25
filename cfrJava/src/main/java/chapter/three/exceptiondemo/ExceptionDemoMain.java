package chapter.three.exceptiondemo;

public class ExceptionDemoMain {
	public static void main(String[] args) {
		System.out.println(Integer.parseInt("a"));
		try {
			System.out.println(ExceptionDemoMyMath.div(10, 1)); // 会执行
			System.out.println(ExceptionDemoMyMath.div(10, 0)); // 异常结束
			System.out.println(ExceptionDemoMyMath.div(10, 2)); // 这里不会执行
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
