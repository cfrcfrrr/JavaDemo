package chapter.three;

public class DataTypeConversion {
	@SuppressWarnings("unused")
	public static void main(String args[]) throws InterruptedException {
		byte b = 120;
		
		int i2 = 120;
		byte b2 = (byte)i2; // byte b2=i,�ᱨ��ֻ�г��������Զ�ת����int�ͱ��������Զ�תbyte������ǿ��ת��
		
		byte b3 = 120;
		int i3 = b3;
		
		for(int x = Integer.MAX_VALUE - 1; x < (long)Integer.MAX_VALUE + 1; x ++) {
//		for(int x = 2147483637; x < 2147483648L; x ++) {
			System.out.println(x);
			Thread.sleep(50);
		}
	}
}
