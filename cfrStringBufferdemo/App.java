package stringbufferdemo;

// String类不可改变->StringBuffer类可改变
// String类和StringBuffer类都是CharSequence接口的子类
public class App {
	public static void main(String[] args) {
		
//		一、String转StringBuffer，可在构造时转换，也可使用append转换
		StringBuffer sb = new StringBuffer();
		sb.append("利用").append("append").append("转换");
		System.out.println(sb);
		
		StringBuffer sb2 = new StringBuffer("初始化时转换");
		System.out.println(sb2);
		
//		二、StringBuffer转String，使用toString，或在构造时转换
		String str = sb.toString();
		System.out.println(str);
		
		String str2 = new String(sb2);
		System.out.println(str2);
		
//		三、String、StringBuffer比较
		System.out.println("利用append转换".contentEquals(sb));
		
//		四、StringBuffer类其他方法
//		反转，会修改到原数据，处理中文也没有问题，会将中文当成一个字符
		System.out.println(sb.reverse()); // 换转dneppa用利

//		在指定位置增加数据，处理中文也没有问题，会将中文当成一个字符
		sb.insert(2, "在指定位置插入"); // 换转在指定位置插入dneppa用利
		System.out.println(sb);
		
//		删除部分数据，包含start，不包含end，处理中文也没有问题，会将中文当成一个字符
		sb.delete(5, 10);
		System.out.println(sb); // 换转在指定neppa用利
		
//		五、StringBuilder，用法与StringBuffer类类似
//		StringBuffer方法都是同步方法，属于安全的线程操作；
//		StringBuilder方法都是异步方法，属于非安全的线程操作；
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("string").append("builder").append("!!!");
		System.out.println(stringBuilder);
	}
}
