package chapter.three.assertdemo;

public class AssertDemoMain {
	public static void main(String[] args) {
		int num = 10;
		num = 20;
		assert num == 10: "num不等于10，错误";
		System.out.println("断言默认不启用");
		// 启用断言：java -ea AssertDemoMain.java，eclipse中就是在Run Configurations中配置VM参数为 -ea
	}
}
