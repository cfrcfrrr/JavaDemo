package datatypeconversiondemo;

public class DataTypeConversionDemoMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 基本数据类型包括boolean、char、byte、short、int、long、float、double

		baseTypeDataRange();

		// 整数常量的类型为int，小数常量的类型为double，如果使用其他数据类型去接收就会报错
		int i = 10; // 不报错
		double d = 1.1; // 不报错
		short l = 10; // 遗留：这个为什么不报错？
		// float f = 1.1; // 报错
		float f1 = 1.1F; // 解决办法1：在数值后加上F（或f），告诉编译器这是一个float数
		float f2 = (float) 1.1; // 解决办法2：使用（float）强制类型转换

		// boolean类型不能和其他基本数据类型相互转换
		convertBooleanAndOtherBaseType();

		// int型数值常量往数据范围小的类型（char、byte、short）会自动转换，但如果超出数据范围会异常
		// 非int型数值常量往数据范围小的类型不会自动转换
		intConstantAutoConvert();

		// char和byte，以及char或short，不能相互转换。因为char型是无符号的，数据范围和byte、short不同
		convertCharAndByteOrShort();

		// 隐式类型转换：小数据范围类型会向大数据范围类型自动转换
		// byte->short
		// short、char->int->long->float->double
		implicitTypeConversion();
		
		// 显示类型转换：大数据范围类型可强制向小数据范围类型转换
		// double→float→long→int→short、char
		// short→byte
		explicitTypeConversions();
	}

	// 数据范围
	// byte 一字节 -2^7 ~ 2^7-1
	// char 两字节 0 ~ 2^16-1
	// short 两字节 -2^15 ~ 2^15-1
	// int 四字节 -2^31 ~ 2^31-1
	// long 八字节 -2^63 ~ 2^63-1
	// float 四字节 -2^127 ~ 2^128
	// double 八字节 -2^1024 ~ 2^1024
	public static void baseTypeDataRange() {
		System.out.println("byte:");
		System.out.println(Byte.SIZE); // 8
		System.out.println(Byte.MIN_VALUE); // -128
		System.out.println(Byte.MAX_VALUE); // 127
		System.out.println("char:");
		System.out.println(Character.SIZE); // 16
		System.out.println((int) Character.MIN_VALUE); // 0，注意这里需要将Character.MIN_VALUE(字符类型)转换为int类型才能显示，应该是因为这两个字符是不可见的
		System.out.println((int) Character.MAX_VALUE); // 65535
		System.out.println("short:");
		System.out.println(Short.SIZE); // 16
		System.out.println(Short.MIN_VALUE); // -32768
		System.out.println(Short.MAX_VALUE); // 32767
		System.out.println("int:");
		System.out.println(Integer.SIZE); // 32
		System.out.println(Integer.MIN_VALUE); // -2147483648
		System.out.println(Integer.MAX_VALUE); // 2147483647
		System.out.println("long:");
		System.out.println(Long.SIZE); // 64
		System.out.println(Long.MIN_VALUE); // -9223372036854775808
		System.out.println(Long.MAX_VALUE); // 9223372036854775807
		System.out.println("float:");
		System.out.println(Float.SIZE); // 32
		System.out.println(Float.MIN_VALUE); // 1.4E-45
		System.out.println(Float.MAX_VALUE); // 3.4028235E38
		System.out.println("double:");
		System.out.println(Double.SIZE); // 64
		System.out.println(Double.MIN_VALUE); // 4.9E-324
		System.out.println(Double.MAX_VALUE); // 1.7976931348623157E308
	}

	@SuppressWarnings("unused")
	public static void convertBooleanAndOtherBaseType() {
		// boolean -> other base type
		boolean b1 = true;
		// char c1 = b1;
		// byte by1 = b1;
		// short s1 = b1;
		// int i1 = b1;
		// long l1 = b1;
		// float f1 = b1;
		// double d1 = b1;
		// other base type -> boolean
		char c2 = 1;
		// boolean b2 = c2;
		byte by2 = 1;
		// boolean b2 = by2;
		short s2 = 1;
		// boolean b2 = s2;
		int i2 = 1;
		// boolean b2 = i2;
		float f2 = 1.1F;
		// boolean b2 = f2;
		double d2 = 1.1;
		// boolean b2 = d2;
	}

	@SuppressWarnings("unused")
	public static void intConstantAutoConvert() {
		byte b1 = -128; // byte类型最小值
		byte b2 = 127; // byte类型最大值
		// byte b3 = -129; // 超出byte类型最小值，编译报错
		// byte b4 = 128; // 超出byte类型最大值，编译报错
		// byte b5 = 1L; // 不是int类型常量也报错
		char c1 = 0;
		char c2 = 65535;
		// char c3 = -1;
		// char c4 = 65536;
		// char c5 = 1L;
		short s1 = -32768;
		short s2 = 32767;
		// short s3 = -32769;
		// short s4 = 32768;
		// short s5 = 1L;
	}

	@SuppressWarnings("unused")
	public static void convertCharAndByteOrShort() {
		char c = 0;
		// byte b = c; // 异常
		// short s = c; // 异常

		byte b2 = 0;
		// char c2 = b2;
		short s2 = b2;

		short s3 = 0;
		// char c3 = s3;
		// byte b3 = s3;
	}

	@SuppressWarnings("unused")
	public static void implicitTypeConversion() {
		byte b = 6;
		// char c = b; // byte到char不能自动转换
		short s = b;
		int i = s;
		long l = i;
		float f = l;
		double d = f;
	}
	
	public static void explicitTypeConversions(){
		System.out.println("---explicitTypeConversions---");
		double d = 2.12345678901234567890123456789; // 2.12345678910111215 -> 2.123456789101112     2.12345678910111255 -> 2.1234567891011125， 最终保留位数不同，什么情况？
		System.out.println(d);
		float f = (float) d;
		System.out.println(f); // 1.1111112
		long l = (long) f;
		System.out.println(l);
		
	}
}
