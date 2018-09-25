package chapter.four;

import java.math.BigDecimal;

class MyMath {
	/**
	 * 实现准确位数的四舍五入操作
	 * @param num 要进行四舍五入操作的数字
	 * @param scale 要保留的小数位
	 * @return 处理后的四舍五入数据
	 */
	public static double round(double num, int scale) {
		BigDecimal bigA = new BigDecimal(num);
		BigDecimal bigB = new BigDecimal(1);
		return bigA.divide(bigB, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
public class BigDecimalClassDemo {

	public static void main(String[] args) {
		System.out.println(MyMath.round(19.7832872387, 2));
		System.out.println(MyMath.round(-15.5, 0)); // -16.0
		System.out.println(MyMath.round(15.5, 0)); // 16.0
	}

}
