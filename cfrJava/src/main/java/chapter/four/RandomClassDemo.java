package chapter.four;

import java.util.Random;

public class RandomClassDemo {

	public static void main(String[] args) {
		Random rand = new Random();
		int data[] = new int[7];
		int foot = 0;
		while (foot < 7) {
			int t = rand.nextInt(37); // ����һ��������37�������
			if (!isRepeat(data, t)) {
				data[foot ++] = t;
			}
		}
		java.util.Arrays.sort(data);
		for (int x = 0 ; x < data.length ; x ++) {
			System.out.print(data[x] + " ");
		}
	}
	/**
	 * 
	 * @param temp ָ�ѱ��������
	 * @param num �����ɵ�����
	 * @return ���ظ�����true��û�ظ�����false
	 */
	public static boolean isRepeat(int temp[], int num) {
		if (num == 0) {
			return true;
		}
		for (int x = 0 ; x < temp.length ; x ++) {
			if (temp[x] == num) {
				return true;
			}
		}
		return false;
	}
}
