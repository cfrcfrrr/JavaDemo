package chapter.four;

import java.math.BigInteger;

public class BigIntegerClassDemo {

	public static void main(String[] args) {
		BigInteger bigA = new BigInteger("223332222222222");
		BigInteger bigB = new BigInteger("333333333333");
		System.out.println(bigA.add(bigB)); // ¼Ó
		System.out.println(bigA.subtract(bigB)); // ¼õ
		System.out.println(bigA.multiply(bigB)); // ³Ë
		System.out.println(bigA.divide(bigB)); // ³ý
		BigInteger[] result = bigA.divideAndRemainder(bigB); // ³ý ºÍ ÇóÓà
		System.out.println(result[0] + " " + result[1]);
	}

}
