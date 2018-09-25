package iodemo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Consumer;

public class IODemoMain {
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		// 一、File Demo
		System.out.println("------File Demo------");
		System.out.println("---e disk file list---");
		File file2 = new File("e:" + File.separator);
		if(file2 != null) { // 无法打开的文件夹，要列出信息会报空指针错误
			File [] fileList = file2.listFiles();
			for(int i = 1; i < fileList.length; i ++) {
				if (fileList[i] != null) {					
					System.out.println(fileList[i].getName() + "\t" + (fileList[i].isDirectory()?"文件夹":"文件")); // 遗留：这里三目运算符表达式部分必须用括号括起来，否则会编译报错，为什么？
				}
			}			
		}
		
		File file = new File("e:" + File.separator + "tmp" + File.separator + "tmp" + File.separator + "tmp.txt");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs(); // 遗留：这里怎么实现递归的？
		}
		
		file.createNewFile();
		// 遗留：BigDecimal等类型与基本数据类型之间怎么转换
		// 输出文件大小，以M为单位
		System.out.println("File size(M): " + (new BigDecimal((double)file.length() / 1024 / 1024).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP)) + "M");
		// 输出文件最后修改时间
		System.out.println("File lastModified time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())));
		
		// 二、OutputStream Demo
		System.out.println("------OutputStream Demo------");
		// 覆盖
		// 写第一次
		OutputStream outputStreamA = new FileOutputStream(file); // 遗留：前面没有调用createNewFile()也可以写入，为什么？会自动创建吗？
		String strA = "aaa";
		byte [] bytesA = strA.getBytes();
		outputStreamA.write(bytesA);
		outputStreamA.close();
		System.out.println("After write aaa file size is " + file.length()); // 3
		// 写第二次
		OutputStream outputStreamB = new FileOutputStream(file);
		String strB = "bbbb";
		byte [] bytesB = strB.getBytes();
		outputStreamB.write(bytesB);
		outputStreamB.close();
		System.out.println("After cover write bbbb file size is " + file.length()); // 4，说明将之前的aaa覆盖了
		// 追加
		OutputStream outputStreamC = new FileOutputStream(file, true);
		String strC = "ccccc";
		byte [] bytesC = strC.getBytes();
		outputStreamC.write(bytesC);
		outputStreamC.close();
		System.out.println("After append write ccccc file size is: " + file.length()); // 9，说明是追加
		
		// 三、InputStream Demo
		System.out.println("------InputStream Demo------");
		// 读取方式一：一次性读取
		InputStream inputStreamA = new FileInputStream(file);
		byte [] readBytesA = new byte[1024];
		int len = inputStreamA.read(readBytesA);
		inputStreamA.close();
		System.out.println(new String(readBytesA, 0, len));
		// 读取方式二：逐字节读取
		InputStream inputStreamB = new FileInputStream(file);
		int temp;
		int foot = 0;
		byte [] readBytesB = new byte[1024];
		while((temp = inputStreamB.read()) != -1) {
			readBytesB[foot ++] = (byte)temp;
		}
		inputStreamB.close();
		System.out.println(new String(readBytesB, 0, readBytesB.length));
		
		// 四、Writer Demo
		System.out.println("------Writer Demo------");
		// 覆盖
		Writer writerA = new FileWriter(file);
		writerA.write("aaa");
		writerA.close();
		System.out.println("After cover writer aaa file size is: " + file.length());
		Writer writerB = new FileWriter(file);
		writerB.write("bbbb");
		writerB.close();
		System.out.println("After cover writer bbbb file size is: " + file.length());
		Writer writerC = new FileWriter(file, true);
		writerC.write("ccccc");
		writerC.close();
		System.out.println("After append writer ccccc file size is: " + file.length());
		
		// 五、Reader Demo
		System.out.println("------Reader Demo------");
		Reader reader = new FileReader(file);
		char [] chars = new char[1024];
		int lenChars = reader.read(chars);
		reader.close();
		System.out.println(new String(chars, 0, lenChars));
		
		// 六、转换流
		System.out.println("------InputStreamReader OuputStreamWriter Demo------");
		OutputStream outputStreamD = new FileOutputStream(file);
		Writer writerD = new OutputStreamWriter(outputStreamD);
		writerD.write("中文dddd");
		writerD.close(); // 转换后就是新类的特性，如此时如果不调用close()则不会输出（Writer类特性）
		
		InputStream inputStreamE = new FileInputStream(file);
		Reader readerE = new InputStreamReader(inputStreamE);
		char [] readCharsE = new char[1024];
		int lenE = readerE.read(readCharsE);
		System.out.println(new String(readCharsE, 0, lenE));
		
		outputStreamD.close();
		writerD.close();
		inputStreamE.close();
		readerE.close();
		
		// 七、综合应用：文件拷贝
		System.out.println("------copy demo------");
		// 需要配置参数：Run->Run configuration->配置这个文件的Arguments为“E:\Backup\运动\大地图.png e:\tmp\大地图copy.png”
		// 注意要先运行一次才能配置
		if (args.length != 2) {
			System.out.println("命令行参数错误");
			System.exit(1);
		}
		File inFileF = new File(args[0]);
		if(!inFileF.exists()) {
			System.out.println("源文件不存在");
			System.exit(1);
		}
		File outFileF = new File(args[1]);
		if(!outFileF.getParentFile().exists()) {
			outFileF.getParentFile().mkdirs();
		}
		InputStream inputStreamF = new FileInputStream(inFileF);
		OutputStream outputStreamF = new FileOutputStream(outFileF);
		long startF = System.currentTimeMillis();
		int lenF = 0;
		byte [] bytesF = new byte[1024];
		while((lenF = inputStreamF.read(bytesF)) != -1) { // 如果每次只读取一个字节，速度非常慢
			outputStreamF.write(bytesF, 0, lenF);
		}
		long endF = System.currentTimeMillis();
		System.out.println(endF - startF + "ms");
		
		inputStreamF.close();
		outputStreamF.close();
		
		// 八、内存流
		System.out.println("------ByteArrayInputStream ByteArrayOutputStream Demo------");
		// 示例一：字符串小写转大写
		System.out.println("---string upper---");
		String strG = "fffff";
		InputStream inputStreamG = new ByteArrayInputStream(strG.getBytes());
		OutputStream outputStreamG = new ByteArrayOutputStream();
		int readByteG = 0;
		while((readByteG = inputStreamG.read()) != -1) {
			outputStreamG.write(Character.toUpperCase(readByteG)); // 转大写 // 遗留：Character.toUpperCase(Char ch)接收的是Char类型，传入的是字节，这样也可以吗？
		}
		System.out.println(outputStreamG); // 总是输出一个对象信息，不输出字符串信息，搞了半天是导错类了
		
		inputStreamG.close();
		outputStreamG.close();
		// 示例二：多文件同时读取
		System.out.println("---multi file read---");
		File fileH1 = new File("e:" + File.separator + "tmp" + File.separator + "tmp1.txt");
		File fileH2 = new File("e:" + File.separator + "tmp" + File.separator + "tmp2.txt");
		InputStream inputStreamH1 = new FileInputStream(fileH1);
		InputStream inputStreamH2 = new FileInputStream(fileH2);
		ByteArrayOutputStream outputStreamH = new ByteArrayOutputStream();
		int readByteH1 = 0;
		while((readByteH1 = inputStreamH1.read()) != -1) {
			outputStreamH.write(readByteH1);
		}
		int readByteH2 = 0;
		while((readByteH2 = inputStreamH2.read()) != -1) {
			outputStreamH.write(readByteH2);
		}
		byte [] bytesH = outputStreamH.toByteArray();
		outputStreamH.close();
		inputStreamH1.close();
		inputStreamH2.close();
		System.out.println(new String(bytesH)); // 需要文件内部自己有换行，否则比如tmp1文件最后没有换行，则tmp2文件开头会紧跟在tmp1文件末尾后
		
		// 九、打印流
		System.out.println("------PrintStream PrintWriter Demo------");
		// 示例一：输出到文件
		File fileI = new File("e:" + File.separator + "tmp" + File.separator + "tmp.txt");
		OutputStream outputStreamI = new FileOutputStream(fileI);
		PrintStream printStreamI = new PrintStream(outputStreamI);
		printStreamI.print("ii");
		printStreamI.print(11);
		printStreamI.print(1.1 + 1.1);
//		printStreamI.close(); // 文件内容为：ii112.2 // 遗留：printStreamI不能关闭，否则后面的printStreamI2写不进去，为什么？printStreamI会导致fileI、outputStreamI被关闭吗？
		// 示例二：格式化输出
		String strI2 = "III";
		double doubleI2 = 2222.2222;
		PrintStream printStreamI2 = new PrintStream(outputStreamI);
		printStreamI2.println();
		printStreamI2.printf("姓名：%s, 成绩：%1.2f", strI2, doubleI2); // 姓名：III, 成绩：2222.22 遗留：m.n f的m代表什么？
		printStreamI2.close();
		// String类也可以格式化字符串
		String strI3 = String.format("姓名：%s, 成绩：%1.2f", strI2, doubleI2);
		System.out.println(strI3);
		
		// 十、Systeme类对IO的支持
		System.out.println("------System class------");
		// 输出
		System.out.println("---输出---");
		System.err.println("error");
//		Thread.sleep(1000);
		System.out.println("out");
		OutputStream outputStreamJ = System.out;
		outputStreamJ.write("jjjjj".getBytes()); // 遗留：System.out和System.err的输出顺序不一定，原因了解下。像这里"error"的打印，有出现在上面"姓名:..."的out之前，也有在"jjjjj"之后
		Consumer<String> consumerJ = System.out :: println;
		consumerJ.accept("JJJJJJJ");
		// 输入
		System.out.println("---输入---");
		InputStream inputStreamK = System.in;
		StringBuffer stringBufferK = new StringBuffer();
		System.out.println("-请输入数据-");
		int readByteK = 0;
		while((readByteK = inputStreamK.read()) != -1) {
			if(readByteK == '\n') {
				break;
			}
			stringBufferK.append((char)readByteK);
		}
		System.out.println("输入内容是：" + stringBufferK); // 遗留：但如果输入中文会乱码，怎么解决？
//		inputStreamK.close(); // 遗留：不能关，会导致下面异常，估计是把System.in也关了？

		// 十一、 缓冲输入流
		System.out.println("------BufferedReader Demo------");
		BufferedReader bufferedReaderL = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("-请输入数据-");
		String strL = bufferedReaderL.readLine();
		System.out.println("输入内容是：" + strL); // 遗留：这里输入中文不会乱码，我没有设置编码，那默认编码是什么？
//		bufferedReaderL.close();
		
		// 十二、扫描流
		System.out.println("------Scanner Demo------");
		// 示例一：hasNext()
		Scanner scannerM = new Scanner(System.in);
		System.out.println("-请输入数据-");
		if(scannerM.hasNext()) {
			System.out.println("输入内容是：" + scannerM.next());
		}
//		scannerM.close(); // close()太容易忘了。。。
		// 示例二：正则匹配
		Scanner scannerM2 = new Scanner(System.in);
		System.out.println("-请输入数据（如2018-06-28）-");
		if(scannerM2.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
			System.out.println("输入格式正确，输入内容为：" + scannerM2.next());
		} else {
			System.out.println("输入格式错误");
		}
		// 示例三：设置分隔符
		Scanner scannerM3 = new Scanner(System.in);
		scannerM3.useDelimiter(" "); // 此时输入数据为“a b”，输出时只会是“a”
		System.out.println("-请输入数据（分隔符为空格）-");
		if(scannerM3.hasNext()) {
			System.out.println("输入内容为：" + scannerM3.next());
		}
		
		// 十三、对象序列化
		System.out.println("------ObjectInputStream ObjectOutputStream Demo------");
		// 遗留：ObjectInputStream、ObjectOutputStream和ObjectMapper这些json序列化的，有什么差别，什么时候使用哪个
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("e:" + File.separator + "tmp" + File.separator + "tmp.txt"))); // 序列化生成的应该是二进制数据
		oos.writeObject(new IODemoBean(1,"aaa",new Date()));
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("e:" + File.separator + "tmp" + File.separator + "tmp.txt"))); // 遗留：像这种匿名的怎么关闭，还是自己会关闭？
		Object obj = ois.readObject();
		IODemoBean bean  = (IODemoBean) obj;
		System.out.println(bean); // IODemoBean [id=1, name=aaa, birthday=null]
		ois.close();
	}
}
