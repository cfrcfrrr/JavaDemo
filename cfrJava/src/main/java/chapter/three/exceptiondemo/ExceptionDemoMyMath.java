package chapter.three.exceptiondemo;

public class ExceptionDemoMyMath {
	public static int div(int x, int y) throws Exception {
		int result = 0;
		System.out.println("开始");
		try {
			result = x / y;
			return result;
		} catch (Exception e) {
			throw e; // 抛出异常
		} finally {
			System.out.println("结束");
		}
	}
}
