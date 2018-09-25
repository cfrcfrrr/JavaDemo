package chapter.four;

import java.math.BigDecimal;

class MyMath {
	/**
	 * ʵ��׼ȷλ���������������
	 * @param num Ҫ���������������������
	 * @param scale Ҫ������С��λ
	 * @return ������������������
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
